package com.example.projectnews.newstories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.SimpleOnPageChangeListener
import com.example.projectnews.R
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager.OnPageChangeListener as OnPageChangeListener


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.myViewPager)
        val tabLayout = findViewById<TabLayout>(R.id.myTabLayout)

        val adapter = TabPagerAdapter(supportFragmentManager)
        viewPager!!.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        viewPager.addOnPageChangeListener(object : SimpleOnPageChangeListener() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewPager.currentItem = position
            }
        })
        /*viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }
        })*/
    }
}
