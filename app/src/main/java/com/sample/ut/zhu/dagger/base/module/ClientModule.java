package com.sample.ut.zhu.dagger.base.module;


import com.sample.ut.zhu.net.GithubApi;
import com.sample.ut.zhu.net.LoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Nafeng Zhu
 * on 2018/2/5 15:22
 */

@Module
public class ClientModule {

    @Singleton
    @Provides
    GithubApi provideGithubApi(Retrofit retrofit) {
        return retrofit.create(GithubApi.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(HttpUrl.parse(GithubApi.BASE_URL))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder okHttpClient, LoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = okHttpClient.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    /**
     * log info interceptor
     * @return interceptor
     */
    @Singleton
    @Provides
    LoggingInterceptor provideLoggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

}
