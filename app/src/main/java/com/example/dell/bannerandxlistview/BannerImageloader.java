package com.example.dell.bannerandxlistview;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by 胡计强 on 2017/06/28.
 */
public class BannerImageloader extends com.youth.banner.loader.ImageLoader {

    private final ImageLoader imageLoader;
    private final DisplayImageOptions options;

    public BannerImageloader(Context context) {
        File file = StorageUtils.getOwnCacheDirectory(context, "hujiqiang");
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context).diskCache(new UnlimitedDiskCache(file)).build();
        //将configuration配置到imageloader中
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(configuration);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中
                .bitmapConfig(Bitmap.Config.ARGB_8888)//设置图片的解码类型
                .showImageOnLoading(R.mipmap.ic_launcher)//设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.ic_launcher)//设置图片加载/解码过程中错误时候显示的图片
                .build();
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageLoader.displayImage((String) path, imageView, options);
    }
}
