package org.sang.qqmusic.showMusic.view;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

/**
 * Created by 王松 on 2016/10/17.
 */

public interface IShowView {
    void updateMainBottom(MusicBean musicBean);

    void updateBottomPauseBtn();
}
