package bobby.irawan.githubuser.presentation.model

import android.os.Parcelable
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
@Parcelize
data class favorite(
    var name: String = "",
    var username: String = "",
    var imageUrl: String = "",
    var bio: String? = ""
) : Parcelable {
    companion object {
        fun from(entity: FavoriteEntity) = favorite(
            entity.name,
            entity.username,
            entity.imageUrl,
            entity.bio
        )
    }
}