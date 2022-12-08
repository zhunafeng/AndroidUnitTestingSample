package com.test.nafeng.zhu.mockito;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;

import com.sample.ut.zhu.bean.Person;

/**
 * @Description: MockitoRule Mock
 * @Author: nafeng zhu
 * @Time: 2017/11/4 14:43.
 */

public class MockitoRuleTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }
   
}
