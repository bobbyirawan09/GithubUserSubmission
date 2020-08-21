package bobby.irawan.githubuser.presentation.widget

import android.content.Intent
import android.widget.RemoteViewsService
import org.koin.android.ext.android.get

/**
 * Created by bobbyirawan09 on 05/08/20.
 */
class StackWidgetService : RemoteViewsService() {

    private val stackRemoteViewsFactory = get<RemoteViewsFactory>()

    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory = stackRemoteViewsFactory
}