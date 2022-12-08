package com.sample.ut.zhu.mvp.base;

import android.content.Context;

/**
 * @author nafeng Zhu on 2018/1/27.
 */

public interface MvpView {

    /***
     * get Context
     * @return Context
     */
    Context getContext();

    /***
     * show Progress
     */
    void showProgress();

    /***
     * close Progress
     */
    void closeProgress();

    /***
     * @param string message text
     */
    void showToast(String string);
}
