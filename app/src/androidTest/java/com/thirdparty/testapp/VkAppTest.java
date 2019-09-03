package com.thirdparty.testapp;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import com.thirdparty.testapp.Suites.Acceptance;
import com.thirdparty.testapp.Suites.Regression;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.content.ContentValues.TAG;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkDiscoverTab;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkFeedbackTab;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkFriendsRecBtn;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkFriendsRecList;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkIcon;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkMenuTab;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkMessagesTab;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkNegative;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkNewsTab;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkPhoto;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkPositive;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkSubtitle;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkSubtitle2;
import static com.thirdparty.testapp.TestMatchers.VkAppMatchers.vkTitle;
import static com.thirdparty.testapp.TestUtils.MemoryLogsUtil.MAX_PEAK_PSS_ALLOWED;
import static com.thirdparty.testapp.TestUtils.TestHelpers.DEFAULT_TIMEOUT;
import static com.thirdparty.testapp.TestUtils.TestHelpers.VK_PACKAGE_NAME;
import static com.thirdparty.testapp.TestUtils.TestHelpers.getUsedMemorySize;
import static com.thirdparty.testapp.TestUtils.TestHelpers.launchApp;
import static com.thirdparty.testapp.TestUtils.TestHelpers.pressHome;
import static com.thirdparty.testapp.TestUtils.TestHelpers.scrollUiObjectDown;
import static com.thirdparty.testapp.TestUtils.TestHelpers.scrollUiObjectUp;
import static com.thirdparty.testapp.TestUtils.TestHelpers.shouldSeeUiObject;
import static com.thirdparty.testapp.TestUtils.TestHelpers.sleepStatement;
import static com.thirdparty.testapp.TestUtils.TestHelpers.waitForApp;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 21)
public class VkAppTest {

    @Before
    public void goToHomeScreen() {
        //Go to the home screen
        pressHome();
    }

    @Test
    @Regression
    public void vkFriendsRecListTest() {

        //Launch Vk application
        launchApp(VK_PACKAGE_NAME);

        //Wait for it
        waitForApp(VK_PACKAGE_NAME);

        //Find News Tab button and then click it
        shouldSeeUiObject(vkNewsTab());
        vkNewsTab()
                .click();
        //Find friends recommendation button and click it
        shouldSeeUiObject(vkFriendsRecBtn());
        vkFriendsRecBtn()
                .click();
        //Find scrollable list and scroll 3 times down
        shouldSeeUiObject(vkFriendsRecList());
        scrollUiObjectDown(vkFriendsRecList());
        scrollUiObjectDown(vkFriendsRecList());
        scrollUiObjectDown(vkFriendsRecList());

        //Check all friends matchers
        shouldSeeUiObject(vkPhoto());
        shouldSeeUiObject(vkTitle());
        shouldSeeUiObject(vkIcon());
        shouldSeeUiObject(vkSubtitle());
        shouldSeeUiObject(vkSubtitle2());
        shouldSeeUiObject(vkPositive());
        shouldSeeUiObject(vkNegative());

        //Scroll 3 times up
        shouldSeeUiObject(vkFriendsRecList());
        scrollUiObjectUp(vkFriendsRecList());
        scrollUiObjectUp(vkFriendsRecList());
        scrollUiObjectUp(vkFriendsRecList());

        //Check bottom menu matchers
        shouldSeeUiObject(vkNewsTab());
        shouldSeeUiObject(vkDiscoverTab());
        shouldSeeUiObject(vkMessagesTab());
        shouldSeeUiObject(vkFeedbackTab());
        shouldSeeUiObject(vkMenuTab());
    }

    @Test
    @Acceptance
    public void checkLauncherMemory() {

        //Launch Vk application
        launchApp(VK_PACKAGE_NAME);

        //Wait for it
        waitForApp(VK_PACKAGE_NAME);

        //Wait for dumpsys
        Log.d(TAG, "wait 3 sec");
        sleepStatement(DEFAULT_TIMEOUT);

        //Check memory peak and return results
        long peakMemoryUsage = getUsedMemorySize(VK_PACKAGE_NAME);
        Log.i(TAG, "MEMORY IS " + peakMemoryUsage);
        Log.i(TAG, "maxPeakPssAllowed is " + MAX_PEAK_PSS_ALLOWED);

        Assert.assertTrue(String.format("memory usage for %s is too high: %d > %d", VK_PACKAGE_NAME,
                peakMemoryUsage, MAX_PEAK_PSS_ALLOWED), peakMemoryUsage < MAX_PEAK_PSS_ALLOWED);
    }
}