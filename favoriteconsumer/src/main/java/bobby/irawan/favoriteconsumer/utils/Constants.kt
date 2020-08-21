package bobby.irawan.githubuser.utils

import android.net.Uri

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
object Constants {

    const val DATABASE_TABLE_NAME = "favorite"
    const val COLUMN_NAME = "name"
    const val COLUMN_USERNAME = "username"
    const val COLUMN_IMAGE_URL = "image_url"
    const val COLUMN_BIO = "bio"

    const val AUTHORITY = "bobby.irawan.githubuser"
    const val SCHEME = "content"

    val CONTENT_URI = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(DATABASE_TABLE_NAME)
        .build()

}