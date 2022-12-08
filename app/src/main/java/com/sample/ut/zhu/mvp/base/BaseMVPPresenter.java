package com.sample.ut.zhu.mvp.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by weilu on 2018/1/27.
 */

public abstract class BaseMVPPresenter<T extends MvpView> {

    /**
     * View weak reference
     */
    private Reference<T> mViewRef;

    protected T mMvpView;

    /**
     * attach to view
     */
    public void attachView(T view){
        mViewRef = new WeakReference<>(view);
        if(isViewAttached()) {
            mMvpView = getView();
        }
    }

    /**
     * get View
     * @return View
     */
    public T getView(){
        return mViewRef.get();
    }

    /**
     * check if activity is finished yet to make sure operation on main thread
     * <p>
     * todo : only isActivityAlive return true then execute operation onActivity,
     * pop up Dialog、Window、Activity transaction
     *
     * @return boolean
     */
    public boolean isViewAttached(){
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * release object
     */
    public void detachView(){
        if( mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
