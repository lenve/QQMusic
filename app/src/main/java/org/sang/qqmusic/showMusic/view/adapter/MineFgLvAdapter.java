package org.sang.qqmusic.showMusic.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.sang.qqmusic.R;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;
import org.sang.qqmusic.util.MusicUtil;

import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MineFgLvAdapter extends BaseAdapter {
    private List<MusicBean> list;
    private Context context;
    private LayoutInflater inflater;

    public MineFgLvAdapter(List<MusicBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mine_fg_lv_item, parent, false);
            holder = new ViewHolder();
            holder.musicName = (TextView) convertView.findViewById(R.id.music_name);
            holder.musicThumbnail = (ImageView) convertView.findViewById(R.id.music_thumbnail);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MusicBean musicBean = list.get(position);
        holder.musicName.setText(musicBean.getSongname());
        holder.musicName.setSelected(true);
        Bitmap bitmap = MusicUtil.getThumbnail(musicBean.getUrl());
        if (bitmap != null) {
            holder.musicThumbnail.setImageBitmap(bitmap);
        }else{
            holder.musicThumbnail.setImageResource(R.drawable.default1);
        }
        return convertView;
    }

    class ViewHolder {
        TextView musicName;
        ImageView musicThumbnail;
    }
}
