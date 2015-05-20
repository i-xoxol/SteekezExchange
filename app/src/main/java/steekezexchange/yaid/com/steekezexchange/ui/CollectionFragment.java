package steekezexchange.yaid.com.steekezexchange.ui;


import android.app.Activity;
import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.adapters.SteekezAdapter;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment{

    private final static int CM_DECR_ID = 1;

    public interface SaveDataListener {
        public void saveData(String collectionName, ArrayList<SteekezItem> collection);
    }

    private static final int ITEMS_QUANTITY = 24;
    private static final int ITEMS_IN_ROW = 4;

    private static final int BOX_QUANTITY = 10; // BOX QUANTITY
    private static final int SIZE_IN_DP = 100; // BOX SIZE
    private static final int MARGIN_IN_DP = 10; // SPACING

    private static final boolean USE_DIP = true; //IS USE Independent pixels

    private SaveDataListener saveCallback;

    private String collectionName;

    //ItemView[] myViews = new ItemView[ITEMS_QUANTITY];
    HeaderGridView myGridLayout;

    ArrayList<SteekezItem> steekezArray;
    SteekezAdapter steekezAdapter;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            saveCallback = (SaveDataListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " saveDataListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_collection, container, false);
        initView(rootView);
        if(steekezArray!=null)
            showCollection();
        return rootView;
    }

    private void initView(View rootView)
    {
        myGridLayout = (HeaderGridView) rootView.findViewById(R.id.myGridLO);
        //myGridLayout.setColumnCount(ITEMS_IN_ROW);
        myGridLayout.setNumColumns(ITEMS_IN_ROW);
        View headerView = View.inflate(getActivity(),R.layout.header_layout,null);
        myGridLayout.addHeaderView(headerView,null,false);
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((ItemView)v).setQuantity(((ItemView) v).getQuantity() + 1);
            SteekezItem item = ((ItemView)v).getItem();
            int index = steekezArray.indexOf(item);
            item.setQuantity(item.getQuantity() + 1);
            steekezArray.set(index, item);
            //FileHelper.writeMyCollection(getActivity(),Parser.getStringData("i@i.ua",steekezArray));
        }
    };

    public void prepareData(String name, List<SteekezItem> collection)
    {
        collectionName = name;
        steekezArray = (ArrayList<SteekezItem>) collection;
    }

    public void showCollection(String name, ArrayList<SteekezItem> collection) {
        prepareData(name,collection);
        showCollection();
    }

    public void showCollection() {
        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);
        TypedArray imgsFrozen = getResources().obtainTypedArray(R.array.steekezfrozenimages);

        steekezAdapter = new SteekezAdapter(getActivity(), steekezArray, imgs,imgsFrozen);
        myGridLayout.setAdapter(steekezAdapter);
    }
/*
    public void showCollection() {
        // Get density of the screen for correct convection into pixels
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float scale = displayMetrics.density;
        //float dpWidth = displayMetrics.widthPixels / scale;
        //int columns = 0;

        int marginLayout = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int size = (int)(displayMetrics.widthPixels - marginLayout*scale)/ITEMS_IN_ROW;

        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);
        TypedArray imgsFrozen = getResources().obtainTypedArray(R.array.steekezfrozenimages);

        myGridLayout.removeAllViews();
        for (int i=0; i<ITEMS_QUANTITY; i++)
        {

            //View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), Integer.toString(i), i+1, i+1);
            //View v = new ItemView(getActivity(), size, 0, imgs.getResourceId(i,-1), steekezArray.get(i).getName(),steekezArray.get(i).getId(), steekezArray.get(i).getQuantity());
            View v = new ItemView(getActivity(), size, imgs.getResourceId(i,-1), imgsFrozen.getResourceId(i,-1), steekezArray.get(i));
            v.setOnClickListener(itemClick);
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return v.showContextMenu();
                }
            });
            myGridLayout.addView(v);
        }
        //registerForContextMenu(myGridLayout);
    }
*/
    @Override
    public void onPause() {
        super.onPause();
        saveCallback.saveData(collectionName, steekezArray);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()==myGridLayout.getId())
        {
            menu.add(Menu.NONE, CM_DECR_ID, Menu.NONE, R.string.friends_list_delete_context);
            AdapterView.AdapterContextMenuInfo aMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
            ItemView itemView = (ItemView) aMenuInfo.targetView;//This is how I get a grip on the view that got long pressed.
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);

        switch (item.getItemId()) {
            case CM_DECR_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                //FriendItem friendItem = (FriendItem) myGridLayout.getvi.getItemAtPosition(info.position);
                //String name = ((ItemView)info.targetView).getName();
                //Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
                View v = info.targetView;
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
}
