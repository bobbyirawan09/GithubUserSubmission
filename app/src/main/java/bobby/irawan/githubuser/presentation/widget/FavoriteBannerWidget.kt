package bobby.irawan.githubuser.presentation.widget

import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE
import android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.Intent.URI_INTENT_SCHEME
import android.widget.RemoteViews
import androidx.core.net.toUri
import bobby.irawan.githubuser.R

/**
 * Implementation of App Widget functionality.
 */
class FavoriteBannerWidget : AppWidgetProvider() {

    companion object {

        private fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(URI_INTENT_SCHEME).toUri()

            val views = RemoteViews(context.packageName, R.layout.favorite_widget)
            views.setRemoteAdapter(R.id.stack_view_favorite, intent)
            views.setEmptyView(R.id.stack_view_favorite, R.id.empty_view)

            val toastIntent = Intent(context, FavoriteBannerWidget::class.java)
            toastIntent.action = ACTION_APPWIDGET_UPDATE
            toastIntent.putExtra(EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(URI_INTENT_SCHEME).toUri()
            val toastPendingIntent =
                PendingIntent.getBroadcast(context, 0, toastIntent, FLAG_UPDATE_CURRENT)
            views.setPendingIntentTemplate(R.id.stack_view_favorite, toastPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId
            )
        }
    }

    override fun onEnabled(context: Context) {
        //Do nothing
    }

    override fun onDisabled(context: Context) {
        //Do nothing
    }

}