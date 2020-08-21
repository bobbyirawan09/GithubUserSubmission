package bobby.irawan.githubuser.utils

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import bobby.irawan.githubuser.AppController
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.utils.Constants.Result
import bobby.irawan.githubuser.utils.Constants.Result.Error
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Response

/**
 * Created by bobbyirawan09 on 22/06/20.
 */

fun <T> Response<T>.convertResponseApi(): Result {
    return when {
        this.isSuccessful -> Result.Success(this.body())
        else -> Error(this.message())
    }
}

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}

fun ShimmerFrameLayout.setVisibleShimmer() {
    this.visibility = View.VISIBLE
    this.startShimmer()
}

fun ShimmerFrameLayout.setGoneShimmer() {
    this.visibility = View.GONE
    this.stopShimmer()
}

fun String?.orNoInfoString(): String =
    if (this.isNullOrEmpty()) AppController.getInstance()
        .getString(R.string.no_available_information) else this

fun Int?.orZero(): Int = this ?: 0

fun CircleImageView.setGlideAttribute(imageUrl: String) =
    Glide.with(this).load(imageUrl)
        .placeholder(R.drawable.ic_baseline_image)
        .error(R.drawable.ic_baseline_broken_image)
        .fallback(R.drawable.ic_baseline_broken_image)
        .into(this)

fun FloatingActionButton.setFavoriteState(isfavorite: Boolean) =
    if (isfavorite) {
        this.setImageDrawable(
            ContextCompat.getDrawable(
                AppController.getInstance(),
                R.drawable.ic_baseline_favorite
            )
        )
    } else {
        this.setImageDrawable(
            ContextCompat.getDrawable(
                AppController.getInstance(),
                R.drawable.ic_baseline_favorite_border
            )
        )
    }

fun View.showErrorSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.danger))
    snackbar.show()
}

fun View.showSuccessSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.success))
    snackbar.show()
}

fun Boolean?.orFalse() = this ?: false

fun TextView.isShowEmptyInfo(data: List<*>?, action: () -> Unit = {}) = if (data.isNullOrEmpty()) {
    this.setVisible()
    action()
} else {
    this.setGone()
}

fun View.orGone(text: String?) = if (text.isNullOrEmpty()) this.setGone() else this.setVisible()
