package steekezexchange.yaid.com.steekezexchange.ui;


import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.Random;

import steekezexchange.yaid.com.steekezexchange.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectionFragment extends Fragment {

    private static final int ITEMS_QUANTITY = 20;
    private static final int ITEMS_IN_ROW = 5;

    private static final int BOX_QUANTITY = 10; // BOX QUANTITY
    private static final int SIZE_IN_DP = 100; // BOX SIZE
    private static final int MARGIN_IN_DP = 10; // SPACING

    private static final boolean USE_DIP = true; //IS USE Independent pixels

    //ItemView[] myViews = new ItemView[ITEMS_QUANTITY];
    GridLayout myGridLayout;

    public MyCollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_collection, container, false);
        //View rootView = inflater.inflate(R.layout.testlayout, container, false);
/*
        View item2View = (View)rootView.findViewById(R.id.item2);
        ImageView ivItem2 = (ImageView)item2View.findViewById(R.id.ivItem);
        ivItem2.setImageResource(R.drawable.i2);
*/
        // Get density of the screen for correct convection into pixels
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float scale = displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / scale;
        int columns = 0;

        int size = (int)(displayMetrics.widthPixels - 32*scale)/ITEMS_IN_ROW;

        myGridLayout = (GridLayout) rootView.findViewById(R.id.myGridLO);
        myGridLayout.setColumnCount(ITEMS_IN_ROW);

        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);

        //ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.scrollView);
        //scrollView.setBackgroundResource(R.drawable.new_background);

        Random rnd = new Random();
        for (int i=0; i<ITEMS_QUANTITY; i++)
        {
            //myGridLayout.addView(new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), Integer.toString(i), i, i));
            //View v = new MyView(getActivity(), size, 3, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), Integer.toString(i), i, i);
            myGridLayout.addView(v);
        }

        /*

        // Get density of the screen for correct convection into pixels
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float scale = displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / scale;
        int columns = 0;

        if (USE_DIP)
            columns = (int) dpWidth / (SIZE_IN_DP + MARGIN_IN_DP);
        else
            columns = displayMetrics.widthPixels / (SIZE_IN_DP + MARGIN_IN_DP);

        myGridLayout = (GridLayout) rootView.findViewById(R.id.myGridLO);
        myGridLayout.setColumnCount(columns);

        int pixels = 0;
        int margin_pixels = 0;

        if (USE_DIP) {
            // Convert the dps to pixels, based on density scale
            pixels = (int) (SIZE_IN_DP * scale + 0.5f);
            margin_pixels = (int) (MARGIN_IN_DP * scale + 0.5f);
        } else {
            pixels = SIZE_IN_DP;
            margin_pixels = MARGIN_IN_DP;
        }

        Random rnd = new Random();

        for (int i = 0; i < BOX_QUANTITY; i++) {
            View v = new MyView(getActivity(), pixels, margin_pixels, Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
            //v.setOnClickListener(this);
            myGridLayout.addView(v);
        }
*/
        return rootView;
    }


}
