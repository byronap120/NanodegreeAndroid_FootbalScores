package barqsoft.footballscores.Widget;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import java.util.ArrayList;
import java.util.List;

import barqsoft.footballscores.DatabaseContract;
import barqsoft.footballscores.R;

/**
 * Created by Byron on 2/14/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WidgetRemoteViewsFactory implements
        RemoteViewsFactory {

    public static final int COL_HOME = 3;
    public static final int COL_AWAY = 4;
    public static final int COL_HOME_GOALS = 6;
    public static final int COL_AWAY_GOALS = 7;

    Cursor cursor;
    Context mContext = null;
    ContentResolver contentResolver;

    public WidgetRemoteViewsFactory(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        cursor.moveToPosition(position);
        RemoteViews mView = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_list_item);

        mView.setTextViewText(R.id.homeTextView,cursor.getString(COL_HOME));
        mView.setTextViewText(R.id.awayTextView,cursor.getString(COL_AWAY));
        mView.setTextViewText(R.id.homeScoretextView,cursor.getString(COL_HOME_GOALS));
        mView.setTextViewText(R.id.awayScoretextView,cursor.getString(COL_AWAY_GOALS));
        return mView;
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }@Override
     public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
        contentResolver = mContext.getContentResolver();
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    private void initData() {
 //       mCollections.clear();
/*        for (int i = 1; i <= 2; i++) {
            mCollections.add("ListView item " + i);
        }*/

        String[] date = {"2016-02-14"};
        cursor = contentResolver.query (DatabaseContract.scores_table.buildScoreWithDate() , null, null,date, null);
/*        while (cursor.moveToNext()) {

            String displayName = cursor.getString(cursor
                    .getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));

            String displayNameHome = cursor.getString(cursor
                    .getColumnIndex(DatabaseContract.scores_table.HOME_COL));

            String displayNameVisit = cursor.getString(cursor
                    .getColumnIndex(DatabaseContract.scores_table.HOME_GOALS_COL));

            String temp = displayNameHome + " - " + displayNameVisit;
            Log.d("sdf","sadf" + displayName);
            mCollections.add(temp);
        }*/
    }

    @Override
    public void onDestroy() {

    }

}
