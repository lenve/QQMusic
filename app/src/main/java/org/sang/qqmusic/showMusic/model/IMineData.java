package org.sang.qqmusic.showMusic.model;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public interface IMineData {

    void getPlayList(OnDataLoadListener onDataLoadListener);

    List<MusicBean> getLocalMusic();
}

