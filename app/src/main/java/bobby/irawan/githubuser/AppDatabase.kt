package bobby.irawan.githubuser

import androidx.room.Database
import androidx.room.RoomDatabase
import bobby.irawan.githubuser.data.favorite.FavoriteDao
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
@Database(entities = arrayOf(FavoriteEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}