package com.test.nafeng.zhu.mockito;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.sample.ut.zhu.bean.Home;
import com.sample.ut.zhu.bean.Person;

/**
 * @Description: Spy case
 * @Author: nafeng zhu
 * @Time: 2017/11/4 19:12.
 */

public class MockitoSpyTest {

    @Spy
    Person mPerson;
    
    @InjectMocks
    Home mHome;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

    @Test
    public void testPersonSpy(){
        //input 11
        System.out.print(mPerson.getAge());
    }

    @Test
    public void testHomeInjectMocks(){
        when(mPerson.getName()).thenReturn("nafeng");
        System.out.print(mHome.getMaster());
    }
}
