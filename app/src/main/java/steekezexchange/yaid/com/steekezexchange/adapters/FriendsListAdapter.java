package steekezexchange.yaid.com.steekezexchange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;

/**
 * Created by Игорь on 13.05.2015.
 */
public class FriendsListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<FriendItem> friendsList;

    public FriendsListAdapter(Context ctx, List<FriendItem> friends)
    {
        this.inflater = LayoutInflater.from(ctx);
        this.friendsList = friends;
    }

    @Override
    public int getCount() {
        return friendsList.size();
    }

    @Override
    public Object getItem(int position) {
        return friendsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolderItem viewHolder;

        if(convertView==null)
        {
            convertView = inflater.inflate(R.layout.friend_item, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvFriendName);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        String name = friendsList.get(position).getEmail();
        if(name!=null)
            viewHolder.tvName.setText(name);

        return convertView;
    }

    static class ViewHolderItem {
        TextView tvName;
    }
}
