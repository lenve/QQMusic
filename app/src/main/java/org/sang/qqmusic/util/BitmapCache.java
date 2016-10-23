package org.sang.qqmusic.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by 王松 on 2016/10/10.
 */

public class BitmapCache implements ImageLoader.ImageCache {
    private Context context;
    private LruCache<String, Bitmap> lruCache;

    public BitmapCache(Context context) {
        this.context = context;
        long maxMemory = Runtime.getRuntime().maxMemory();
        long maxSize = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>((int) maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = lruCache.get(getUrl(url));
        if (bitmap == null) {
            bitmap = getBitmapFromSDCard(getUrl(url));
            if (bitmap != null) {
                putBitmap(url, bitmap);
            }
        }
        return bitmap;
    }

    private Bitmap getBitmapFromSDCard(String url) {
        return BitmapFactory.decodeFile(new File(context.getExternalCacheDir(), url).getAbsolutePath());
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(getUrl(url), bitmap);
        Bitmap bitmapFromSDCard = getBitmapFromSDCard(getUrl(url));
        if (bitmapFromSDCard == null) {
            saveBitmap2SDCard(getUrl(url), bitmap);
        }
    }

    private void saveBitmap2SDCard(String url, Bitmap bitmap) {
        try {
            FileOutputStream fos = new FileOutputStream(new File(context.getExternalCacheDir(), url));
            if (url.endsWith(".png") || url.endsWith(".PNG")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            }else{
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1, url.length());
    }
}
