package com.chaosapp

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.chaosapp.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pager = findViewById<ViewPager>(R.id.viewpager);

        val tabLayout = findViewById<TabLayout>(R.id.tablayout);
        tabLayout.setupWithViewPager(pager);



        val pagerAdapter = ViewPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(CustomFragment(), "Chaos first tab")
        pagerAdapter.addFragment(CustomFragment(), "Chaos second tab")
        pagerAdapter.addFragment(CustomFragment(), "Chaos third tab")

        pager.adapter = pagerAdapter

    }



}
