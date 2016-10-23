package org.sang.qqmusic.showMusic.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sang.qqmusic.BaseFragment;
import org.sang.qqmusic.R;
import org.sang.qqmusic.showMusic.model.bean.Category;
import org.sang.qqmusic.showMusic.presenter.LeftPresenter;

import java.util.List;

/**
 * Created by 王松 on 2016/10/19.
 */

public class LeftFragment extends BaseFragment implements ILeftView{
    private LeftPresenter leftPresenter = new LeftPresenter(this);
    private LinearLayout container;
    private RightFragment rightFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.left_fg_layout, container, false);
        initVieww(view);
        leftPresenter.start();
        return view;
    }

    private void initVieww(View view) {
        this.container = ((LinearLayout) view.findViewById(R.id.container));
    }

    @Override
    public void initContainer(final List<Category> list) {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        for (int i = 0; i < list.size(); i++) {
            View view = inflater.inflate(R.layout.left_fg_item, container, false);
            TextView cateName = (TextView) view.findViewById(R.id.cate_name);
            cateName.setText(list.get(i).getCategory());
            container.addView(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateContainer(finalI);
                    rightFragment.updateRv(list.get(finalI).getId());
                }
            });
        }
        View view = container.getChildAt(0);
        TextView cateName = (TextView) view.findViewById(R.id.cate_name);
        View cateLine = view.findViewById(R.id.cate_line);
        cateName.setTextColor(getResources().getColor(R.color.colorPrimary));
        cateLine.setVisibility(View.VISIBLE);
    }

    public void setRightFragment(RightFragment rightFragment) {
        this.rightFragment = rightFragment;
    }

    private void updateContainer(int finalI) {
        int count = container.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = container.getChildAt(i);
            TextView cateName = (TextView) view.findViewById(R.id.cate_name);
            View cateLine = view.findViewById(R.id.cate_line);
            if (i == finalI) {
                cateName.setTextColor(getResources().getColor(R.color.colorPrimary));
                cateLine.setVisibility(View.VISIBLE);
            }else{
                cateName.setTextColor(Color.BLACK);
                cateLine.setVisibility(View.GONE);
            }
        }
    }
}
