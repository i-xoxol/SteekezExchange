package steekezexchange.yaid.com.steekezexchange.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Игорь on 22.05.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final String DB_NAME = "SteekezExchangeDB.db";
    public static final String COLLECTIONS_TABLE_NAME = "collections_table";
    private static final int DB_VERSION = 1;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
