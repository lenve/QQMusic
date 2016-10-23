package org.sang.qqmusic.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;

/**
 * Created by 王松 on 2016/10/17.
 */

public class MusicUtil {

    public static final String MUSICBEAN = "musicbean";

    /**
     * 获取一个mp3文件的专辑图片
     *
     * @param filePath
     * @return
     */
    public static Bitmap getThumbnail(String filePath) {
        if (filePath == null || "".equals(filePath) || filePath.startsWith("http")) {
            return null;
        }
        Bitmap bitmap = null;
        MediaMetadataRetriever retriever = null;
        try {
            retriever = new MediaMetadataRetriever();
            retriever.setDataSource(filePath);
            byte[] bytes = retriever.getEmbeddedPicture();
            if (bytes != null && bytes.length > 0) {
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//            }else{
//                bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.default1);
            }
        } finally {
            if (retriever != null) {
                retriever.release();
            }
        }
        return bitmap;
    }
}
