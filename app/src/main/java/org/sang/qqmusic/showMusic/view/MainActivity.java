package org.sang.qqmusic.showMusic.view;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.lenve.customshapeimageview.CustomShapeImageView;
import org.sang.qqmusic.BaseActivity;
import org.sang.qqmusic.R;
import org.sang.qqmusic.playMusic.view.PlayerActivity;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.presenter.ShowPresenter;
import org.sang.qqmusic.showMusic.view.adapter.VpAdapter;
import org.sang.qqmusic.showMusic.view.fragment.FindFragment;
import org.sang.qqmusic.showMusic.view.fragment.MineFragment;
import org.sang.qqmusic.showMusic.view.fragment.MusicFragment;
import org.sang.qqmusic.showMusic.view.receiver.MusicReceiver;
import org.sang.qqmusic.util.MusicUtil;
import org.sang.qqmusic.util.PlayUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements IShowView {

    private CustomShapeImageView musicThumbnail;
    private TextView musicName;
    private ImageView playOrPause;
    private ShowPresenter showPresenter = new ShowPresenter(this);
    private MusicReceiver musicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(PlayUtil.STOPSERVICE_ACTION);
        filter.addAction(PlayUtil.UPDATE_BOTTOM_MUSIC_MSG_ACTION);
        musicReceiver = new MusicReceiver();
        registerReceiver(musicReceiver, filter);
        initView();
        showPresenter.loadData(PlayUtil.LOCALMUSICBEAN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showPresenter.loadData(PlayUtil.PLAYUTILMUSICBEAN);
    }

    private void initView() {
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList<>();
        list.add(new MineFragment());
        list.add(new MusicFragment());
        list.add(new FindFragment());
        viewpager.setAdapter(new VpAdapter(getSupportFragmentManager(), list));
        musicThumbnail = ((CustomShapeImageView) findViewById(R.id.music_thumbnail));
        musicName = ((TextView) findViewById(R.id.music_name));
        playOrPause = ((ImageView) findViewById(R.id.play_or_pause));
    }

    @Override
    public void updateMainBottom(MusicBean musicBean) {
        PlayUtil.currentMusic = musicBean;
        musicName.setText(musicBean.getSongname());
        Bitmap bitmap = MusicUtil.getThumbnail(musicBean.getUrl());
        if (bitmap != null) {
            musicThumbnail.setImageBitmap(bitmap);
        } else {
            musicThumbnail.setImageResource(R.drawable.default1);
        }
        updatePauseBtn();
    }

    private void updatePauseBtn() {
        if (PlayUtil.CURRENT_STATE == PlayUtil.PLAY) {
            playOrPause.setImageResource(R.drawable.search_stop_btn);
        } else {
            playOrPause.setImageResource(R.drawable.ring_btnplay);
        }
    }

    @Override
    public void updateBottomPauseBtn() {
        updatePauseBtn();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(musicReceiver);
        showPresenter.saveData(PlayUtil.currentMusic);
    }

    public void goPlayerActivity(View view) {
        Intent intent = new Intent(this, PlayerActivity.class);
        startActivity(intent);
    }
}
