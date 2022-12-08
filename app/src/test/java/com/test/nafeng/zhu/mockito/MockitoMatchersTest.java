package com.test.nafeng.zhu.mockito;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

import com.sample.ut.zhu.bean.Person;

/**
 *
 * @Author: nafeng zhu
 * @Time: 2017/11/4 18:32.
 */

public class MockitoMatchersTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonAny(){
        when(mPerson.eat(any(String.class))).thenReturn("rice");
        //
        //when(mPerson.eat(anyString())).thenReturn("rice");

        //log rice
        System.out.println(mPerson.eat("noodle"));

    }


    @Test
    public void testPersonContains(){

        when(mPerson.eat(contains("flour"))).thenReturn("noodle");
        //log noodle
        System.out.println(mPerson.eat("flour"));

    }

    @Test
    public void testPersonArgThat(){


        when(mPerson.eat(argThat(new ArgumentMatcher<String>() {
            @Override
            public boolean matches(String argument) {
                return argument.length() % 2 == 0;
            }
        }))).thenReturn("noodle");

        System.out.println(mPerson.eat("1234"));

    }

}
