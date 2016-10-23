package org.sang.qqmusic.showMusic.model;

import android.content.Context;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

/**
 * Created by 王松 on 2016/10/18.
 */

public interface IShowData {
    void saveData(MusicBean musicBean, Context context);

    MusicBean loadData(Context context);
}
