package com.test.nafeng.zhu.junit;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nafeng zhu on 2017/10/15.
 */

public class IsMobilePhoneMatcher extends BaseMatcher<String> {


    @Override
    public boolean matches(Object item) {
        if (item == null) {
            return false;
        }

        Pattern pattern = Pattern.compile("(1|861)(3|5|7|8)\\d{9}$*");
        Matcher matcher = pattern.matcher((String) item);

        return matcher.find();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("phone number！");
    }

    @Override
    public void describeMismatch(Object item, Description description) {
        description.appendText(item.toString() + "not phone number！");
    }
}
