package steekezexchange.yaid.com.steekezexchange.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by Игорь on 20.05.2015.
 */
public class SteekezAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private List<SteekezItem> steekezItems;
    private TypedArray images,imagesFrozen;

    public SteekezAdapter(Context ctx, List<SteekezItem> steekezItems, TypedArray images, TypedArray imagesFrozen)
    {
        this.inflater = LayoutInflater.from(ctx);
        this.steekezItems = steekezItems;
        this.images=images;
        this.imagesFrozen=imagesFrozen;
    }
    @Override
    public int getCount() {
        return steekezItems.size();
    }

    @Override
    public Object getItem(int position) {
        return steekezItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return steekezItems.get(position).getId();
    }

    public void updateItem(SteekezItem item)
    {
        int index = steekezItems.indexOf(item);
        steekezItems.set(index,item);
        this.notifyDataSetChanged();
        this.notifyDataSetInvalidated();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem viewHolder;

        if(convertView==null) {
            convertView = inflater.inflate(R.layout.item_layout_new, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.tvName = (TextView)convertView.findViewById(R.id.tvItemName);
            viewHolder.tvQuant = (TextView)convertView.findViewById(R.id.tvQuantity);
            viewHolder.tvNum = (TextView)convertView.findViewById(R.id.tvNum);
            viewHolder.ivSteekez = (ImageView)convertView.findViewById(R.id.ivItem);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        //viewHolder.tvName.setText(steekezItems.get(position).getName());
        viewHolder.tvName.setText("lllll");
        viewHolder.tvNum.setText(Integer.toString(steekezItems.get(position).getId()));
        int quant = steekezItems.get(position).getQuantity();

        if(quant==0)
        {
            viewHolder.ivSteekez.setImageResource(imagesFrozen.getResourceId(position,-1));
            viewHolder.tvQuant.setText("");
        }
        else
        {
            viewHolder.ivSteekez.setImageResource(images.getResourceId(position,-1));
            if(quant==1)
                viewHolder.tvQuant.setText("");
            else
                viewHolder.tvQuant.setText("x"+Integer.toString(quant));
        }

        return convertView;
    }

    static class ViewHolderItem {
        TextView tvName, tvNum, tvQuant;
        ImageView ivSteekez;
    }
}
