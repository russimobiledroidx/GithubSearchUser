package id.rushdroid.githubusersearch.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val username: String
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3 // 3 Tab: Repos, Followers, Following

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RepositoryFragment.newInstance(username)
            1 -> FollowersFragment.newInstance(username)
            2 -> FollowingFragment.newInstance(username)
            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
