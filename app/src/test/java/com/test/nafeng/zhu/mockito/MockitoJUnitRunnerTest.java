package com.test.nafeng.zhu.mockito;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

import com.sample.ut.zhu.bean.Person;

/**
 * @Description: Mock
 * @Author: nafeng zhu
 * @Time: 2017/11/4 0004 10:50.
 */

@RunWith(MockitoJUnitRunner.class) //<--MockitoJUnitRunner
public class MockitoJUnitRunnerTest {

    @Mock //<--@Mock
    Person mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}
