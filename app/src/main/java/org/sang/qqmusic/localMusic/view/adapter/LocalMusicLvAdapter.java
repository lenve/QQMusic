package org.sang.qqmusic.localMusic.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.sang.qqmusic.R;
import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.util.List;

/**
 * Created by 王松 on 2016/10/18.
 */

public class LocalMusicLvAdapter extends BaseAdapter {
    private List<MusicBean> list;
    private Context context;
    private LayoutInflater inflater;

    public LocalMusicLvAdapter(List<MusicBean> list, Context context) {
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
            convertView = inflater.inflate(R.layout.localmusic_lv_item, parent, false);
            holder = new ViewHolder();
            holder.artist = (TextView) convertView.findViewById(R.id.artist);
            holder.musicName = (TextView) convertView.findViewById(R.id.music_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        MusicBean bean = list.get(position);
        holder.musicName.setText(bean.getSongname());
        holder.artist.setText(bean.getSingername());
        return convertView;
    }
    class ViewHolder{
        TextView musicName, artist;
    }
}
