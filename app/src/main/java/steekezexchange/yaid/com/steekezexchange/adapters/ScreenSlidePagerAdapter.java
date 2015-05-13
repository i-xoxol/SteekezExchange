package steekezexchange.yaid.com.steekezexchange.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;

/**
 * Created by Игорь on 12.05.2015.
 */
public class ScreenSlidePagerAdapter extends PagerAdapter {

    List<View> pages = null;
    Context ctx;

    public ScreenSlidePagerAdapter(List<View> pages, Context ctx) {
        this.pages = pages;
        this.ctx = ctx;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        View v = pages.get(position);
        ((ViewPager) collection).addView(v, 0);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view){
        ((ViewPager) collection).removeView((View) view);
    }

    @Override
    public int getCount(){
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object){
        return view.equals(object);
    }

    @Override
    public void finishUpdate(ViewGroup arg0){
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1){
    }

    @Override
    public Parcelable saveState(){
        return null;
    }

    @Override
    public void startUpdate(ViewGroup arg0){
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ctx.getString(R.string.tab_one);
            case 1:
                return ctx.getString(R.string.tab_two);
        }

        return null;
    }
}

