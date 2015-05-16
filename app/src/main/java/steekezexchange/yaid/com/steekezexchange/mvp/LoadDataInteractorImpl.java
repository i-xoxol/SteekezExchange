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
public class LoadDataInteractorImpl implements LoadDataInteractor{
    private Context ctx;

    public LoadDataInteractorImpl(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public void loadItemsFromInternalStorage(final DataFinishedListener listener) {

       Thread loadThread = new Thread(new Runnable() {
           @Override
           public void run() {
               final ArrayList<SteekezItem> myColletion = loadCollection();
/*
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
*/
               final ArrayList<FriendItem> friendsList = FileHelper.getFriendsList(ctx);

               ((Activity) ctx).runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       listener.onFinished(FileHelper.MY_COLLECTION, myColletion, friendsList);
                   }
               });
           }
       });

        loadThread.start();

    }

    private ArrayList<SteekezItem> loadCollection()
    {
        String myData = FileHelper.readMyCollectionFile(ctx);
        ArrayList<SteekezItem> steekezArray = null;

        if (myData==null || myData.length()==0)
        {
            String[] names = ctx.getResources().getStringArray(R.array.steekez_name);
            steekezArray = new ArrayList<SteekezItem>();

            for (int i=0; i<names.length; i++)
                steekezArray.add(new SteekezItem(i+1,1,names[i]));
        }
        else
            steekezArray = Parser.parseFile(myData);

        return steekezArray;
    }
}
