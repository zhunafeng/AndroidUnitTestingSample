package com.sample.ut.zhu.dagger.ui;


import com.sample.ut.zhu.dagger.base.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author nafeng zhu on 2018/2/5 09:46
 */

@Module
public abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract LoginDaggerActivity loginDaggerActivityInjector();

}
