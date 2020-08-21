package bobby.irawan.githubuser.presentation.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.content.UriMatcher.NO_MATCH
import android.database.Cursor
import android.net.Uri
import bobby.irawan.githubuser.data.favorite.FavoriteDao
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME
import org.koin.android.ext.android.inject

class favoriteProvider : ContentProvider() {

    private val favoriteDao by inject<FavoriteDao>()

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return -1
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var cursor: Cursor? = null
        when (uriMatcher.match(uri)) {
            favorite -> {
                cursor = favoriteDao.getAllFavoriteCursor()
            }
            favorite_ID -> {
                cursor =
                    favoriteDao.getfavoriteByUsernameCursor(uri.lastPathSegment.toString())
            }
        }
        cursor?.setNotificationUri(context?.contentResolver, uri)
        return cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return -1
    }

    companion object {
        const val AUTHORITY = "bobby.irawan.githubuser"
        private const val favorite = 1
        private const val favorite_ID = 2
        private val uriMatcher = UriMatcher(NO_MATCH)

        init {
            uriMatcher.addURI(
                AUTHORITY, DATABASE_TABLE_NAME,
                favorite
            )
            uriMatcher.addURI(
                AUTHORITY, "$DATABASE_TABLE_NAME/#",
                favorite_ID
            )
        }
    }
}
