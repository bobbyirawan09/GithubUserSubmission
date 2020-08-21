package bobby.irawan.githubuser.presentation.detailuser.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.databinding.ActivityDetailUserBinding
import bobby.irawan.githubuser.presentation.detailuser.adapter.ViewPagerAdapter
import bobby.irawan.githubuser.presentation.detailuser.viewmodel.DetailUserViewModel
import bobby.irawan.githubuser.presentation.model.DetailUser
import bobby.irawan.githubuser.presentation.setting.view.SettingActivity
import bobby.irawan.githubuser.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val followerFragment by lazy {
        FollowerFragment()
    }
    private val followingFragment by lazy {
        FollowingFragment()
    }
    private val viewModel by viewModel<DetailUserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel.getDataFromIntent(intent)
        viewModel.getUserFromDatabase()
        viewModel.getDetailUserData()
        initClickListener()
        observeViewModel()
    }

    private fun initClickListener() {
        binding.floatingActionButtonFavorite.setOnClickListener {
            viewModel.onChangefavoriteState()
        }
    }

    private fun observeViewModel() {
        viewModel.detailUserhResult.observe(this, Observer { onShowSuccessResult(it) })
        viewModel.errorLoadDetail.observe(this, Observer { onShowErrorResult(it) })
        viewModel.favoriteState.observe(this, Observer { onShowfavoriteState(it) })
    }

    private fun onShowSuccessResult(detailUser: DetailUser) {
        setViewPager(detailUser.followers.orZero())

        binding.textViewName.text = detailUser.name.orNoInfoString()
        binding.textViewUserName.text = detailUser.username.orNoInfoString()
        binding.textViewLocation.text = detailUser.location.orNoInfoString()
        binding.textViewWork.text = detailUser.company.orNoInfoString()
        binding.textViewFollowers.text = detailUser.followers.toString()
        binding.textViewFollowing.text = detailUser.following.toString()
        binding.textViewRepositories.text = detailUser.repos.toString()
        binding.circleImageViewAvatar.setGlideAttribute(detailUser.imageUrl.orEmpty())

        binding.shimmerFrameLayoutLoading.setGoneShimmer()
        binding.constraintLayoutParentItem.setVisible()
    }

    private fun onShowErrorResult(errorMessage: String) {
        val message =
            applicationContext.getString(R.string.error_message_load_detail_user, errorMessage)
        binding.shimmerFrameLayoutLoading.setGoneShimmer()
        binding.root.showErrorSnackbar(message)
    }

    private fun onShowfavoriteState(isfavorite: Boolean) {
        binding.floatingActionButtonFavorite.setFavoriteState(isfavorite)
    }

    private fun setViewPager(amountFollower: Int) {
        val viewPagerAdapter =
            ViewPagerAdapter(
                this,
                supportFragmentManager
            )
        viewPagerAdapter.setFragmentAndTitle(
            followerFragment,
            this.resources.getQuantityString(R.plurals.tab_follower_text, amountFollower)
        )
        viewPagerAdapter.setFragmentAndTitle(
            followingFragment,
            this.resources.getString(R.string.tab_following_text)
        )
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
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

    companion object {
        const val EXTRA_USER_NAME = "ExtraUserName"
        fun startActivity(context: Context, username: String?) {
            val intent = Intent(context, DetailUserActivity::class.java).apply {
                putExtra(EXTRA_USER_NAME, username)
            }
            context.startActivity(intent)
        }
    }
}
