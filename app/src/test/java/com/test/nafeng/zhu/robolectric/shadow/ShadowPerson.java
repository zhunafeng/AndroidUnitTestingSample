package com.test.nafeng.zhu.robolectric.shadow;


import com.sample.ut.zhu.bean.Person;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

/**
 * {@link Person}
 * @Description: custom Shadow
 * @Author: nafeng zhu
 * @Time: 2017/12/4 13:05.
 */
@Implements(Person.class)
public class ShadowPerson {

    @Implementation
    public String getName() {
        return "AndroidUT";
    }
    
    @Implementation
    public int getSex() {
        return 0;
    }
    
    @Implementation
    public int getAge(){
        return 18;
    }
}
