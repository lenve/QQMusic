package org.sang.qqmusic.showMusic.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;
import org.sang.qqmusic.localMusic.view.LocalMusicActivity;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.showMusic.model.bean.UserBean;
import org.sang.qqmusic.showMusic.presenter.MinePresenter;
import org.sang.qqmusic.showMusic.view.IShowView;
import org.sang.qqmusic.showMusic.view.adapter.MineFgLvAdapter;
import org.sang.qqmusic.showMusic.view.view.MyListView;
import org.sang.qqmusic.util.PlayUtil;

import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MineFragment extends BaseFragment implements IMineView {
    private MinePresenter minePresenter = new MinePresenter(this);
    private MyListView lv;
    private IShowView iShowView;
    private Handler mHandler = new Handler();
    private TextView playListSizeTv;
    private LinearLayout localMusic;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IShowView) {
            iShowView = (IShowView) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mine_fg_layout, container, false);
        initView(view);
        minePresenter.start();
        return view;
    }

    private void initView(View view) {
        lv = ((MyListView) view.findViewById(R.id.play_list_lv));
        playListSizeTv = ((TextView) view.findViewById(R.id.play_list_size_tv));
        localMusic = ((LinearLayout) view.findViewById(R.id.localMusic));
        localMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LocalMusicActivity.class));
            }
        });
    }

    @Override
    public void setUserInfo(UserBean userBean) {

    }

    @Override
    public void setPlayList(final List<MusicBean> list) {
        playListSizeTv.setText(list.size()+"");
        lv.setAdapter(new MineFgLvAdapter(list, getActivity()));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                PlayUtil.startService(getActivity(), list.get(position), PlayUtil.PLAY);
                PlayUtil.CURRENT_POSITION = position;
                PlayUtil.CURRENT_PLAY_LIST = list;
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        iShowView.updateMainBottom(list.get(position));
//                    }
//                }, 500);
            }
        });
    }
}
