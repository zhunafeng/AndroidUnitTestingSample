package com.test.nafeng.zhu.robolectric.broadcast;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowApplication;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import com.sample.ut.zhu.broadcast.MyReceiver;

/**
 * @Description: broadcast receiver test
 * @Author: nafeng zhu
 * @Time: 2017/12/4 11:25.
 */

@RunWith(AndroidJUnit4.class)
public class MyReceiverTest{
    
    private final String action = "com.test.nafeng.zhu";

    @Test
    public void testRegister() throws Exception {
        //  ShadowApplication shadowApplication = ShadowApplication.getInstance();
        ShadowApplication shadowApplication = shadowOf((Application) getApplicationContext());
        Intent intent = new Intent(action);

        assertTrue(shadowApplication.hasReceiverForIntent(intent));
    }

    @Test
    public void testReceive() throws Exception {

        Intent intent = new Intent(action);
        intent.putExtra(MyReceiver.NAME, "AndroidUT");
        MyReceiver myReceiver = new MyReceiver();
        
        myReceiver.onReceive(getApplicationContext(), intent);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        assertEquals( "AndroidUT", preferences.getString(MyReceiver.NAME, ""));
    }
}
