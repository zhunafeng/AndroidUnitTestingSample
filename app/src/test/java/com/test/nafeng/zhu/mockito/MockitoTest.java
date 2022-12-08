package com.test.nafeng.zhu.mockito;

import com.sample.ut.zhu.bean.Person;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @Description: Mock
 * @Author: Nafeng zhu
 * @Time: 2017/11/4 10:44.
 */

public class MockitoTest {

    @Test
    public void testIsNotNull(){
        Person mPerson = mock(Person.class); //<--mock

        assertNotNull(mPerson);
    }
}
