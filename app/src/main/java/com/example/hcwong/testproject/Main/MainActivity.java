package com.example.hcwong.testproject.Main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hcwong.testproject.Library.DepthPageTransformer;
import com.example.hcwong.testproject.Main.ListNews.ListFragment;
import com.example.hcwong.testproject.Main.CreateNews.LocalFragment;
import com.example.hcwong.testproject.Model.Article;
import com.example.hcwong.testproject.Main.News.NewsFragment;
import com.example.hcwong.testproject.R;

public class MainActivity extends AppCompatActivity implements LocalFragment.OnListFragmentInteractionListener, NewsFragment.OnListFragmentInteractionListener, ListFragment.OnListFragmentInteractionListener{

    private static final int NUM_PAGES=3;
    private ViewPager pager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager=findViewById(R.id.pager);
        adapter= new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.setPageTransformer(true,new DepthPageTransformer());

        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(pager);

    }


    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem()==0)
            super.onBackPressed();
        else
            pager.setCurrentItem(pager.getCurrentItem()-1);
    }

    @Override
    public void onListFragmentInteraction(Article item) {

    }

    private class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0)
                return NewsFragment.newInstance();
            else if (position == 1)
                return  LocalFragment.newInstance();
            else if (position == 2)
                return  ListFragment.newInstance();
            else
                return  LocalFragment.newInstance();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return getResources().getString(R.string.news);
            else if (position == 1)
                return getResources().getString(R.string.add_news);
            else if (position == 2)
                return getResources().getString(R.string.list_news);
            else
                return getResources().getString(R.string.non);
        }
    }

}
