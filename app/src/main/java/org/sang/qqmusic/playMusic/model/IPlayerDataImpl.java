package org.sang.qqmusic.playMusic.model;

import android.util.Log;

import org.sang.qqmusic.util.JsonParse;
import org.sang.qqmusic.util.NetUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 王松 on 2016/10/20.
 */

public class IPlayerDataImpl implements IPlayerData {
    @Override
    public void loadLrc(int id, final OnLrcLoadListener onLrcLoadListener) {
        String url = String.format(NetUtil.LRCDOWNLOADURL, id);
        Log.d("google.sang", "loadLrc: "+url);
        Request request = new Request.Builder().url(url).build();
        NetUtil.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onLrcLoadListener.onLoadFailed(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    onLrcLoadListener.onLoadSuccess(JsonParse.parseLrc2List(response.body().string()));
                }
            }
        });
    }
}
