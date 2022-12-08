package com.test.nafeng.zhu.mockito;

import com.sample.ut.zhu.bean.Person;


import org.junit.Rule;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.*;

import static org.mockito.Mockito.*;

/**
 * Created by nafeng zhu on 2017/11/5.
 */

public class MockitoInOrderTest {

    @Mock
    Person mPerson;

    @Mock
    Person mPerson1;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void testPersonInOrder(){

        mPerson.setName("zhu");
        mPerson.setSex(1);

        mPerson1.setName("zhu");
        mPerson1.setSex(0);

        InOrder mInOrder = inOrder(mPerson, mPerson1);

        mInOrder.verify(mPerson).setName("nafeng");
        mInOrder.verify(mPerson).setSex(1);


    }
}
