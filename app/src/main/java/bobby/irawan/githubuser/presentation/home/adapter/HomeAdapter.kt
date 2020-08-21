package bobby.irawan.githubuser.presentation.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubuser.databinding.ItemUserBinding
import bobby.irawan.githubuser.presentation.model.User
import bobby.irawan.githubuser.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 21/06/20.
 */
class HomeAdapter(private val onClickListener: OnClickListener?) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var users: List<User> = listOf()
    private lateinit var context: Context

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                textViewName.text = user.username
                circleImageViewAvatar.setGlideAttribute(user.imageUrl.orEmpty())
                root.setOnClickListener {
                    onClickListener?.onClick(user)
                }
            }
        }
    }

    interface OnClickListener {
        fun onClick(user: User)
    }
}