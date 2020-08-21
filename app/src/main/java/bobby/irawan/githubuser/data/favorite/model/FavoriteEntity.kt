package bobby.irawan.githubuser.data.favorite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

@Entity(tableName = DATABASE_TABLE_NAME)
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    var id: Int = 0,
    @ColumnInfo(name = COLUMN_NAME)
    var name: String = "",
    @ColumnInfo(name = COLUMN_USERNAME)
    var username: String = "",
    @ColumnInfo(name = COLUMN_IMAGE_URL)
    var imageUrl: String = "",
    @ColumnInfo(name = COLUMN_BIO)
    var bio: String = ""
) {
    companion object {
        const val DATABASE_TABLE_NAME = "favorite"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_BIO = "bio"
    }
}