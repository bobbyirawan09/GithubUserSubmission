package bobby.irawan.githubuser.presentation.favorite.viewmodel

import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.Mapper

/**
 * Created by bobbyirawan09 on 07/08/20.
 */
class FavoriteMapper : Mapper<List<FavoriteEntity>, List<favorite>> {
    override fun map(data: List<FavoriteEntity>): List<favorite> =
        data.map {
            favorite(
                username = it.username,
                imageUrl = it.imageUrl,
                name = it.name,
                bio = it.bio
            )
        }
}