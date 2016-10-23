package org.sang.qqmusic.showMusic.presenter;

import android.os.Handler;

import org.sang.qqmusic.BasePresenter;
import org.sang.qqmusic.showMusic.model.IRightData;
import org.sang.qqmusic.showMusic.model.IRightDataImpl;
import org.sang.qqmusic.showMusic.model.OnDataLoadListener;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.view.fragment.IRightView;

import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class RightPresenter implements BasePresenter {
    private IRightView iRightView;
    private IRightData iRightData;
    private Handler mHandler = new Handler();

    public RightPresenter(IRightView iRightView) {
        this.iRightView = iRightView;
        iRightData = new IRightDataImpl();
    }

    public void start(int id) {
        iRightData.getPlayList(id, new OnDataLoadListener() {
            @Override
            public void onLoadSuccess(final List<MusicBean> list) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRightView.initRv(list);
                    }
                });
            }

            @Override
            public void onLoadFailed(final String errorMsg) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iRightView.showErrorMsg(errorMsg);
                    }
                });
            }
        });
    }


    @Override
    public void start() {

    }
}
