package co.iyubinest.myapplication;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by cristiangomez on 18/11/15.
 */
public class DemoContentProvider extends ContentProvider
{

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE;

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE;

    public static final int POSITIONS = 100;

    public static final int POSITION_ID = 200;

    private static final String AUTHORITY = "co.iyubinest.myapplication.DemoContentProvider";

    private static final String POSITIONS_BASE_PATH = "positions";

    public static final Uri CONTENT_URI =
        Uri.parse("content://" + AUTHORITY + "/" + POSITIONS_BASE_PATH);

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static
    {
        URI_MATCHER.addURI(AUTHORITY, POSITIONS_BASE_PATH, POSITIONS);
        URI_MATCHER.addURI(AUTHORITY, POSITIONS_BASE_PATH + "/#", POSITION_ID);
    }

    private DemoDatabase mDemoDatabase;

    @Override
    public boolean onCreate()
    {

        mDemoDatabase = new DemoDatabase(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
        String sortOrder)
    {

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(DemoDatabase.TABLE_HISTORY);
        int uriType = URI_MATCHER.match(uri);
        switch (uriType)
        {
            case POSITIONS:
                break;
            case POSITION_ID:
                sqLiteQueryBuilder.appendWhere(
                    DemoDatabase.TABLE_HISTORY_COL_ID + "=" + uri.getLastPathSegment());
                break;
        }
        Cursor cursor =
            sqLiteQueryBuilder.query(mDemoDatabase.getReadableDatabase(), projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {

        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues)
    {

        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings)
    {

        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings)
    {

        return 0;
    }
}
