package com.hhzmy.applocation;

import android.app.Application;

import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {
    {
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3465861582", "d7079bea3deb7ba6da553eefd9343a1d");
        PlatformConfig.setQQZone("1105874430", "c7394704798a158208a74ab60104f0ba");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //友盟SDK初始化
        UMShareAPI.get(this);
        Config.REDIRECT_URL = "http://sns.whalecloud.com";
        //MOB短信SDK初始化
        // SMSSDK.initSDK(this, "19ba81bb93830", "7352424464c0bbeeb14c73278018b90d");

        JPushInterface.setDebugMode(true);    // 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);            // 初始化 JPush

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
                .showImageOnLoading(R.mipmap.logistic_empty).showImageOnFail(R.mipmap.logistic_empty).build();
        int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
        @SuppressWarnings("deprecation")
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .discCache(new UnlimitedDiskCache(getCacheDir())).memoryCache(new UsingFreqLimitedMemoryCache(maxSize))
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1).defaultDisplayImageOptions(options).build();
        ImageLoader.getInstance().init(configuration);

    }
}
