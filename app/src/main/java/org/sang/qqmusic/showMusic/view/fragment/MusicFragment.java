package org.sang.qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;
import org.sang.qqmusic.showMusic.view.adapter.MusicFgVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MusicFragment extends BaseFragment {
    private ViewPager viewpager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.music_fg_layout, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        viewpager = ((ViewPager) view.findViewById(R.id.viewpager));
        tabLayout = ((TabLayout) view.findViewById(R.id.tabLayout));
        String[] titles = new String[]{"精选","排行","歌单","电台","MV"};
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SelectFragment());
        fragments.add(new RankFragment());
        fragments.add(new PlayListFragment());
        fragments.add(new RadioStationFragment());
        fragments.add(new MVFragment());
        MusicFgVpAdapter adapter = new MusicFgVpAdapter(getChildFragmentManager(), fragments, titles);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
