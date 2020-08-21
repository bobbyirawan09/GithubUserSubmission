package bobby.irawan.githubuser.data.detailuser.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DetailUserResponse(
    @SerializedName("avatar_url")
    val avatarUrl: String? = "",
    val bio: String? = "",
    val blog: String? = "",
    val company: String? = "",
    @SerializedName("created_at")
    val createdAt: String? = "",
    val email: String? = "",
    @SerializedName("events_url")
    val eventsUrl: String? = "",
    val followers: Int? = 0,
    @SerializedName("followers_url")
    val followersUrl: String? = "",
    val following: Int? = 0,
    @SerializedName("following_url")
    val followingUrl: String? = "",
    @SerializedName("gists_url")
    val gistsUrl: String? = "",
    @SerializedName("gravatar_id")
    val gravatarId: String? = "",
    val hireable: String? = "",
    @SerializedName("html_url")
    val htmlUrl: String? = "",
    val id: Int? = 0,
    val location: String? = "",
    val login: String? = "",
    val name: String? = "",
    @SerializedName("node_id")
    val nodeId: String? = "",
    @SerializedName("organizations_url")
    val organizationsUrl: String? = "",
    @SerializedName("public_gists")
    val publicGists: Int? = 0,
    @SerializedName("public_repos")
    val publicRepos: Int? = 0,
    @SerializedName("received_events_url")
    val receivedEventsUrl: String? = "",
    @SerializedName("repos_url")
    val reposUrl: String? = "",
    @SerializedName("site_admin")
    val siteAdmin: Boolean? = false,
    @SerializedName("starred_url")
    val starredUrl: String? = "",
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String? = "",
    @SerializedName("twitter_username")
    val twitterUsername: String? = "",
    val type: String? = "",
    @SerializedName("updated_at")
    val updatedAt: String? = "",
    val url: String? = ""
) : Serializable