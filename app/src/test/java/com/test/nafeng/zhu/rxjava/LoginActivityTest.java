package com.test.nafeng.zhu.rxjava;

import android.app.Application;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.sample.ut.R;
import com.sample.ut.zhu.ui.LoginActivity;

/**
 * @Description: LoginActivity test
 * @Author: nafeng zhu
 * @Time: 2018/1/6 15:20.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private TextView mTvSendIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp(){
        ActivityScenario<LoginActivity> scenario = launch(LoginActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            mTvSendIdentify = activity.findViewById(R.id.tv_send_identify);
        });
    }

    @Test
    public void testGetIdentify() throws Exception {
        Application application = getApplicationContext();
        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));


        mTvSendIdentify.performClick();

        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);
        assertEquals(mTvSendIdentify.isEnabled(), false);
        assertEquals(mTvSendIdentify.getText().toString(), "retry");

        rule.getTestScheduler().advanceTimeTo(120, TimeUnit.SECONDS);

        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));
        assertEquals(mTvSendIdentify.isEnabled(), true);
    }

}