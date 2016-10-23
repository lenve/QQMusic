package org.sang.qqmusic.localMusic.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.sang.qqmusic.R;
import org.sang.qqmusic.localMusic.view.adapter.LocalVpAdapter;
import org.sang.qqmusic.localMusic.view.fragment.LocalArtistFragment;
import org.sang.qqmusic.localMusic.view.fragment.LocalMusicFragment;

import java.util.ArrayList;
import java.util.List;

public class LocalMusicActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_local_music);
        initView();
    }

    private void initView() {
        viewpager = ((ViewPager) findViewById(R.id.viewpager));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LocalMusicFragment());
        fragments.add(new LocalArtistFragment());
        String[] titles = new String[]{"歌曲", "歌手"};
        viewpager.setAdapter(new LocalVpAdapter(getSupportFragmentManager(), fragments, titles));
        tabLayout.setupWithViewPager(viewpager);
    }
}
