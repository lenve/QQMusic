package org.sang.qqmusic.showMusic.model;

import android.content.Context;
import android.content.SharedPreferences;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.util.MusicUtil;

/**
 * Created by 王松 on 2016/10/18.
 */

public class IShowDataImpl implements IShowData {
    @Override
    public void saveData(MusicBean musicBean, Context context) {
        SharedPreferences sp = context.getSharedPreferences(MusicUtil.MUSICBEAN, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("musicPath", musicBean.getUrl());
        edit.putString("musicName", musicBean.getSongname());
        edit.putInt("Seconds", musicBean.getSeconds());
        edit.putInt("id", musicBean.getSongid());
        edit.commit();
    }

    @Override
    public MusicBean loadData(Context context) {
        MusicBean bean = new MusicBean();
        SharedPreferences sp = context.getSharedPreferences(MusicUtil.MUSICBEAN, Context.MODE_PRIVATE);
        bean.setSongname(sp.getString("musicName", ""));
        bean.setUrl(sp.getString("musicPath", ""));
        bean.setSongid(sp.getInt("id", 0));
        bean.setSeconds(sp.getInt("Seconds", 0));
        return bean;
    }
}
