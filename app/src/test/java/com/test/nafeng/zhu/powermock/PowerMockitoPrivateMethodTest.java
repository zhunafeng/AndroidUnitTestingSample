package com.test.nafeng.zhu.powermock;


import com.sample.ut.zhu.bean.Banana;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @Description: mock private
 * @Author: nafeng zhu
 * @Time: 2017/11/18 11:20.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Banana.class})
public class PowerMockitoPrivateMethodTest {

    @Test
    public void testPrivateMethod() throws Exception {
        Banana mBanana = PowerMockito.mock(Banana.class);
        PowerMockito.when(mBanana.getBananaInfo()).thenCallRealMethod();
        PowerMockito.when(mBanana, "flavor").thenReturn("bitter");
        Assert.assertEquals("bitter and yellow", mBanana.getBananaInfo());

        PowerMockito.verifyPrivate(mBanana).invoke("flavor"); 
    }

    @Test
    public void skipPrivateMethod() {
        Banana mBanana = new Banana();

        PowerMockito.suppress(PowerMockito.method(Banana.class, "flavor")); 
        Assert.assertEquals("null yellow", mBanana.getBananaInfo());
    }


    @Test
    public void testChangeParentPrivate() throws Exception {
        Banana mBanana = new Banana();
        MemberModifier.field(Banana.class, "fruit").set(mBanana, "vega");
        Assert.assertEquals("vega", mBanana.getFruit());
    }

}
