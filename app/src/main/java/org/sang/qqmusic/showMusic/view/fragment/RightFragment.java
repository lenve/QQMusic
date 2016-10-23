package org.sang.qqmusic.showMusic.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;

import org.sang.qqmusic.App;
import org.sang.qqmusic.BR;
import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.presenter.RightPresenter;
import org.sang.qqmusic.showMusic.view.adapter.RvAdapter;
import org.sang.qqmusic.util.BitmapCache;
import org.sang.qqmusic.util.PlayUtil;

import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class RightFragment extends BaseFragment implements IRightView {
    private RecyclerView recyclerView;
    private RightPresenter rightPresenter = new RightPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.right_fg_layout, container, false);
        initView(view);
        rightPresenter.start(3);
        return view;
    }

    private void initView(View view) {
        recyclerView = ((RecyclerView) view.findViewById(R.id.recyclerView));
    }

    @Override
    public void initRv(final List<MusicBean> list) {
        MusicBean.loader = new ImageLoader(((App) getActivity().getApplication()).getRequestQueue(), new BitmapCache(getActivity()));
        RvAdapter<MusicBean> adapter = new RvAdapter<>(getActivity(), list, R.layout.right_fg_rv_item, BR.music);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter.setOnItemClickListener(new RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PlayUtil.startService(getActivity(),list.get(position),PlayUtil.PLAY);
                PlayUtil.CURRENT_PLAY_LIST = list;
                PlayUtil.CURRENT_POSITION = position;
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showErrorMsg(String errorMsg) {

    }

    public void updateRv(int id) {
        rightPresenter.start(id);
    }
}
