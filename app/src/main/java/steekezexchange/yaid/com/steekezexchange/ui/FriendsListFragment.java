package steekezexchange.yaid.com.steekezexchange.ui;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.adapters.FriendsListAdapter;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsListFragment extends Fragment {

    private final static int CM_DELETE_ID = 1;

    private OnFriendItemsEvents onItemsEvents;

    public interface OnFriendItemsEvents{
        public void deleteFriend(String name);
        public void friendOpen(String name);
    }

    private ListView lvFriends;
    private List<FriendItem> friendsList;

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onItemsEvents = (OnFriendItemsEvents) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " OnFriendItemsEvents");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_friends_list, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView){
        lvFriends = (ListView)rootView.findViewById(R.id.lvFriends);
/*
        //FOR TEST
        testFriends();
*/
        if(friendsList==null)
            friendsList = new ArrayList<FriendItem>();
        FriendsListAdapter adapter = new FriendsListAdapter(getActivity(),friendsList);
        lvFriends.setAdapter(adapter);
        registerForContextMenu(lvFriends);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId()==lvFriends.getId())
        {
            menu.add(Menu.NONE, CM_DELETE_ID, Menu.NONE, R.string.friends_list_delete_context);
        }
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case CM_DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                FriendItem friendItem = (FriendItem) lvFriends.getItemAtPosition(info.position);
                onItemsEvents.deleteFriend(friendItem.getEmail());
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }

    public void prepareData(ArrayList<FriendItem> friendList)
    {
        this.friendsList = friendList;
    }

    public List<FriendItem> getFriendsList()
    {
        return friendsList;
    }

    public void showList()
    {
        ((FriendsListAdapter)lvFriends.getAdapter()).notifyDataSetChanged();
    }

    private void testFriends(){
        friendsList = new ArrayList<FriendItem>();
        for (int i=0; i<30; i++)
            friendsList.add(new FriendItem("Test"+i+"@tester.com"));
    }

}
