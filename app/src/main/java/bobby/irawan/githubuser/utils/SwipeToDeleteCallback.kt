package bobby.irawan.githubuser.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubuser.AppController
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.presentation.favorite.adapter.FavoriteAdapter


/**
 * Created by bobbyirawan09 on 30/07/20.
 * Taken from https://medium.com/@zackcosborn/step-by-step-recyclerview-swipe-to-delete-and-undo-7bbae1fce27e
 */

class SwipeToDeleteCallback(
    private val adapter: FavoriteAdapter,
    dragDirs: Int = 0,
    swipeDirs: Int = LEFT or RIGHT,
    private val action: (username: String) -> Unit
) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val deleteData = adapter.getItemByPosition(viewHolder.adapterPosition)
        action(deleteData.username)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView: View = viewHolder.itemView
        val backgroundCornerOffset =
            16 //so background is behind the rounded corners of itemView

        val icon = ContextCompat.getDrawable(
            AppController.getInstance(),
            R.drawable.ic_baseline_delete
        ) as Drawable
        val backgroundColor = ColorDrawable(Color.RED)

        val iconMargin: Int = (itemView.height - icon.intrinsicHeight) / 2
        val iconTop: Int =
            itemView.top + (itemView.height - icon.intrinsicHeight) / 2
        val iconBottom: Int = iconTop + icon.intrinsicHeight

        if (dX > 0) { // Swiping to the right
            val iconLeft = itemView.left + iconMargin
            val iconRight = itemView.left + iconMargin + icon.intrinsicWidth
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            backgroundColor.setBounds(
                itemView.left, itemView.top,
                itemView.left + dX.toInt() + backgroundCornerOffset, itemView.bottom
            )
        } else if (dX < 0) { // Swiping to the left
            val iconLeft: Int = itemView.right - iconMargin - icon.intrinsicWidth
            val iconRight: Int = itemView.right - iconMargin
            icon.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            backgroundColor.setBounds(
                itemView.right + dX.toInt() - backgroundCornerOffset,
                itemView.top, itemView.right, itemView.bottom
            )
        } else { // view is unSwiped
            backgroundColor.setBounds(0, 0, 0, 0)
            icon.setBounds(0, 0, 0, 0)
        }

        backgroundColor.draw(c)
        icon.draw(c)
    }
}