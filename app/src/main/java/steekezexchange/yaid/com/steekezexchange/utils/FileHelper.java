package steekezexchange.yaid.com.steekezexchange.utils;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;
import steekezexchange.yaid.com.steekezexchange.ui.MainActivity;

/**
 * Created by Игорь on 15.05.2015.
 */
public class FileHelper {

    private static final String DIRNAME = "SteekezExchangeData";
    public static final String MY_COLLECTION = "_myCollection";

    static public File getStorageDir(Context ctx){

        //boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);

        File f = ctx.getDir(DIRNAME,Context.MODE_PRIVATE);
/*
        File f = new File(Environment.getExternalStorageDirectory() + DIRNAME);
        if(f.isDirectory()) {

        }
*/
        return f;
    }

    private static final ArrayList<String> readAllRecords(Context ctx)
    {
        File f = getStorageDir(ctx);
        File[] files = f.listFiles();
        ArrayList<String> records = new ArrayList<String>(files.length);

        for (int i=0; i<files.length; i++)
        {
            records.add(readFile(files[i]));
        }

        return  records;
    }

    public static ArrayList<FriendItem> getFriendsList(Context ctx)
    {
        File f = getStorageDir(ctx);
        File[] files = f.listFiles();

        ArrayList<FriendItem> friendsList = new ArrayList<FriendItem>();

        for (int i=0; i<files.length; i++)
        {
            if (!files[i].getName().equalsIgnoreCase(MY_COLLECTION))
                friendsList.add(new FriendItem(files[i].getName()));
        }

        return friendsList;
    }

    private static final String readFile(File file)
    {
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                //text.append('\n');
            }
            br.close();
        }
        catch (IOException e) {
            //You'll need to add proper error handling here
        }

        return text.toString();
    }

    public static final String readMyCollectionFile(Context ctx)
    {
        File dir = getStorageDir(ctx);
        File myCollectionFile = new File(dir,MY_COLLECTION);

        if (myCollectionFile.exists())
            return readFile(myCollectionFile);
        else
            return null;
    }

    public static final boolean writeFile(Context ctx, String fileName, String dataToWrite)
    {
        File dir = getStorageDir(ctx);
        File fileToWrite = new File(dir,fileName);

        boolean result = true;
        try {
            FileOutputStream fout = new FileOutputStream(fileToWrite,false);
            fout.write(dataToWrite.getBytes());
            result = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            result = false;
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static final boolean writeMyCollection(Context ctx, String dataToWrite){
        return writeFile(ctx,MY_COLLECTION,dataToWrite);
    }

    public final static boolean deleteFile(Context ctx, String name)
    {
        File dir = getStorageDir(ctx);
        File fileToDel = new File(dir,name);
        return fileToDel.delete();
    }
}
