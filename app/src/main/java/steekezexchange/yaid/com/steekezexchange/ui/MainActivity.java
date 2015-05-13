package steekezexchange.yaid.com.steekezexchange.ui;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;

public class MainActivity extends Activity {

    private static final String MY_COL_FRAGMENT_TAG = "MY_COL_FRAGMENT_TAG";
    private static final String FRIEND_LIST_FRAGMENT_TAG = "FRIEND_LIST_FRAGMENT_TAG";

    final private static int NUM_PAGES = 2;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    List<View> pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews()
    {
        mPager = (ViewPager) findViewById(R.id.pager);
        pages = new ArrayList<View>();
        View page = this.getLayoutInflater().inflate(R.layout.fragment_my_collection, (ViewGroup) getWindow().findViewById(android.R.id.content),false);
        pages.add(page);
        page = this.getLayoutInflater().inflate(R.layout.fragment_friends_list, (ViewGroup) getWindow().findViewById(android.R.id.content),false);
        pages.add(page);

        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(pageChangeListener);

    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            invalidateOptionsMenu();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (mPager.getCurrentItem() == 1)
        {
            getMenuInflater().inflate(R.menu.menu_friends, menu);
        }
        else
            getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        FragmentManager fm;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            this.fm=fm;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if(position==0)
            {
                fragment = (MyCollectionFragment)fm.findFragmentByTag(MY_COL_FRAGMENT_TAG);
                if(fragment==null)
                    fragment = new MyCollectionFragment();
            }
            else if (position == 1)
            {
                fragment = (FriendsListFragment)fm.findFragmentByTag(FRIEND_LIST_FRAGMENT_TAG);
                if(fragment==null)
                    fragment = new FriendsListFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return MainActivity.this.getString(R.string.tab_one);
                case 1:
                    return MainActivity.this.getString(R.string.tab_two);
            }
            return null;
        }

    }
}
