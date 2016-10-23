package org.sang.qqmusic.showMusic.view.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.sang.qqmusic.showMusic.view.IShowView;
import org.sang.qqmusic.util.PlayUtil;

/**
 * Created by 王松 on 2016/10/18.
 */

public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (PlayUtil.STOPSERVICE_ACTION.equals(intent.getAction())) {
            ((IShowView) context).updateBottomPauseBtn();
        } else if (PlayUtil.UPDATE_BOTTOM_MUSIC_MSG_ACTION.equals(intent.getAction())) {
            ((IShowView) context).updateMainBottom(PlayUtil.currentMusic);
        }
    }
}
