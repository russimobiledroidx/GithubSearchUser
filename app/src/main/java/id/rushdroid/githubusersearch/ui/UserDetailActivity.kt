package id.rushdroid.githubusersearch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.rushdroid.githubusersearch.R
import id.rushdroid.githubusersearch.ui.detail.DetailPagerAdapter
import id.rushdroid.githubusersearch.viewmodel.UserDetailViewModel

@AndroidEntryPoint
class UserDetailActivity : AppCompatActivity() {
    private val viewModel: UserDetailViewModel by viewModels()

    private lateinit var progressBar: ProgressBar
    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var bioTextView: TextView
    private lateinit var repoCountTextView: TextView
    private lateinit var followersCountTextView: TextView
    private lateinit var followingCountTextView: TextView
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        // Inisialisasi ProgressBar
        progressBar = findViewById(R.id.progressLoading)
        progressBar.visibility = View.VISIBLE  // Tampilkan saat Activity dibuka

        // Inisialisasi Views
        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        usernameTextView = findViewById(R.id.usernameTextView)
        bioTextView = findViewById(R.id.bioTextView)
        repoCountTextView = findViewById(R.id.repoCountTextView)
        followersCountTextView = findViewById(R.id.followersCountTextView)
        followingCountTextView = findViewById(R.id.followingCountTextView)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)

        val username = intent.getStringExtra(EXTRA_USERNAME) ?: return
        setupToolbar(username)

        // Observe user details
        viewModel.getUserDetail(username).observe(this) { user ->
            user?.let {
                nameTextView.text = it.name ?: "No Name"
                usernameTextView.text = "@$username"
                bioTextView.text = it.bio ?: "No bio available"
                repoCountTextView.text = "${it.publicRepos} Repositories"
                followersCountTextView.text = "${it.followers} Followers"
                followingCountTextView.text = "${it.following} Following"
                Glide.with(this).load(it.avatarUrl).into(avatarImageView)

                // Sembunyikan ProgressBar setelah data selesai dimuat
                hideProgressBar()
            }
        }

        setupViewPager(username)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    private fun setupToolbar(username: String) {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.apply {
            title = username
            setDisplayHomeAsUpEnabled(true)
        }

        findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun setupViewPager(username: String) {
        val adapter = DetailPagerAdapter(this, username)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Repos"
                1 -> "Followers"
                2 -> "Following"
                else -> ""
            }
        }.attach()
    }

    companion object {
        private const val EXTRA_USERNAME = "username"
        fun newIntent(context: Context, username: String) =
            Intent(context, UserDetailActivity::class.java).apply {
                putExtra(EXTRA_USERNAME, username)
            }
    }
}
