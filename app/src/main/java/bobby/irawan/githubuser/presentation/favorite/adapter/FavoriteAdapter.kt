package bobby.irawan.githubuser.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubuser.databinding.ItemFavoriteBinding
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.orGone
import bobby.irawan.githubuser.utils.orNoInfoString
import bobby.irawan.githubuser.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
class FavoriteAdapter(private val clickListener: ClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<favorite>() {
        override fun areItemsTheSame(oldItem: favorite, newItem: favorite): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: favorite, newItem: favorite): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(datafavorite: List<favorite>) {
        differ.submitList(datafavorite)
    }

    fun getItemByPosition(position: Int): favorite {
        return differ.currentList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return favoriteViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is favoriteViewHolder -> holder.bind(differ.currentList[position], clickListener)
        }
    }

    class favoriteViewHolder(private val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: favorite, clickListener: ClickListener) {
            with(binding) {
                textViewUsername.text = favorite.username.orNoInfoString()
                circleImageViewAvatar.setGlideAttribute(favorite.imageUrl)
                textViewName.text = favorite.name.orNoInfoString()
                textViewBio.orGone(favorite.bio)
                textViewBio.text = favorite.bio
                root.setOnClickListener { clickListener.onClickUser(favorite.username) }
            }
        }
    }

    interface ClickListener {
        fun onClickUser(username: String)
    }
}