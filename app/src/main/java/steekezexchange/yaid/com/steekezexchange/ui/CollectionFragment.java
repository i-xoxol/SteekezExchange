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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment{

    private final static int CM_DECR_ID = 11;

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
    GridLayout myGridLayout;

    ArrayList<SteekezItem> steekezArray;

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
        myGridLayout = (GridLayout) rootView.findViewById(R.id.myGridLO);
        myGridLayout.setColumnCount(ITEMS_IN_ROW);
    }

    private View.OnClickListener itemClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setSteekezQuantity((ItemView) v, ((ItemView) v).getQuantity() + 1);
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
        // Get density of the screen for correct convection into pixels
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        final float scale = displayMetrics.density;
        //float dpWidth = displayMetrics.widthPixels / scale;
        //int columns = 0;

        int marginLayout = (int) getResources().getDimension(R.dimen.activity_horizontal_margin);
        int size = (int)(displayMetrics.widthPixels - marginLayout*scale)/ITEMS_IN_ROW;

        TypedArray imgs = getResources().obtainTypedArray(R.array.steekezimages);
        TypedArray imgsFrozen = getResources().obtainTypedArray(R.array.steekezfrozenimages);

/*
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
*/
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

            registerForContextMenu(v);
            myGridLayout.addView(v);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        saveCallback.saveData(collectionName, steekezArray);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v instanceof ItemView)
        {
            menu.add(Menu.NONE, CM_DECR_ID, Menu.NONE, R.string.steekez_decr_context);
            //AdapterView.AdapterContextMenuInfo aMenuInfo = new AdapterView.AdapterContextMenuInfo(v,0,((ItemView)v).getNum());
            //menuInfo=aMenuInfo;
            //aMenuInfo.targetView = v;
            //((AdapterView.AdapterContextMenuInfo) menuInfo).targetView = v;
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
                if(info!=null) {
                    ItemView v = (ItemView)info.targetView;
                    if(v.getQuantity()>0)
                        setSteekezQuantity(v, v.getQuantity()-1);
                    //String name = ((ItemView)info.targetView).getName();
                    //Toast.makeText(getActivity(),name,Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    private void setSteekezQuantity(ItemView v, int quantity)
    {
        v.setQuantity(quantity);
        SteekezItem item = v.getItem();
        int index = steekezArray.indexOf(item);
        item.setQuantity(quantity);
        steekezArray.set(index, item);
    }

}
