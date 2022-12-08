package com.test.nafeng.zhu.mockito;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.sample.ut.zhu.bean.Person;

/**
 *
 * @Author: nafeng zhu
 * @Time: 2017/11/4 11:11.
 */

public class MockitoStubTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonReturn(){
        when(mPerson.getName()).thenReturn("nafeng");
        when(mPerson.getSex()).thenThrow(new NullPointerException("unknown"));
        
        // output nafeng
        System.out.println(mPerson.getName());

        doReturn("xiaoxiao").when(mPerson).getName();
        // output xiaoxiao
        System.out.println(mPerson.getName());
        
        // exception
//        System.out.println(mPerson.getSex());

    }

    @Test
    public void testPersonRealMethod(){
        doCallRealMethod().when(mPerson).getAge();

        System.out.println(mPerson.getAge());
    }

    @Test
    public void testPersonNothing(){
        doNothing().doThrow(new RuntimeException()).when(mPerson).setSex(1);

        mPerson.setSex(1);

    }
    
    @Test
    public void testPersonAnswer(){
        when(mPerson.eat(anyString())).thenAnswer(new Answer() {
            @Override
            public String answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                return args[0].toString() + "tasty";
            }
        });
        //output dumpling tasty
        System.out.println(mPerson.eat("dumpling"));
    }
}
