package com.hhzmy.applocation;

import android.app.Application;

import com.hhzmy.hhz.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMShareAPI.get(this);
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

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
