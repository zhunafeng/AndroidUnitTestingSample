package com.test.nafeng.zhu.powermock;


import com.sample.ut.zhu.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

/**
 * @Description: mock static
 * @Author: nafeng zhu
 * @Time: 2017/11/18 11:12.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoStaticMethodTest {

    @Test
    public void testStaticMethod() { 
        PowerMockito.mockStatic(Banana.class); //<-- mock
        Mockito.when(Banana.getColor()).thenReturn("green");
        Assert.assertEquals("green", Banana.getColor());
    }

    @Test
    public void testChangeColor() { 
        Whitebox.setInternalState(Banana.class, "COLOR", "red");
        Assert.assertEquals("red", Banana.getColor());
    }
}
