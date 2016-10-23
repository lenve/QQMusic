package org.sang.qqmusic.showMusic.model;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public interface OnDataLoadListener {
    void onLoadSuccess(List<MusicBean> list);

    void onLoadFailed(String errorMsg);
}
