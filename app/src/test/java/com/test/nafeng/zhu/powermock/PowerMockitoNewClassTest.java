package com.test.nafeng.zhu.powermock;



import com.sample.ut.zhu.bean.Banana;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

/**
 * @Description: mock new
 * @Author: nafeng zhu
 * @Time: 2017/11/18 19:30.
 */

public class PowerMockitoNewClassTest {

    @Rule
    public PowerMockRule rule = new PowerMockRule();

    @Test
    @PrepareForTest({Banana.class})
    public void testNewClass() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenReturn("banana");

        PowerMockito.whenNew(Banana.class).withNoArguments().thenReturn(mBanana);

        Banana newBanana = new Banana();
        Assert.assertEquals("banana", newBanana.getBananaInfo());
    }
}
