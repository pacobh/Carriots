package fjbermudez.com.carriots.app;

import android.app.Application;
import android.content.Context;

public class AppVass extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getAppVassContext() {

        return mContext;
    }
}
