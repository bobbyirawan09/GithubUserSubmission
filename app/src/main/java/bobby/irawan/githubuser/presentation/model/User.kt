package bobby.irawan.githubuser.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by bobbyirawan09 on 21/06/20.
 */
@Parcelize
data class User(
    var username: String?,
    var imageUrl: String?
) : Parcelable