package org.sang.qqmusic.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.RemoteViews;

import org.sang.qqmusic.R;
import org.sang.qqmusic.util.NetUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王松 on 2016/10/21.
 */

public class DownLoadService extends IntentService {

    private NotificationManager nfManager;
    private RemoteViews remoteViews;
    private Notification.Builder builder;

    public DownLoadService() {
        super("");
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        remoteViews = new RemoteViews(getPackageName(), R.layout.download_nf_layout);
        builder = new Notification.Builder(this);
        builder
//                .setContent(remoteViews)
                .setContentTitle("正在下载...")
                .setContentInfo("已下载:")
                .setSmallIcon(R.drawable.download_icon);
        nfManager = ((NotificationManager) getSystemService(NOTIFICATION_SERVICE));
        nfManager.notify(2,builder.build());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String downloadurl = intent.getStringExtra("downloadurl");
        Log.d("google.sang", "onHandleIntent: "+downloadurl);
        final String songid = intent.getStringExtra("songid");
        Log.d("google.sang", "onHandleIntent: "+songid);
        Request request = new Request.Builder().url(downloadurl).build();
        NetUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        File myMusic = new File(Environment.getExternalStorageDirectory(), "MyMusic");
                        if (!myMusic.exists()) {
                            myMusic.mkdirs();
                        }
                        FileOutputStream fos = new FileOutputStream(new File(myMusic, songid+".mp3"));
                        InputStream is = response.body().byteStream();
                        byte[] buf = new byte[1024];
                        int len = 0;
                        int currentLength = 0;
                        int totalLength = (int) response.body().contentLength();
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            fos.flush();

                            currentLength += len;
                            builder.setProgress(totalLength, currentLength, false);
                            builder.setContentInfo("已下载:" + (int) ((currentLength * 1.0f / totalLength) * 100) + "%");
//                            remoteViews.setProgressBar(R.id.pb, totalLength, currentLength, false);
//                            remoteViews.setTextViewText(R.id.current_percent, (int) ((currentLength * 1.0f / totalLength) * 100) + "%");
                            nfManager.notify(2,builder.build());
                        }
                    }

                }
            }
        });
    }
}
