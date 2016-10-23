package org.sang.qqmusic;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by 王松 on 2016/10/19.
 */

public class App extends Application {
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        ShareSDK.initSDK(this);
        requestQueue = Volley.newRequestQueue(this);
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
}
