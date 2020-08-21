package bobby.irawan.githubuser.data.user.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
data class UsersResponse(
    @SerializedName("total_count")
    val totalCount: Int? = 0,
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean? = false,
    @SerializedName("items")
    val users: List<UserResponse>? = listOf()
) : Serializable