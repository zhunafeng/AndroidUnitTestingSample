package com.test.nafeng.zhu.robolectric.service;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;

import org.robolectric.android.controller.ServiceController;
import org.robolectric.shadows.ShadowLog;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.sample.ut.zhu.service.MyService;

/**
 * @Description: custom service test
 * @Author: Nafeng zhu
 * @Time: 2017/12/4 11:40.
 */
@RunWith(AndroidJUnit4.class)
public class MyServiceTest {

    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        controller = Robolectric.buildService(MyService.class);
        mService = controller.get();
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testServiceLifecycle() throws Exception {
        controller.create();
        controller.startCommand(0, 0);
        controller.bind();
        controller.unbind();
        controller.destroy();
    }
}
