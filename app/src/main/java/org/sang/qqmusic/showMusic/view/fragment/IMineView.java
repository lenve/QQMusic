package org.sang.qqmusic.showMusic.view.fragment;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.model.bean.UserBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public interface IMineView {
    void setUserInfo(UserBean userBean);

    void setPlayList(List<MusicBean> list);
}
