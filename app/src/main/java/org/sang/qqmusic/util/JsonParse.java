package org.sang.qqmusic.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class JsonParse {
    public static List<MusicBean> parseJson2List(String json) {
        List<MusicBean> list = new ArrayList<>();
        try {
            JSONArray songList = new JSONObject(json).getJSONObject("showapi_res_body").getJSONObject("pagebean").getJSONArray("songlist");
            for (int i = 0; i < songList.length(); i++) {
                JSONObject data = songList.getJSONObject(i);
                String songname = data.getString("songname");
                int seconds = data.getInt("seconds");
                String albummid = data.getString("albummid");
                String albumpic_big = data.getString("albumpic_big");
                String albumpic_small = data.getString("albumpic_small");
                String downUrl = data.getString("downUrl");
                String url = data.getString("url");
                String singername = data.getString("singername");
                int songid = data.getInt("songid");
                int singerid = data.getInt("singerid");
                int albumid = data.getInt("albumid");
                list.add(new MusicBean(songname, seconds, albummid, songid, singerid, albumpic_big, albumpic_small, downUrl, url, singername, albumid));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String parseLrc2List(String json) {
        String lrcText = null;
        try {
            lrcText = new JSONObject(json).getJSONObject("showapi_res_body")
                    .getString("lyric");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lrcText;
    }
}
