package com.example.hcwong.testproject.Main

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.hcwong.testproject.Library.DepthPageTransformer
import com.example.hcwong.testproject.Main.ListNews.ListFragment
import com.example.hcwong.testproject.Main.CreateNews.LocalFragment
import com.example.hcwong.testproject.Model.Article
import com.example.hcwong.testproject.Main.News.NewsFragment
import com.example.hcwong.testproject.R

class MainActivity : AppCompatActivity(), LocalFragment.OnListFragmentInteractionListener, NewsFragment.OnListFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener {
    private var pager: ViewPager? = null
    private var adapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pager = findViewById(R.id.pager)
        adapter = PagerAdapter(supportFragmentManager)
        pager!!.adapter = adapter
        pager!!.setPageTransformer(true, DepthPageTransformer())

        val tabLayout = findViewById<TabLayout>(R.id.sliding_tabs)
        tabLayout.setupWithViewPager(pager)

    }


    override fun onBackPressed() {
        if (pager!!.currentItem == 0)
            super.onBackPressed()
        else
            pager!!.currentItem = pager!!.currentItem - 1
    }

    override fun onListFragmentInteraction(item: Article) {

    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return if (position == 0)
                NewsFragment.newInstance()
            else if (position == 1)
                LocalFragment.newInstance()
            else if (position == 2)
                ListFragment.newInstance()
            else
                LocalFragment.newInstance()
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return if (position == 0)
                resources.getString(R.string.news)
            else if (position == 1)
                resources.getString(R.string.add_news)
            else if (position == 2)
                resources.getString(R.string.list_news)
            else
                resources.getString(R.string.non)
        }
    }

    companion object {

        private val NUM_PAGES = 3
    }

}
