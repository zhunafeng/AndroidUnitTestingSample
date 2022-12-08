package com.sample.ut.zhu.net;

import com.sample.ut.zhu.bean.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: Github api
 * @Author: Nafeng zhu
 * @Time: 2017/12/22 0022 14:03.
 */
public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
