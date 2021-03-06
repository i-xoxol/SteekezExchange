package steekezexchange.yaid.com.steekezexchange.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import steekezexchange.yaid.com.steekezexchange.R;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public class ItemView_old extends View {

    boolean touchOn;
    boolean mDownTouch = false;

    private ImageView ivItem;
    private TextView tvName, tvQuant, tvNum;
    private int num,quantity;
    private String name;

    private int size = 0;

    public ItemView_old(Context context, int size, int margins, int image, String name, int num, int quantity, ViewGroup viewGroup) {
        super(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        //View view = inflater.inflate(R.layout.item_layout, null, false);
        inflate(context,R.layout.item_layout, null);
        this.size = size;
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = size;
        params.height = GridLayout.LayoutParams.WRAP_CONTENT;
        params.setMargins(margins, margins, 0, 0);
        this.setLayoutParams(params);

        this.num = num;
        this.name = name;
        this.quantity = quantity;

        tvName = (TextView)this.findViewById(R.id.tvItemName);
        tvQuant = (TextView)this.findViewById(R.id.tvQuantity);
        tvNum = (TextView)this.findViewById(R.id.tvNum);

        tvName.setText(name);
        if(quantity>1)
            tvQuant.setText("x"+Integer.toString(quantity));
        else
            tvQuant.setText("");
        tvNum.setText(Integer.toString(num));

        //ivItem.setImageResource(image);

    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        tvNum.setText(num);
    }

    public int getQuantity() {
        return quantity;

    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        if(quantity>1)
            tvQuant.setText("x"+quantity);
        else
            tvQuant.setText("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        tvNum.setText(num);
    }
}
