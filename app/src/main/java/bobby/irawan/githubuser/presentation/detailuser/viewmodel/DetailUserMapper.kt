package bobby.irawan.githubuser.presentation.detailuser.viewmodel

import bobby.irawan.githubuser.data.detailuser.model.DetailUserResponse
import bobby.irawan.githubuser.presentation.model.DetailUser
import bobby.irawan.githubuser.utils.Mapper

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
class DetailUserMapper : Mapper<DetailUserResponse, DetailUser> {
    override fun map(data: DetailUserResponse) = DetailUser(
        name = data.name,
        imageUrl = data.avatarUrl,
        username = data.login,
        company = data.company,
        location = data.location,
        bio = data.bio,
        repos = data.publicRepos,
        gists = data.publicGists,
        followers = data.followers,
        following = data.following
    )
}