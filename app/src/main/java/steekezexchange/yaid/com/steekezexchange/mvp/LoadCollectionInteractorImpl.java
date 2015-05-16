package steekezexchange.yaid.com.steekezexchange.mvp;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import steekezexchange.yaid.com.steekezexchange.R;
import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.entity.SteekezItem;
import steekezexchange.yaid.com.steekezexchange.utils.FileHelper;
import steekezexchange.yaid.com.steekezexchange.utils.Parser;

/**
 * Created by Игорь on 16.05.2015.
 */
public class LoadCollectionInteractorImpl implements LoadCollectionInteractor{

    private Context ctx;
    private String ownerName;

    public LoadCollectionInteractorImpl(Context ctx, String ownerName) {
        this.ctx = ctx;
        this.ownerName = ownerName;
    }

    @Override
    public void loadItemsFromInternalStorage(String name, final CollectionFinishedListener listener) {
        Thread loadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                final ArrayList<SteekezItem> friendColletion = loadCollection(ownerName);

                ((Activity) ctx).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listener.onFinished(friendColletion);
                    }
                });
            }
        });

        loadThread.start();
    }

    private ArrayList<SteekezItem> loadCollection(String name)
    {
        String myData = FileHelper.readCollectionFile(ctx,name);
        ArrayList<SteekezItem> steekezArray = null;

        if (myData==null || myData.length()==0)
        {
            String[] names = ctx.getResources().getStringArray(R.array.steekez_name);
            steekezArray = new ArrayList<SteekezItem>();

            for (int i=0; i<names.length; i++)
                steekezArray.add(new SteekezItem(i+1,0,names[i]));
        }
        else
            steekezArray = Parser.parseFile(myData);

        return steekezArray;
    }
}

