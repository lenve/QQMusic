package org.sang.qqmusic.playMusic.presenter;

import android.os.Handler;

import org.sang.qqmusic.BasePresenter;
import org.sang.qqmusic.playMusic.model.IPlayerData;
import org.sang.qqmusic.playMusic.model.IPlayerDataImpl;
import org.sang.qqmusic.playMusic.model.OnLrcLoadListener;
import org.sang.qqmusic.playMusic.view.IPlayerView;

/**
 * Created by 王松 on 2016/10/20.
 */

public class PlayerPresenter implements BasePresenter {
    private IPlayerView iPlayerView;
    private IPlayerData iPlayerData;
    private Handler mHandler = new Handler();

    public PlayerPresenter(IPlayerView iPlayerView) {
        this.iPlayerView = iPlayerView;
        iPlayerData = new IPlayerDataImpl();
    }

    @Override
    public void start() {
        iPlayerView.updatePlayerControl();
    }

    public void loadLrc(int id) {
        iPlayerData.loadLrc(id, new OnLrcLoadListener() {
            @Override
            public void onLoadSuccess(final String lrcStr) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        iPlayerView.initLrcView(lrcStr);
                    }
                });
            }

            @Override
            public void onLoadFailed(String msg) {

            }
        });
    }
}
