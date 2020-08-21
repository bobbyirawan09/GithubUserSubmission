package bobby.irawan.githubuser.presentation.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import bobby.irawan.favoriteconsumer.R
import bobby.irawan.favoriteconsumer.databinding.ActivityFavoriteConsumerBinding
import bobby.irawan.favoriteconsumer.di.CustomKoinComponent
import bobby.irawan.favoriteconsumer.model.Favorite
import bobby.irawan.githubuser.utils.isShowEmptyInfo
import bobby.irawan.githubuser.utils.showErrorSnackbar
import org.koin.android.ext.android.get

class FavoriteConsumerActivity : AppCompatActivity(), CustomKoinComponent {

    private val viewModel = get<FavoriteConsumerViewModel>()
    private lateinit var binding: ActivityFavoriteConsumerBinding
    private val adapter = FavoriteConsumerAdapter()
//    private fun inject() = loadKoinModules(presentationModule)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteConsumerBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        inject()

        setView()
        observeViewModel()
        viewModel.getAllfavorite()
    }

    private fun setView() {
        binding.recyclerViewFavorite.addItemDecoration(
            DividerItemDecoration(
                this,
                VERTICAL
            )
        )
        binding.recyclerViewFavorite.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.favoriteResult.observe(this, Observer {
            onShowSuccessResult(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            onShowFailureResult(it)
        })
    }

    private fun onShowSuccessResult(favorites: List<Favorite>) {
        binding.textViewEmptyDataMessage.isShowEmptyInfo(favorites)
        adapter.submitList(favorites)
    }

    private fun onShowFailureResult(errorMessage: String?) {
        val message =
            applicationContext.getString(R.string.error_message_load_user_favorite, errorMessage)
        binding.root.showErrorSnackbar(message)
    }
}