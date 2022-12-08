package com.test.nafeng.zhu.dagger;

import com.sample.ut.zhu.MyApp;
import com.sample.ut.zhu.dagger.ui.LoginDaggerPresenter;
import com.sample.ut.zhu.mvp.ui.LoginMvpView;
import com.test.nafeng.zhu.rxjava.RxJavaTestSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * @author nafeng zhu on 2018/2/6
 */
@RunWith(AndroidJUnit4.class)
public class LoginDaggerPresenterTest {

    private LoginDaggerPresenter mPresenter;

    @Mock
    private LoginMvpView mvpView;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp(){
        //log
        ShadowLog.stream = System.out;

        mPresenter = new LoginDaggerPresenter(MyApp.getInstance().getGithubApi());
        mPresenter.attachView(mvpView);
    }

    @Test
    public void testGetIdentify() throws Exception {
        mPresenter.getIdentify();
        // up to 10
        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);

        verify(mvpView, times(10)).countdownNext(anyString());

        rule.getTestScheduler().advanceTimeTo(120, TimeUnit.SECONDS);
        verify(mvpView, times(120)).countdownNext(anyString());

        verify(mvpView).countdownComplete();
    }

    @Test
    public void testLogin() throws Exception {

        initRxJava();

        mPresenter.login("123", "123");
        verify(mvpView).showToast("wrong phone");

        mPresenter.login("13000000000", "123");
        verify(mvpView).showToast("wrong code");

        mPresenter.login("13000000000", "123456");

        verify(mvpView).showProgress();

        verify(mvpView).loginSuccess();

        verify(mvpView).closeProgress();

    }

    private void initRxJava() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }
}
