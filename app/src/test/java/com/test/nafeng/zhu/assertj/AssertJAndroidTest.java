package com.test.nafeng.zhu.assertj;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ActivityScenario.launch;
import static org.assertj.core.api.Assertions.assertThat;

import com.sample.ut.R;
import com.sample.ut.zhu.ui.MainActivity;

/**
 * @Description: 
 * @Author: Nafeng zhu
 * @Time: 2018/5/15 0015 10:33.
 */
@RunWith(AndroidJUnit4.class)
public class AssertJAndroidTest {

    private Button mJumpBtn;
    private LinearLayout mRoot;
    private CheckBox checkBox;

    @Before
    public void setUp(){
        ActivityScenario<MainActivity> scenario = launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            mJumpBtn = activity.findViewById(R.id.button1);
            mRoot = activity.findViewById(R.id.root);
            checkBox = activity.findViewById(R.id.checkbox);
        });
    }

    @Test
    public void testView() {
//        AssertJ-Android
        // Button visible
        assertThat(mJumpBtn.getVisibility()).isEqualTo(View.VISIBLE);
        // LinearLayout orientation，View count
        assertThat(mRoot.getOrientation()).isEqualTo(LinearLayout.VERTICAL);
        assertThat(mRoot.getChildCount()).isEqualTo(4);
        // CheckBox
        assertThat(checkBox.isChecked()).isEqualTo(false);
    }
}
