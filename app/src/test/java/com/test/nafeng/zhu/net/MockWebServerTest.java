package com.test.nafeng.zhu.net;

import android.util.Log;

import com.sample.ut.zhu.bean.User;
import com.sample.ut.zhu.net.GithubApi;
import com.sample.ut.zhu.net.LoggingInterceptor;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;

/**
 * Created by nafeng zhu on 2017/12/23.
 */

@RunWith(AndroidJUnit4.class)
public class MockWebServerTest {

    private GithubApi mockGithubService;
    private MockWebServer server;

    @Rule
    public RxJavaRule rule = new RxJavaRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;


        server = new MockWebServer();

        Dispatcher dispatcher = new Dispatcher() {

            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

                if (request.getPath().equals("/users/simplezhli")){
                    return new MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .addHeader("Cache-Control", "no-cache")
                            .setBody("{\"id\": 12456431, " +
                                    " \"name\": \"nafeng\"," +
                                    " \"blog\": \"http://blog.csdn.net/qq_17766\"}");
                } else {
                    return new MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .setResponseCode(404)
                            .throttleBody(5, 1, TimeUnit.SECONDS) //
                            .setBody("{\"error\": \"exception\"}");
                }
            }
        };

        server.setDispatcher(dispatcher);



        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + server.getHostName() + ":" + server.getPort() + "/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mockGithubService = retrofit.create(GithubApi.class);

    }

    @Test
    public void getUserTest() throws Exception {

        mockGithubService.getUser("nafeng")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(User user) {
                        assertEquals("nafeng", user.name);
                        assertEquals("http://blog.csdn.net/qq_17766", user.blog);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Test", e.toString());
                    }

                    @Override
                    public void onComplete() {
                    }
                });


        RecordedRequest request = server.takeRequest();
        assertEquals("GET /users/weilu HTTP/1.1", request.getRequestLine());
        assertEquals("okhttp/3.12.0", request.getHeader("User-Agent"));


        server.shutdown();
    }
}
