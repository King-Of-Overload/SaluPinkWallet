package com.zjut.alan.pinkwallet;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zjut.alan.pinkwallet.constants.WalletConstant;
import com.zjut.alan.saluandroid.tools.data.SaluPreferenceTool;

import java.io.File;

public class PinkWalletApp extends Application {
    public static PinkWalletApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
        SaluPreferenceTool.putString(WalletConstant.PREFERENCE_CELLPHONE, "");
        SaluPreferenceTool.putString(WalletConstant.PREFERENCE_PASSWORD, "");
        SaluPreferenceTool.putBoolean(WalletConstant.PREFERENCE_FIRST_LOGIN, true);
    }


    private void init(){
        File cacheDir = StorageUtils.getIndividualCacheDirectory(this);
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);//总内存
        ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(this)
                .writeDebugLogs()
                .memoryCache(new LruMemoryCache(maxMemory / 6))//可以通过自己的内存缓存实现
                .memoryCacheSize(4 * 1024 * 1024)//内存缓存的最大值
                .memoryCacheExtraOptions(480, 800)
                .memoryCacheSizePercentage(13)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                .build();
        ImageLoader.getInstance().init(imageLoaderConfiguration);//初始化图片加载
    }
}
