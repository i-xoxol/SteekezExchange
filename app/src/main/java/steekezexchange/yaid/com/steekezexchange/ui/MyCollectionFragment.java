package steekezexchange.yaid.com.steekezexchange.ui;


import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;
import steekezexchange.yaid.com.steekezexchange.utils.FileHelper;
import steekezexchange.yaid.com.steekezexchange.utils.Parser;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyCollectionFragment extends Fragment {

    private static final int ITEMS_QUANTITY = 24;
    private static final int ITEMS_IN_ROW = 4;

    private static final int BOX_QUANTITY = 10; // BOX QUANTITY
    private static final int SIZE_IN_DP = 100; // BOX SIZE
    private static final int MARGIN_IN_DP = 10; // SPACING

    private static final boolean USE_DIP = true; //IS USE Independent pixels

    //ItemView[] myViews = new ItemView[ITEMS_QUANTITY];
    GridLayout myGridLayout;

    ArrayList<SteekezItem> steekezArray;

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

        int marginLayout = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int size = (int)(displayMetrics.widthPixels - marginLayout*scale)/ITEMS_IN_ROW;

        myGridLayout = (GridLayout) rootView.findViewById(R.id.myGridLO);
        myGridLayout.setColumnCount(ITEMS_IN_ROW);

        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);

        String myData = FileHelper.readMyCollectionFile(getActivity());

        if (myData==null || myData.length()==0)
        {
            String[] names = getResources().getStringArray(R.array.steekez_name);
            steekezArray = new ArrayList<SteekezItem>();

            for (int i=0; i<names.length; i++)
                steekezArray.add(new SteekezItem(i+1,1,names[i]));
        }
        else
            steekezArray = Parser.parseFile(myData);

        for (int i=0; i<ITEMS_QUANTITY; i++)
        {

            //View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), Integer.toString(i), i+1, i+1);
            //View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), steekezArray.get(i).getName(),steekezArray.get(i).getId(), steekezArray.get(i).getQuantity());
            View v = new ItemView(getActivity(), size, imgs.getResourceId(i,-1), steekezArray.get(i));
            v.setOnClickListener(itemClick);
            myGridLayout.addView(v);
        }

        return rootView;
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ItemView)v).setQuantity(((ItemView) v).getQuantity() + 1);
            SteekezItem item = ((ItemView)v).getItem();
            int index = steekezArray.indexOf(item);
            item.setQuantity(item.getQuantity()+1);
            steekezArray.set(index, item);
            FileHelper.writeMyCollection(getActivity(),Parser.getStringData("i@i.ua",steekezArray));
        }
    };

}
