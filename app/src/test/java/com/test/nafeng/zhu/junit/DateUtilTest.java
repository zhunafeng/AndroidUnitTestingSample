package com.test.nafeng.zhu.junit;


import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.sample.ut.zhu.utils.DateUtil;

/**
 * Created by Nafeng zhu on 2017/10/15.
 */
public class DateUtilTest {

    private String time = "2017-10-15 16:00:02";

    private long timeStamp = 1508054402000L;

    private Date mDate;

    @Before
    public void setUp() throws Exception {
        System.out.println("test start！");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("test end！");
    }

    @Test
    public void stampToDateTest() throws Exception {
        assertEquals(time, DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws Exception {
        assertNotEquals(4, DateUtil.dateToStamp(time));
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp("2017-10-15");
    }

    @Test
    @Ignore("test\n")
    public void test() {
        System.out.println("-----");
    }
}