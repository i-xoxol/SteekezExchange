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

        // Get density of the screen for correct convection into pixels
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float scale = displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / scale;
        int columns = 0;

        int size = (int)(displayMetrics.widthPixels - 32*scale)/ITEMS_IN_ROW;

        myGridLayout = (GridLayout) rootView.findViewById(R.id.myGridLO);
        myGridLayout.setColumnCount(ITEMS_IN_ROW);

        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);



        Random rnd = new Random();
        for (int i=0; i<ITEMS_QUANTITY; i++)
        {
            View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), Integer.toString(i), i, i);
            myGridLayout.addView(v);
        }

        return rootView;
    }


}
