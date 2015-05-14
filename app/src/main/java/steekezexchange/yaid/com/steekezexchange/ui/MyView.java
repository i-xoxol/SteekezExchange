package steekezexchange.yaid.com.steekezexchange.ui;

/**
 * Created by ikhokhlov on 5/14/2015.
 */
import android.content.Context;
import android.view.View;
import android.widget.GridLayout;

public class MyView extends View {

    boolean touchOn;
    boolean mDownTouch = false;

    private int size = 0;

    public MyView(Context context, int size, int margins, int color) {
        super(context);
        this.size = size;
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = size;
        params.height = size;
        params.setMargins(margins, margins, 0, 0);
        this.setLayoutParams(params);
        this.setBackgroundColor(color);
    }
}