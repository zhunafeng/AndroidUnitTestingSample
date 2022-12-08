package com.test.nafeng.zhu.mockito;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

import com.sample.ut.zhu.bean.Person;

/**
 * @Description: annotation Mock
 * @Author: nafeng zhu
 * @Time: 2017/11/4 10:47.
 */

public class MockitoAnnotationsTest {

    @Mock //<-@Mock
    Person mPerson;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this); //<--init
    }

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}
