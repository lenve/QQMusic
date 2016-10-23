package org.sang.qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;

/**
 * Created by 王松 on 2016/10/17.
 */

public class FindFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fg_layout, container, false);
        return view;
    }
}
