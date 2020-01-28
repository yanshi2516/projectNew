package com.example.projectnews.newstories

import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter


class TabPagerAdapter(fm: FragmentManager) :

FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){


    override fun getItem(position: Int): Fragment {
        var storiesType : String = ""
       return when (position) {
             0 -> FragmentNews.getInstance("topstories")
             1 -> FragmentNews.getInstance("newstories")
             2 -> FragmentNews.getInstance("beststories")
           else -> FragmentNews.getInstance( "topstories")
        }

    }
    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position)
        {
            0-> "Top"
            1-> "New"
            2-> "Best"
            else -> null
        }
    }
}
