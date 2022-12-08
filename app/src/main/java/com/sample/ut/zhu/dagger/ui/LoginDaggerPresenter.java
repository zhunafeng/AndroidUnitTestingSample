package com.sample.ut.zhu.dagger.ui;


import com.sample.ut.zhu.bean.User;
import com.sample.ut.zhu.dagger.base.scope.ActivityScope;
import com.sample.ut.zhu.mvp.base.BaseMVPPresenter;
import com.sample.ut.zhu.mvp.ui.LoginMvpView;
import com.sample.ut.zhu.net.GithubApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author nafeng Zhu on 2018/2/5.
 */

@ActivityScope
public class LoginDaggerPresenter extends BaseMVPPresenter<LoginMvpView> {

    private GithubApi mApi;

    @Inject
    public LoginDaggerPresenter(GithubApi mApi){
        this.mApi = mApi;
    }

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void getIdentify() {
        // interval once in each second until 120
        Disposable mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(120)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onComplete() {
                        mMvpView.countdownComplete();
                    }
                    
                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("interval error！！");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mMvpView.countdownNext(String.valueOf(Math.abs(aLong - 120)));
                    }
                });
        mCompositeDisposable.add(mDisposable);
    }

    public void login(String mobile, String code) {
        if(mobile.length() != 11){
            mMvpView.showToast("wrong phone number");
            return;
        }
        if(code.length() != 6){
            mMvpView.showToast("wrong verification code");
            return;
        }

        mApi.getUser("simplezhli")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (isViewAttached()){
                            mMvpView.showProgress();
                        }

                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()){
                            mMvpView.closeProgress();
                        }
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        mMvpView.showToast("success");
                        mMvpView.loginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("failure");
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void detachView(){
        super.detachView();
        mCompositeDisposable.clear();
    }
}
