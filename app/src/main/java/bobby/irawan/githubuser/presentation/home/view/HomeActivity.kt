package bobby.irawan.githubuser.presentation.home.view

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.databinding.ActivityHomeBinding
import bobby.irawan.githubuser.presentation.detailuser.view.DetailUserActivity
import bobby.irawan.githubuser.presentation.favorite.view.FavoriteActivity
import bobby.irawan.githubuser.presentation.home.adapter.HomeAdapter
import bobby.irawan.githubuser.presentation.home.viewmodel.HomeViewModel
import bobby.irawan.githubuser.presentation.model.User
import bobby.irawan.githubuser.presentation.setting.view.SettingActivity
import bobby.irawan.githubuser.utils.*
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(),
    HomeAdapter.OnClickListener, TextView.OnEditorActionListener {

    private lateinit var binding: ActivityHomeBinding

    private val adapter: HomeAdapter by lazy {
        HomeAdapter(this)
    }

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSearchComponent()
        setRecyclerViewComponent()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.searchResult.observe(this, Observer {
            onShowSuccessResult(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            onShowErrorResult(it)
        })
    }

    private fun setSearchComponent() {
        binding.searchViewLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
        binding.searchViewLayout.editText?.setOnEditorActionListener(this)
    }

    private fun setRecyclerViewComponent() {
        binding.recyclerViewGithubUser.setHasFixedSize(true)
        binding.recyclerViewGithubUser.adapter = adapter
    }

    private fun onShowSuccessResult(searchResult: List<User>) {
        binding.shimmerFrameLayoutLoading.setGoneShimmer()
        adapter.setUsers(searchResult)
        binding.recyclerViewGithubUser.setVisible()
        binding.textViewEmptyDataMessage.isShowEmptyInfo(searchResult, ::isShowEmptyInfo)
    }

    private fun onShowErrorResult(errorMessage: String) {
        val message = applicationContext.getString(R.string.error_message_load_user, errorMessage)
        binding.shimmerFrameLayoutLoading.setGoneShimmer()
        binding.recyclerViewGithubUser.setGone()
        binding.root.showErrorSnackbar(message)
    }

    private fun isShowEmptyInfo() {
        binding.recyclerViewGithubUser.setGone()
        binding.shimmerFrameLayoutLoading.setGone()
        binding.textViewEmptyDataMessage.setVisible()
    }

    override fun onClick(user: User) {
        DetailUserActivity.startActivity(this, user.username)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settings -> {
                SettingActivity.startActivity(this)
            }
            R.id.favorite -> {
                FavoriteActivity.startActivity(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_DONE) {
            val imm: InputMethodManager =
                applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(p0?.windowToken, 0)
            viewModel.getUsersData(p0?.text.toString())
            binding.textViewTooltipSearch.setGone()
            binding.shimmerFrameLayoutLoading.setVisibleShimmer()
            binding.recyclerViewGithubUser.setGone()
            return true
        }
        return false
    }
}
