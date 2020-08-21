package bobby.irawan.githubuser.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.favoriteconsumer.databinding.ItemFavoriteConsumerBinding
import bobby.irawan.favoriteconsumer.model.Favorite
import bobby.irawan.githubuser.utils.orGone
import bobby.irawan.githubuser.utils.orNoInfoString
import bobby.irawan.githubuser.utils.setGlideAttribute

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
class FavoriteConsumerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Favorite>() {
        override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem.username == newItem.username
        }

        override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(dataFavorite: List<Favorite>) {
        differ.submitList(dataFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemFavoriteConsumerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavoriteViewHolder -> holder.bind(differ.currentList[position])
        }
    }

    class FavoriteViewHolder(private val binding: ItemFavoriteConsumerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite) {
            with(binding) {
                textViewUsername.text = favorite.username.orNoInfoString()
                circleImageViewAvatar.setGlideAttribute(favorite.imageUrl)
                textViewName.text = favorite.name.orNoInfoString()
                textViewBio.orGone(favorite.bio)
                textViewBio.text = favorite.bio
            }
        }
    }
}