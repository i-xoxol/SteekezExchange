package steekezexchange.yaid.com.steekezexchange.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;

import steekezexchange.yaid.com.steekezexchange.entity.FriendItem;

/**
 * Created by Игорь on 22.05.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DB_NAME = "SteekezExchangeDB.db";
    public static final String COLLECTIONS_TABLE_NAME = "collections_table";
    public static final String USER_TABLE_NAME = "users_table";
    private static final int DB_VERSION = 1;

    public static final String USER_ID_COL = "userID";
    public static final String USER_NAME_COL = "userName";
    public static final String USER_EMAIL_COL = "userEmail";
    public static final String COLLECTION_CONT_ID_COL = "collectionContents";

    //TODO: check for unique record

    private static final String DATABASE_CREATE_SCRIPT_USERS = "create table "
            + USER_TABLE_NAME + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + USER_ID_COL + " text not null, "
            + USER_NAME_COL + " text, "
            + USER_EMAIL_COL + " text);";

    private static final String DATABASE_CREATE_SCRIPT_COLLECTIONS = "create table "
            + COLLECTIONS_TABLE_NAME + " ("
            + BaseColumns._ID + " integer primary key autoincrement, "
            + USER_ID_COL + " text not null, "
            + COLLECTION_CONT_ID_COL + " text);";

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT_USERS);
        db.execSQL(DATABASE_CREATE_SCRIPT_COLLECTIONS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static void fillUsersTable(SQLiteDatabase sdb, List<FriendItem> users)
    {
        sdb.execSQL("delete from " + USER_TABLE_NAME);

        ContentValues newValues = null;
        FriendItem item = null;
        for (int i=0; i<users.size(); i++)
        {
            item = users.get(i);
            newValues = new ContentValues();
            newValues.put(USER_ID_COL,item.getEmail());
            newValues.put(USER_EMAIL_COL,item.getEmail());
            newValues.put(USER_NAME_COL,item.getEmail());

            sdb.insert(USER_TABLE_NAME,null,newValues);
        }
    }

    public static int deleteUserRecord(SQLiteDatabase sdb, String userID)
    {
        return sdb.delete(COLLECTIONS_TABLE_NAME, USER_ID_COL + " = " + userID, null);
    }

    public static String getCollection(SQLiteDatabase sdb, String userID)
    {
        String result;

        //Cursor cursor = sdb.query(COLLECTIONS_TABLE_NAME, new String[] {USER_ID_COL, COLLECTION_CONT_ID_COL}, )

        return result;
    }
}
