package steekezexchange.yaid.com.steekezexchange.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ListView lvFriends;
    private List<FriendItem> friendsList;

    public FriendsListFragment() {
        // Required empty public constructor
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
