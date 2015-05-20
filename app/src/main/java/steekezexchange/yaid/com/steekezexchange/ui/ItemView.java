package steekezexchange.yaid.com.steekezexchange.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
public class ItemView extends LinearLayout {

    boolean touchOn;
    boolean mDownTouch = false;

    private ImageButton ibItem;
    private TextView tvName, tvQuant, tvNum;
    private int num,quantity;
    private String name;
    private SteekezItem item;
    private int imageResource, imageFrozenRes;

    private int size = 0;

    public ItemView(Context context) {
        super(context);
        init();
    }

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.item_layout, this);

        tvName = (TextView)findViewById(R.id.tvItemName);
        tvQuant = (TextView)findViewById(R.id.tvQuantity);
        tvNum = (TextView)findViewById(R.id.tvNum);
        ibItem = (ImageButton)findViewById(R.id.ivItem);
    }

    public ItemView (Context context, int size, int image, int imageFrozen, SteekezItem item)
    {
        this(context,size,0,image, imageFrozen, item.getName(),item.getId(),item.getQuantity());
        this.item = item;
    }

    public ItemView (Context context, int size, int margins, int image, int imageFrozen,String name, int num, int quantity) {
        super(context);

        inflate(getContext(), R.layout.item_layout, this);

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
        ibItem = (ImageButton)findViewById(R.id.ivItem);

        tvName.setText(name);
        if(quantity>1)
            tvQuant.setText("x"+Integer.toString(quantity));
        else
            tvQuant.setText("");
        tvNum.setText(Integer.toString(num));
        imageResource = image;
        imageFrozenRes = imageFrozen;
        if(quantity==0)
            ibItem.setImageResource(imageFrozen);
        else
            ibItem.setImageResource(image);

        ibItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemView.this.callOnClick();
            }
        });

        ibItem.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return ItemView.this.performLongClick();
                //return true;
            }
        });


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

        if(quantity==0)
            ibItem.setImageResource(imageFrozenRes);
        else
            ibItem.setImageResource(imageResource);

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

    public SteekezItem getItem() {
        return item;
    }

    public void setItem(SteekezItem item) {
        this.item = item;
    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
            ivItem.setImageResource(android.R.drawable.ic_delete);
        else if(event.getAction() == MotionEvent.ACTION_UP)
            ivItem.setImageResource(imageResource);
        return super.onTouchEvent(event);
    }
*/

}
