package com.sample.ut.zhu.mvp.ui;

import com.sample.ut.zhu.bean.User;
import com.sample.ut.zhu.mvp.base.BaseMVPPresenter;
import com.sample.ut.zhu.net.GithubService;

import java.util.concurrent.TimeUnit;

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
 * @author Nafeng zhu on 2018/1/27.
 */

public class LoginPresenter extends BaseMVPPresenter<LoginMvpView> {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void getIdentify() {
        // interval
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
                        mMvpView.showToast("countdown error！");
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
            mMvpView.showToast("wrong verification number");
            return;
        }

        GithubService.createGithubService()
                .getUser("simplezhli")
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
