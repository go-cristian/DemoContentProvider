package co.iyubinest.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cristiangomez on 18/11/15.
 */
public class DemoDatabase extends SQLiteOpenHelper
{

    public static final String TABLE_HISTORY = "history";

    public static final String TABLE_HISTORY_COL_ID = "_id";

    public static final String TABLE_HISTORY_COL_POSITION = "position";

    private static final String DB_NAME = "demo_db";

    private static final int DB_VERSION = 2;

    private static final String CREATE_TABLE_HISTORY = "CREATE TABLE "
        + TABLE_HISTORY
        + " ( "
        + TABLE_HISTORY_COL_ID
        + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + TABLE_HISTORY_COL_POSITION
        + " TEXT NOT NULL);";

    private static final String DB_SCHEMA = CREATE_TABLE_HISTORY;

    private static final String DB_SCHEMA_DELETE = "DROP TABLE IF EXISTS " + TABLE_HISTORY;

    public DemoDatabase(Context context)
    {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {

        sqLiteDatabase.execSQL(DB_SCHEMA);
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_HISTORY_COL_POSITION, "74.2, 14");
        sqLiteDatabase.insert(TABLE_HISTORY, "", contentValues);
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(TABLE_HISTORY_COL_POSITION, "74.2, 15");
        sqLiteDatabase.insert(TABLE_HISTORY, "", contentValues2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

        sqLiteDatabase.execSQL(DB_SCHEMA_DELETE);
        onCreate(sqLiteDatabase);
    }
}
