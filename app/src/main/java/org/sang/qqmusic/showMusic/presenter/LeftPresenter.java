package org.sang.qqmusic.showMusic.presenter;

import org.sang.qqmusic.BasePresenter;
import org.sang.qqmusic.showMusic.model.ILeftData;
import org.sang.qqmusic.showMusic.model.ILeftDataImpl;
import org.sang.qqmusic.showMusic.view.fragment.ILeftView;

/**
 * Created by 王松 on 2016/10/19.
 */

public class LeftPresenter implements BasePresenter {
    private ILeftView iLeftView;
    private ILeftData iLeftData;

    public LeftPresenter(ILeftView iLeftView) {
        this.iLeftView = iLeftView;
        iLeftData = new ILeftDataImpl();
    }

    @Override
    public void start() {
        iLeftView.initContainer(iLeftData.getCategory());
    }
}
