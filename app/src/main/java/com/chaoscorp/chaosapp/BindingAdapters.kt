package com.chaoscorp.chaosapp

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import androidx.databinding.BindingAdapter


object BindingAdapters {


    @BindingAdapter("bind:handler")
    @JvmStatic
    fun bindViewPagerAdapter(view: ViewPager, activity: MainActivity) {
        val adapter = MainTabsAdapter(activity.supportFragmentManager)
        view.adapter = adapter
    }

    @BindingAdapter("bind:pager")
    @JvmStatic
    fun bindViewPagerTabs(view: TabLayout, pagerView: ViewPager) {
        view.setupWithViewPager(pagerView, true)
    }
}