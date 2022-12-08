package com.test.nafeng.zhu.robolectric.ui;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowToast;

import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.sample.ut.R;
import com.sample.ut.zhu.ui.LoginActivity;
import com.sample.ut.zhu.ui.MainActivity;
import com.sample.ut.zhu.ui.fragment.SampleFragment;

/**
 * @Description: MainActivity
 * @Author: nafeng zhu
 * @Time: 2017/12/3 12:20.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
    
    private final String TAG = "test";

    private MainActivity mainActivity;
    private Button mJumpBtn;
    private Button mToastBtn;
    private Button mDialogBtn;
    private Button mInverseBtn;
    private CheckBox checkBox;

    @Before
    public void setUp(){
        //log
        ShadowLog.stream = System.out;
        mainActivity = activityRule.getActivity();
        mJumpBtn = mainActivity.findViewById(R.id.button1);
        mToastBtn = mainActivity.findViewById(R.id.button2);
        mDialogBtn = mainActivity.findViewById(R.id.button3);
        mInverseBtn = mainActivity.findViewById(R.id.button4);
        checkBox = mainActivity.findViewById(R.id.checkbox);
    }

    /**
     * Activity test
     */
    @Test
    public void testMainActivity() {
        assertNotNull(mainActivity);
        Log.d(TAG, "Log");
    }


    /**
     *
     * @throws Exception
     */
    @Test
    public void testJump() throws Exception {

        assertEquals(mJumpBtn.getText().toString(), "Activity");

        mJumpBtn.performClick();

        ShadowActivity shadowActivity = Shadows.shadowOf(mainActivity);
        Intent nextIntent = shadowActivity.getNextStartedActivity();
        assertEquals(nextIntent.getComponent().getClassName(), LoginActivity.class.getName());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testToast() throws Exception {
        Toast toast = ShadowToast.getLatestToast();
        assertNull(toast);

        mToastBtn.performClick();
        toast = ShadowToast.getLatestToast();

        assertNotNull(toast);

//        ShadowToast shadowToast = Shadows.shadowOf(toast);
//        assertEquals(Toast.LENGTH_LONG, shadowToast.getDuration());
        assertEquals("Hello UT!", ShadowToast.getTextOfLatestToast());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testDialog() throws Exception {
        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();

        assertNull(dialog);

        mDialogBtn.performClick();
        dialog = ShadowAlertDialog.getLatestAlertDialog();

        assertNotNull(dialog);

        ShadowAlertDialog shadowDialog = Shadows.shadowOf(dialog);
        assertEquals("Hello UT！", shadowDialog.getMessage());
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testCheckBoxState() throws Exception {

        assertFalse(checkBox.isChecked());


        mInverseBtn.performClick();

        assertTrue(checkBox.isChecked());


        mInverseBtn.performClick();

        assertFalse(checkBox.isChecked());
    }

    /**
     * Fragment
     */
    @Test
    public void testFragment() {
        FragmentScenario<SampleFragment> scenario = FragmentScenario.launch(SampleFragment.class);
        scenario.onFragment(fragment -> assertNotNull(fragment.getView()));
    }

    /**
     */
    @Test
    public void testResources() {
        Application application = getApplicationContext();
        String appName = application.getString(R.string.app_name);
        assertEquals("AndroidUT", appName);
    }

    @Test
    public void testLifecycle() throws Exception {

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        MainActivity activity = controller.get();
        assertNull(activity.getLifecycleState());


        controller.create();
        assertEquals("onCreate", activity.getLifecycleState());


        controller.start();
        assertEquals("onStart", activity.getLifecycleState());


        controller.resume();
        assertEquals("onResume", activity.getLifecycleState());


        controller.pause();
        assertEquals("onPause", activity.getLifecycleState());


        controller.stop();
        assertEquals("onStop", activity.getLifecycleState());


        controller.restart();

        assertEquals("onStart", activity.getLifecycleState());


        controller.destroy();
        assertEquals("onDestroy", activity.getLifecycleState());
    }

}