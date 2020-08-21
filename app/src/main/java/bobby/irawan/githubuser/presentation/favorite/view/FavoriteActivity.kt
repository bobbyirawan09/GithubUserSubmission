package bobby.irawan.githubuser.presentation.favorite.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.ItemTouchHelper
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.databinding.ActivityFavoriteBinding
import bobby.irawan.githubuser.presentation.detailuser.view.DetailUserActivity
import bobby.irawan.githubuser.presentation.favorite.adapter.FavoriteAdapter
import bobby.irawan.githubuser.presentation.favorite.viewmodel.FavoriteViewModel
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.presentation.setting.view.SettingActivity
import bobby.irawan.githubuser.utils.SwipeToDeleteCallback
import bobby.irawan.githubuser.utils.isShowEmptyInfo
import bobby.irawan.githubuser.utils.showErrorSnackbar
import bobby.irawan.githubuser.utils.showSuccessSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity(),
    FavoriteAdapter.ClickListener {

    private val viewModel by viewModel<FavoriteViewModel>()
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter =
        FavoriteAdapter(
            this
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setView()
        observeViewModel()
    }

    private fun setView() {
        val itemTouchHelper = ItemTouchHelper(
            SwipeToDeleteCallback(
                adapter
            ) { username ->
                viewModel.deletefavorite(username)
            })
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavorite)
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
        viewModel.successMessage.observe(this, Observer { message ->
            binding.root.showSuccessSnackbar(message)
        })
    }

    private fun onShowSuccessResult(favorites: List<favorite>) {
        adapter.submitList(favorites)
        binding.textViewEmptyDataMessage.isShowEmptyInfo(favorites)
    }

    private fun onShowFailureResult(errorMessage: String?) {
        val message =
            applicationContext.getString(R.string.error_message_load_detail_user, errorMessage)
        binding.root.showErrorSnackbar(message)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        menu.findItem(R.id.favorite).isVisible = false
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
            R.id.settings -> {
                SettingActivity.startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickUser(username: String) {
        DetailUserActivity.startActivity(this, username)
    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, FavoriteActivity::class.java)
            context.startActivity(intent)
        }
    }
}