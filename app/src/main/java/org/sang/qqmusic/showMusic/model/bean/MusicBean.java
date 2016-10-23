package org.sang.qqmusic.showMusic.model.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;

import org.sang.qqmusic.R;

import java.io.Serializable;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MusicBean implements Serializable {

//    public void preMusic(View view) {
//        if (PlayUtil.CURRENT_POSITION > 0) {
//            PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION - 1;
//            List<MusicBean> currentPlayList = PlayUtil.CURRENT_PLAY_LIST;
//            PlayUtil.currentMusic = currentPlayList.get(PlayUtil.CURRENT_POSITION);
//            PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
//            updateMusicInfo();
//        }
//    }
//
//    private void updateMusicInfo() {
//        String songname = PlayUtil.currentMusic.getSongname();
//        setSongname(songname);
//        String singername = PlayUtil.currentMusic.getSingername();
//        setSingername(singername);
//        String albumpic_small = PlayUtil.currentMusic.getAlbumpic_small();
//        setAlbumpic_small(albumpic_small);
////        PlayUtil.currentMusic.setSongname(PlayUtil.currentMusic.getSongname());
////        PlayUtil.currentMusic.setSingername(PlayUtil.currentMusic.getSingername());
////        PlayUtil.currentMusic.setAlbumpic_small(PlayUtil.currentMusic.getAlbumpic_small());
//        Log.d("google.sang", "updateMusicInfo: " + songname);
//        Log.d("google.sang", "updateMusicInfo: " + singername);
//        Log.d("google.sang", "updateMusicInfo: " + albumpic_small);
//    }
//
//    public void nextMusic(View view) {
//        if (PlayUtil.CURRENT_POSITION < PlayUtil.CURRENT_PLAY_LIST.size() - 1) {
//            PlayUtil.CURRENT_POSITION = PlayUtil.CURRENT_POSITION + 1;
//            List<MusicBean> currentPlayList = PlayUtil.CURRENT_PLAY_LIST;
//            PlayUtil.currentMusic = currentPlayList.get(PlayUtil.CURRENT_POSITION);
//            List<MusicBean> currentPlayList2 = PlayUtil.CURRENT_PLAY_LIST;
//            PlayUtil.startService(view.getContext(), PlayUtil.currentMusic, PlayUtil.PLAY);
//            List<MusicBean> currentPlayList3 = PlayUtil.CURRENT_PLAY_LIST;
//            updateMusicInfo();
//            List<MusicBean> currentPlayList4 = PlayUtil.CURRENT_PLAY_LIST;
//        }
//    }

    /**
     * songname : 是你
     * seconds : 231
     * albummid : 000JN9VH0F2Lfk
     * songid : 107899426
     * singerid : 34412
     * albumpic_big : http://i.gtimg.cn/music/photo/mid_album_300/f/k/000JN9VH0F2Lfk.jpg
     * albumpic_small : http://i.gtimg.cn/music/photo/mid_album_90/f/k/000JN9VH0F2Lfk.jpg
     * downUrl : http://dl.stream.qqmusic.qq.com/107899426.mp3?vkey=09A63325B0A7D639CDDF4FDE91B8D65D180698AA9762497F49765B52664E1583C08FA1D3CC7D86DDDC3D62A73B46E1DAA55086AC8D948BB3&guid=2718671044
     * url : http://ws.stream.qqmusic.qq.com/107899426.m4a?fromtag=46
     * singername : TFBOYS
     * albumid : 1544479
     */

    private String songname;
    private int seconds;
    private String albummid;
    private int songid;
    private int singerid;
    private String albumpic_big;
    private String albumpic_small;
    private String downUrl;
    private String url;
    private String singername;
    private int albumid;
    public static ImageLoader loader;


//    public void onItemClick(View view) {
//        PlayUtil.startService(view.getContext(),this,PlayUtil.PLAY);
//    }

    @BindingAdapter("bind:albumpic_small")
    public static void getNetImage(ImageView iv, String url) {
        if (url == null || "".equals(url) || !url.startsWith("http")) {
            return;
        }
//        Picasso.with(iv.getContext()).load(url).resize(iv.getWidth(),iv.getHeight()).centerCrop().into(iv);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv, R.drawable.default1, R.drawable.default1);
        loader.get(url, listener);
    }

    public MusicBean() {
    }

    public MusicBean(String songname, int seconds, String albummid, int songid, int singerid, String albumpic_big, String albumpic_small, String downUrl, String url, String singername, int albumid) {
        this.songname = songname;
        this.seconds = seconds;
        this.albummid = albummid;
        this.songid = songid;
        this.singerid = singerid;
        this.albumpic_big = albumpic_big;
        this.albumpic_small = albumpic_small;
        this.downUrl = downUrl;
        this.url = url;
        this.singername = singername;
        this.albumid = albumid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getAlbummid() {
        return albummid;
    }

    public void setAlbummid(String albummid) {
        this.albummid = albummid;
    }

    public int getSongid() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid = songid;
    }

    public int getSingerid() {
        return singerid;
    }

    public void setSingerid(int singerid) {
        this.singerid = singerid;
    }

    public String getAlbumpic_big() {
        return albumpic_big;
    }

    public void setAlbumpic_big(String albumpic_big) {
        this.albumpic_big = albumpic_big;
    }

    public String getAlbumpic_small() {
        return albumpic_small;
    }

    public void setAlbumpic_small(String albumpic_small) {
        this.albumpic_small = albumpic_small;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public int getAlbumid() {
        return albumid;
    }

    public void setAlbumid(int albumid) {
        this.albumid = albumid;
    }
}
