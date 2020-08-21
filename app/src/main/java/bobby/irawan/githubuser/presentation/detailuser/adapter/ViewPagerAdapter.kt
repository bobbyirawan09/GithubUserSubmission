package bobby.irawan.githubuser.presentation.detailuser.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
class ViewPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = mutableListOf<String>()
    private val fragments = mutableListOf<Fragment>()

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    fun setFragmentAndTitle(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getCount(): Int {
        return 2
    }
}