package bobby.irawan.githubuser.presentation.widget

import android.content.Context
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import android.widget.Toast
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.data.favorite.FavoriteDao
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.orNoInfoString
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 05/08/20.
 */
class StackRemoteViewsFactory(
    private val context: Context,
    private val favoriteDao: FavoriteDao
) : RemoteViewsService.RemoteViewsFactory {

    private var favorites = listOf<favorite>()

    override fun onCreate() {
        //Do nothing
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        val identifyToken = Binder.clearCallingIdentity()
        GlobalScope.launch(Dispatchers.Main) {
            favoriteDao.getAllfavorite().collect { favoriteEntities ->
                favorites = favoriteEntities.map { favoriteEntity ->
                    favorite.from(favoriteEntity)
                }
            }
        }
        Binder.restoreCallingIdentity(identifyToken)
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        val remoteView = RemoteViews(context.packageName, R.layout.favorite_banner_widget)
        val imageUrl = favorites.get(position).imageUrl
        try {
            val bitmap = Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .fallback(R.drawable.ic_baseline_broken_image)
                .submit(430, 390)
                .get()
            remoteView.setImageViewBitmap(R.id.image_view_user, bitmap)

            val username = favorites.get(position).username
            val name = favorites.get(position).name.orNoInfoString()
            val userInfo =
                context.getString(R.string.widget_name_placeholder, name).orNoInfoString()
            val usernameInfo = context.getString(R.string.widget_username_placeholder, username)
            remoteView.setTextViewText(R.id.text_view_username, userInfo + usernameInfo)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error " + e.localizedMessage, Toast.LENGTH_LONG).show()
        }
        return remoteView
    }

    override fun getCount(): Int = favorites.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {
        //Do nothing
    }
}