package bobby.irawan.githubuser.presentation.home.viewmodel

import bobby.irawan.githubuser.data.user.model.UsersResponse
import bobby.irawan.githubuser.presentation.model.User
import bobby.irawan.githubuser.utils.Mapper

/**
 * Created by bobbyirawan09 on 12/07/20.
 */
class UserMapper : Mapper<UsersResponse, List<User>> {
    override fun map(data: UsersResponse): List<User> {
        return data.users.orEmpty().map {
            User(
                username = it.login,
                imageUrl = it.avatarUrl
            )
        }
    }
}