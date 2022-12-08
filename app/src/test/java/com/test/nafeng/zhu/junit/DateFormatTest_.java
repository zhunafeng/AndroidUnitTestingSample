package com.test.nafeng.zhu.junit;


import com.sample.ut.zhu.utils.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;

/**
 * Created by nafeng zhu on 2017/10/15.
 */

@RunWith(Parameterized.class)
public class DateFormatTest_ {

    private String time;
    private long lt;

    public DateFormatTest_(String time, long lt) {
        this.time = time;
        this.lt = lt;
    }


    @Parameterized.Parameters
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] { { "2017-10-15", 1508054402001L }, { "2017年10月15日 16时00分02秒", 1508054402000L }});
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp(time);
    }


    @Test
    public void stampToDateTest(){
        DateUtil.stampToDate(lt);
    }
}
