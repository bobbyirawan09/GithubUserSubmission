package bobby.irawan.favoriteconsumer.helper

import android.database.Cursor
import bobby.irawan.favoriteconsumer.model.Favorite
import bobby.irawan.githubuser.utils.Constants.COLUMN_BIO
import bobby.irawan.githubuser.utils.Constants.COLUMN_IMAGE_URL
import bobby.irawan.githubuser.utils.Constants.COLUMN_NAME
import bobby.irawan.githubuser.utils.Constants.COLUMN_USERNAME

/**
 * Created by bobbyirawan09 on 01/08/20.
 */
object MappingHelper {
    fun mapCursorToList(cursor: Cursor?): List<Favorite> {
        val favorites = mutableListOf<Favorite>()
        cursor?.apply {
            while (moveToNext()) {
                val favorite = Favorite(
                    getString(getColumnIndexOrThrow(COLUMN_NAME)),
                    getString(getColumnIndexOrThrow(COLUMN_USERNAME)),
                    getString(getColumnIndexOrThrow(COLUMN_IMAGE_URL)),
                    getString(getColumnIndexOrThrow(COLUMN_BIO))
                )
                favorites.add(favorite)
            }
        }
        return favorites
    }
}