package com.chaosapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    val fragments = ArrayList<Fragment>()
    val fragmentsTitles = ArrayList<String>()

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentsTitles.get(position)

    }

    public fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment);
        fragmentsTitles.add(title);
    }


}



