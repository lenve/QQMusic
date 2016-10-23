package org.sang.qqmusic.localMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;
import org.sang.qqmusic.localMusic.presenter.LocalMusicPresenter;
import org.sang.qqmusic.localMusic.view.adapter.LocalMusicLvAdapter;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.util.PlayUtil;

import java.util.List;

/**
 * Created by 王松 on 2016/10/18.
 */

public class LocalMusicFragment extends BaseFragment implements ILocalMusicView {
    private LocalMusicPresenter localMusicPresenter = new LocalMusicPresenter(this);
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.localmusic_fg_layout, container, false);
        initView(view);
        localMusicPresenter.start(getActivity());
        return view;
    }

    private void initView(View view) {
        lv = ((ListView) view.findViewById(R.id.lv));
    }

    @Override
    public void initLv(final List<MusicBean> list) {
        lv.setAdapter(new LocalMusicLvAdapter(list, getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PlayUtil.startService(getActivity(),list.get(position),PlayUtil.PLAY);
            }
        });
    }
}
