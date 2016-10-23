package org.sang.qqmusic.localMusic.model;

import android.content.Context;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/18.
 */

public interface ILocalData {
    List<MusicBean> getLocalMusic(Context context);
}
