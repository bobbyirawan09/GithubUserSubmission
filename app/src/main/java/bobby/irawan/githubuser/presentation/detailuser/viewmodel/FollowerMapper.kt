package bobby.irawan.githubuser.presentation.detailuser.viewmodel

import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowerResponse
import bobby.irawan.githubuser.presentation.model.Follower
import bobby.irawan.githubuser.utils.Mapper

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
class FollowerMapper : Mapper<List<DetailUserFollowerResponse>, List<Follower>> {
    override fun map(data: List<DetailUserFollowerResponse>): List<Follower> =
        data.map {
            Follower(
                username = it.login,
                imageUrl = it.avatarUrl
            )
        }
}