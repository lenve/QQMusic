package org.sang.qqmusic.showMusic.model;

import android.os.Environment;

import org.sang.qqmusic.showMusic.model.bean.MusicBean;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王松 on 2016/10/17.
 */

public class IMineDataImpl implements IMineData {

    //正常情况下在这里调用网络接口，获取我的歌单的数
    @Override
    public void getPlayList(final OnDataLoadListener onDataLoadListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<MusicBean> list = new ArrayList<>();
                File rootFile = new File(Environment.getExternalStorageDirectory(),"qqmusic"+File.separator+"song");
                getMp3(list, rootFile);
                if (list.size() > 3) {
                    //从List集合 中 截取前三项数据
                    list = list.subList(0, 3);
                }
                onDataLoadListener.onLoadSuccess(list);
            }
        }).start();
    }

    //获取本地所有的音频文件
    //1.去ContentProvider中查找
    //2.遍历SDCard，搜索出所有的mp3文件
    @Override
    public List<MusicBean> getLocalMusic() {
        final List<MusicBean> list = new ArrayList<>();
        File rootFile = new File(Environment.getExternalStorageDirectory(),"qqmusic"+File.separator+"song");
        getMp3(list, rootFile);
        return list;
    }

    private void getMp3(final List<MusicBean> list, File rootFile) {
        rootFile.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.isFile()) {
                    if (pathname.getName().endsWith(".mp3")) {
                        String musicName = pathname.getName();
                        String musicPath = pathname.getAbsolutePath();
                        MusicBean bean = new MusicBean();
                        bean.setSongname(musicName);
                        bean.setUrl(musicPath);
                        list.add(bean);
                        return true;
                    }
                } else {
                    getMp3(list, pathname);
                }
                return false;
            }
        });
    }
}
