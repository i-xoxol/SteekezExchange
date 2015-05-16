package steekezexchange.yaid.com.steekezexchange.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;
import steekezexchange.yaid.com.steekezexchange.mvp.CollectionPresenter;
import steekezexchange.yaid.com.steekezexchange.mvp.CollectionView;
import steekezexchange.yaid.com.steekezexchange.mvp.DataPresenterImpl;
import steekezexchange.yaid.com.steekezexchange.mvp.MainView;
import steekezexchange.yaid.com.steekezexchange.utils.FileHelper;
import steekezexchange.yaid.com.steekezexchange.utils.Parser;

public class MainActivity extends Activity implements CollectionFragment.SaveDataListener, MainView{

    private static final String MY_COL_FRAGMENT_TAG = "MY_COL_FRAGMENT_TAG";
    private static final String FRIEND_LIST_FRAGMENT_TAG = "FRIEND_LIST_FRAGMENT_TAG";

    final private static int NUM_PAGES = 2;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    private ProgressBar progressBar;
    private CollectionPresenter dataPresenter;
    private CollectionFragment collectionFragment;
    private FriendsListFragment friendsListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareFragments();
        initViews();
        dataPresenter = new DataPresenterImpl(this,this);

    }

    private void prepareFragments()
    {
        FragmentManager fm = getFragmentManager();
        collectionFragment = (CollectionFragment)fm.findFragmentByTag(MY_COL_FRAGMENT_TAG);
        if(collectionFragment==null)
            collectionFragment = new CollectionFragment();
        friendsListFragment = (FriendsListFragment)fm.findFragmentByTag(FRIEND_LIST_FRAGMENT_TAG);
        if(friendsListFragment==null)
            friendsListFragment = new FriendsListFragment();
    }


    private void initViews()
    {
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(pageChangeListener);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

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
    protected void onResume() {
        super.onResume();
        dataPresenter.onLoad();
    }

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
        else if(id == R.id.action_add_friend)
        {
            showAddDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void saveData(String collectionName, ArrayList<SteekezItem> collection) {
        if(collection!=null)
            FileHelper.writeMyCollection(this, Parser.getStringData(collectionName, collection));
    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);
        mPager.setVisibility(View.INVISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        mPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(String collectionName, List<SteekezItem> myCollection, List<FriendItem> items) {
        //FragmentManager fm = getFragmentManager();
        //CollectionFragment collectionFragment = (CollectionFragment) fm.findFragmentByTag(MY_COL_FRAGMENT_TAG);
        if(collectionFragment!=null)
            if (collectionFragment.isAdded())
                collectionFragment.showCollection(collectionName, (ArrayList<SteekezItem>) myCollection);
            else
                collectionFragment.prepareData(collectionName,(ArrayList<SteekezItem>) myCollection);

        if(friendsListFragment!=null)
        {
            friendsListFragment.prepareData((ArrayList<FriendItem>) items);
            if(friendsListFragment.isAdded())
                friendsListFragment.showList();
        }

    }

    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        //FragmentManager fm;

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            //this.fm=fm;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            if(position==0)
                fragment = collectionFragment;
            else if (position == 1)
                fragment = friendsListFragment;

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

    private void showAddDialog()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle(getString(R.string.add_dialog_title));
        alert.setMessage(getString(R.string.add_dialog_message));

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        alert.setView(input);

        alert.setPositiveButton(getString(R.string.add_dialog_but_add), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();

                ArrayList<FriendItem> friendsList = (ArrayList<FriendItem>) friendsListFragment.getFriendsList();
                friendsList.add(new FriendItem(value));
                FileHelper.writeFile(MainActivity.this, value, "jjj");
                friendsListFragment.prepareData(friendsList);
                friendsListFragment.showList();
            }
        });

        alert.setNegativeButton(getString(R.string.add_dialog_but_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });

        alert.show();
    }
}
