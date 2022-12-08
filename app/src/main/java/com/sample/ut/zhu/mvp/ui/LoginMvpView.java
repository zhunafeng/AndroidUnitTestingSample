package com.sample.ut.zhu.mvp.ui;


import com.sample.ut.zhu.mvp.base.MvpView;

/**
 * @author nafeng Zhu on 2018/1/27.
 */

public interface LoginMvpView extends MvpView {

    /**
     * countdown completed
     */
    void countdownComplete();

    /**
     * countdown
     * @param time remaining time
     */
    void countdownNext(String time);

    /**
     * login success
     */
    void loginSuccess();

}
