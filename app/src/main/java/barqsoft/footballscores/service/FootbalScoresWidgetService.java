package barqsoft.footballscores.service;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.widget.RemoteViewsService;

import barqsoft.footballscores.Widget.WidgetRemoteViewsFactory;

/**
 * Created by Byron on 2/14/2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FootbalScoresWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        WidgetRemoteViewsFactory dataProvider = new WidgetRemoteViewsFactory(
                getApplicationContext(), intent);
        return dataProvider;
    }

}