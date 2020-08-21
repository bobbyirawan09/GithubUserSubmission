package bobby.irawan.githubuser.utils

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import bobby.irawan.favoriteconsumer.AppFavoriteController
import bobby.irawan.favoriteconsumer.R
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by bobbyirawan09 on 22/06/20.
 */

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun String?.orNoInfoString(): String =
    if (this.isNullOrEmpty()) AppFavoriteController.getInstance()
        .getString(R.string.no_available_information) else this

fun CircleImageView.setGlideAttribute(imageUrl: String) =
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_baseline_image)
        .error(R.drawable.ic_baseline_broken_image)
        .fallback(R.drawable.ic_baseline_broken_image)
        .into(this)

fun View.showErrorSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.danger))
    snackbar.show()
}

fun TextView.isShowEmptyInfo(data: List<*>?, action: () -> Unit = {}) = if (data.isNullOrEmpty()) {
    this.setVisible()
    action()
} else {
    this.setGone()
}

fun View.orGone(text: String?) = if (text.isNullOrEmpty()) this.setGone() else this.setVisible()