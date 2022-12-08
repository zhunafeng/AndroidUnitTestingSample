package com.sample.ut.zhu.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;

import com.sample.ut.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class LoginActivity extends AppCompatActivity {

    private Disposable mDisposable;
    private TextView mTvSendIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mTvSendIdentify = this.findViewById(R.id.tv_send_identify);

        mTvSendIdentify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getIdentify();
            }
        });
    }

    private void getIdentify() {
        mTvSendIdentify.setEnabled(false);
        // interval
        mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(120)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onComplete() {
                        mTvSendIdentify.setText(R.string.login_send_identify);
                        mTvSendIdentify.setEnabled(true);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(Long aLong) {
                        mTvSendIdentify.setText(TextUtils.concat(String.valueOf(Math.abs(aLong - 120)), "retry"));
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null){
            mDisposable.dispose();
        }
    }

}
