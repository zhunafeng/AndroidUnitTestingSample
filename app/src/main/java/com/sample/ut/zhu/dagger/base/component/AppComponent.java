package com.sample.ut.zhu.dagger.base.component;


import com.sample.ut.zhu.MyApp;
import com.sample.ut.zhu.dagger.base.module.AppModule;
import com.sample.ut.zhu.dagger.base.module.ClientModule;
import com.sample.ut.zhu.dagger.ui.BuildersModule;

import javax.inject.Singleton;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author Nafeng zhu on 2017/4/26 15:20
 */

@Singleton
@Subcomponent(modules = {
        AppModule.class,
        ClientModule.class,
        BuildersModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MyApp> {

    @Subcomponent.Factory
    public interface Factory extends AndroidInjector.Factory<MyApp> {}

}
