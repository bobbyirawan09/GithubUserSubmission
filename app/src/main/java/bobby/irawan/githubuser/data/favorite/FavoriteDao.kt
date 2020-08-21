package bobby.irawan.githubuser.data.favorite

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity.Companion.COLUMN_USERNAME
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity.Companion.DATABASE_TABLE_NAME
import kotlinx.coroutines.flow.Flow


/**
 * Created by bobbyirawan09 on 26/07/20.
 */

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
    fun getAllfavorite(): Flow<List<FavoriteEntity>>

    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE $COLUMN_USERNAME = :username")
    suspend fun getfavoriteByUsername(username: String): FavoriteEntity

    @Insert(onConflict = IGNORE)
    suspend fun addfavorite(favoriteEntity: FavoriteEntity)

    @Query("DELETE FROM $DATABASE_TABLE_NAME WHERE $COLUMN_USERNAME = :username")
    suspend fun deleteByUsername(username: String): Int

    @Query("SELECT * FROM $DATABASE_TABLE_NAME")
    fun getAllFavoriteCursor(): Cursor

    @Query("SELECT * FROM $DATABASE_TABLE_NAME WHERE $COLUMN_USERNAME = :username")
    fun getfavoriteByUsernameCursor(username: String): Cursor
}