package org.sang.qqmusic.playMusic.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.sang.qqmusic.BaseActivity;
import org.sang.qqmusic.R;
import org.sang.qqmusic.playMusic.presenter.PlayerPresenter;
import org.sang.qqmusic.service.DownLoadService;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.util.PlayUtil;
import org.sang.lrcview.LrcView;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.sharesdk.onekeyshare.OnekeyShare;

public class PlayerActivity extends BaseActivity implements IPlayerView {

    private ImageView playOrPause;
    private TextView currentTimeTv;
    private TextView totalTimeTv;
    private SeekBar seekBar;
    private PlayerPresenter playerPresenter = new PlayerPresenter(this);
    private LrcView lrcView;
    private TextView toolbarMusicName;
    private TextView bottomMusicName;
    private TextView singerName;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (PlayUtil.CURRENT_STATE == PlayUtil.PLAY) {
                currentTimeTv.setText(dateFormat.format(new Date(PlayUtil.player.getCurrentPosition())));
                seekBar.setProgress(PlayUtil.player.getCurrentPosition());
                mHandler.sendEmptyMessageDelayed(0, 200);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        initView();
        playerPresenter.start();
    }

    public void share(View view) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("好歌就在MyQQMusic");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(PlayUtil.currentMusic.getUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText("听好歌--"+PlayUtil.currentMusic.getSongname());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(PlayUtil.currentMusic.getAlbumpic_big());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(PlayUtil.currentMusic.getUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(PlayUtil.currentMusic.getSongname());
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("MyQQMusic");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(PlayUtil.currentMusic.getUrl());

// 启动分享GUI
        oks.show(this);
    }

    private void initView() {
        LinearLayout leftMenu = (LinearLayout) findViewById(R.id.left_menu);
        leftMenu.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels;
        leftMenu.requestLayout();
        LinearLayout rightMenu = (LinearLayout) findViewById(R.id.right_menu);
        rightMenu.getLayoutParams().width = getResources().getDisplayMetrics().widthPixels;
        rightMenu.requestLayout();
        playOrPause = ((ImageView) findViewById(R.id.play_or_pause));
        currentTimeTv = ((TextView) findViewById(R.id.current_time));
        totalTimeTv = (TextView) findViewById(R.id.total_time);
        seekBar = ((SeekBar) findViewById(R.id.seek_bar));
        lrcView = ((LrcView) findViewById(R.id.lrc_view));
        toolbarMusicName = ((TextView) findViewById(R.id.music_name));
        bottomMusicName = ((TextView) findViewById(R.id.music_name2));
        singerName = ((TextView) findViewById(R.id.singerName));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    PlayUtil.player.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                PlayUtil.player.pause();
                PlayUtil.CURRENT_STATE = PlayUtil.PAUSE;
                playerPresenter.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                PlayUtil.pause();
                playerPresenter.start();
            }
        });
    }

    @Override
    public void updatePlayerControl() {
        if (PlayUtil.CURRENT_STATE == PlayUtil.PLAY) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
        } else {
            playOrPause.setImageResource(R.drawable.ring_btnplay);
        }
        MusicBean currentMusic = PlayUtil.currentMusic;
        toolbarMusicName.setText(currentMusic.getSongname());
        bottomMusicName.setText(currentMusic.getSongname());
        singerName.setText(currentMusic.getSingername());
        playerPresenter.loadLrc(PlayUtil.currentMusic.getSongid());
        mHandler.sendEmptyMessage(0);
        totalTimeTv.setText(dateFormat.format(new Date(PlayUtil.currentMusic.getSeconds() * 1000)));
        seekBar.setMax(PlayUtil.currentMusic.getSeconds() * 1000);
    }

    @Override
    public void initLrcView(String lrcStr) {
        lrcView.setLrc(lrcStr);
        lrcView.setPlayer(PlayUtil.player);
        lrcView.init();
    }

    public void playOrPause(View view) {
        if (PlayUtil.CURRENT_STATE == PlayUtil.STOP) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PLAY);
        } else if (PlayUtil.CURRENT_STATE == PlayUtil.PAUSE) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PAUSE);
        } else {
            playOrPause.setImageResource(R.drawable.ring_btnplay);
            PlayUtil.startService(this, PlayUtil.currentMusic, PlayUtil.PAUSE);
        }
    }

    public void preMusic(View view) {
        if (PlayUtil.CURRENT_POSITION > 0) {
            PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION - 1;
            PlayUtil.currentMusic = PlayUtil.CURRENT_PLAY_LIST.get(PlayUtil.CURRENT_POSITION);
            PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
            playerPresenter.start();
        }
    }

    public void nextMusic(View view) {
        if (PlayUtil.CURRENT_POSITION < PlayUtil.CURRENT_PLAY_LIST.size() - 1) {
            PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION + 1;
            PlayUtil.currentMusic = PlayUtil.CURRENT_PLAY_LIST.get(PlayUtil.CURRENT_POSITION);
            PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
            playerPresenter.start();
        }
    }

    public void downloadMusic(View view) {
        Intent intent = new Intent(this, DownLoadService.class);
        intent.putExtra("downloadurl", PlayUtil.currentMusic.getDownUrl());
        intent.putExtra("songid", PlayUtil.currentMusic.getSongid()+"");
        startService(intent);
    }
}
