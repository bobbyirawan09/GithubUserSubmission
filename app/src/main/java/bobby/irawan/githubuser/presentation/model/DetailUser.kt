package bobby.irawan.githubuser.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
@Parcelize
data class DetailUser(
    var name: String? = "",
    var imageUrl: String? = "",
    var username: String? = "",
    var company: String? = "",
    var location: String? = "",
    var bio: String? = "",
    var repos: Int? = 0,
    var gists: Int? = 0,
    var followers: Int? = 0,
    var following: Int? = 0
) : Parcelable