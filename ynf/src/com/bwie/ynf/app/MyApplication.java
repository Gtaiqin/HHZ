package com.bwie.ynf.app;

import com.bwie.ynf.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true)
				.showImageOnLoading(R.drawable.chat_photo_album).showImageOnFail(R.drawable.chat_photo_album).build();
		int maxSize = (int) (Runtime.getRuntime().maxMemory() / 8);
		@SuppressWarnings("deprecation")
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext())
				.discCache(new UnlimitedDiskCache(getCacheDir())).memoryCache(new UsingFreqLimitedMemoryCache(maxSize))
				.threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 1).defaultDisplayImageOptions(options).build();
		ImageLoader.getInstance().init(configuration);
	}
}
