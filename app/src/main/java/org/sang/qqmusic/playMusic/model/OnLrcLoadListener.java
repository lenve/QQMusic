package org.sang.qqmusic.playMusic.model;

/**
 * Created by 王松 on 2016/10/20.
 */

public interface OnLrcLoadListener {
    void onLoadSuccess(String lrcStr);

    void onLoadFailed(String msg);
}
