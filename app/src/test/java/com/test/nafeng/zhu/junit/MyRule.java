package com.test.nafeng.zhu.junit;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * @author nafeng zhu on 2017/10/16 13:23
 */

public class MyRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {

                String methodName = description.getMethodName(); //
                System.out.println(methodName + "start！");

                base.evaluate();


                System.out.println(methodName + "end！");
            }
        };
    }

}
