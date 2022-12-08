package com.sample.ut.zhu.dagger.base.module;

import android.content.Context;

import com.sample.ut.zhu.MyApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author nafeng Zhu
 * on 2018/2/5 15:20
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(MyApp mApplication) {
        return mApplication.getApplicationContext();
    }
}
