package org.sang.qqmusic.showMusic.presenter;

import android.os.Handler;

import org.sang.qqmusic.BasePresenter;
import org.sang.qqmusic.showMusic.model.IMineData;
import org.sang.qqmusic.showMusic.model.IMineDataImpl;
import org.sang.qqmusic.showMusic.model.OnDataLoadListener;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.view.fragment.IMineView;

import java.util.List;

/**
 * 该Presenter对应MineFragment
 */

public class MinePresenter implements BasePresenter {
    private IMineData iMineData;
    private IMineView iMineView;
    private Handler mHandler = new Handler();

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
        iMineData = new IMineDataImpl();
    }

    @Override
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                iMineData.getPlayList(new OnDataLoadListener() {
                    @Override
                    public void onLoadSuccess(final List<MusicBean> list) {
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                iMineView.setPlayList(list);

                            }
                        });
                    }

                    @Override
                    public void onLoadFailed(String errorMsg) {

                    }
                });
            }
        }).start();
    }
}
