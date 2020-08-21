package bobby.irawan.githubuser.presentation.detailuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubuser.databinding.ItemFollowUserBinding
import bobby.irawan.githubuser.presentation.model.Following
import bobby.irawan.githubuser.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 12/07/20.
 */
class FollowingAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dataFollowing = listOf<Following>()

    fun setDataFollowing(dataFollowing: List<Following>) {
        this.dataFollowing = dataFollowing
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFollowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataFollowing.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FollowingViewHolder -> holder.bind(dataFollowing[position])
        }
    }

    class FollowingViewHolder(private val binding: ItemFollowUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(following: Following) {
            with(binding) {
                textViewUserName.text = following.username
                circleImageViewAvatar.setGlideAttribute(following.imageUrl.orEmpty())
            }
        }
    }
}