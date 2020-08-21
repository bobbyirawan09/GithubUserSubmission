package bobby.irawan.githubuser.presentation.detailuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubuser.databinding.ItemFollowUserBinding
import bobby.irawan.githubuser.presentation.model.Follower
import bobby.irawan.githubuser.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 13/07/20.
 */
class FollowerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataFollower = listOf<Follower>()

    fun setDataFollower(dataFollower: List<Follower>) {
        this.dataFollower = dataFollower
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFollowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataFollower.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FollowerViewHolder -> holder.bind(dataFollower[position])
        }
    }

    class FollowerViewHolder(private val binding: ItemFollowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: Follower) {
            with(binding) {
                textViewUserName.text = follower.username
                circleImageViewAvatar.setGlideAttribute(follower.imageUrl.orEmpty())
            }
        }
    }
}