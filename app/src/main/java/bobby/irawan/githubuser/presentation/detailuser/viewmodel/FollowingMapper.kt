package bobby.irawan.githubuser.presentation.detailuser.viewmodel

import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowingResponse
import bobby.irawan.githubuser.presentation.model.Following
import bobby.irawan.githubuser.utils.Mapper

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
class FollowingMapper : Mapper<List<DetailUserFollowingResponse>, List<Following>> {
    override fun map(data: List<DetailUserFollowingResponse>): List<Following> =
        data.map {
            Following(
                username = it.login,
                imageUrl = it.avatarUrl
            )
        }
}