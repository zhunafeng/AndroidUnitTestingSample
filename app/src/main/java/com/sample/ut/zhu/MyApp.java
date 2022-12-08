package com.sample.ut.zhu;

import android.app.Application;

import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.sample.ut.zhu.net.GithubApi;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @author Nafeng Zhu on 2017/12/3.
 */

public class MyApp extends Application implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;
    private static MyApp instance;

    @Inject
    GithubApi mGithubApi;
    
    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }
        
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .build();
        XLog.init(config);
    }

    public static MyApp getInstance() {
        return instance;
    }

    public GithubApi getGithubApi(){
        return mGithubApi;
    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        return DaggerAppComponent.factory().create(this);
//    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }
}
