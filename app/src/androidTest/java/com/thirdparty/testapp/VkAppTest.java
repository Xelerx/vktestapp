package com.thirdparty.testapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.thirdparty.testapp.TestHelpers.VK_PACKAGE_NAME;
import static com.thirdparty.testapp.TestHelpers.findUiObjectById;
import static com.thirdparty.testapp.TestHelpers.launchApp;
import static com.thirdparty.testapp.TestHelpers.pressHome;
import static com.thirdparty.testapp.TestHelpers.scrollUiObjectDown;
import static com.thirdparty.testapp.TestHelpers.scrollUiObjectUp;
import static com.thirdparty.testapp.TestHelpers.shouldSeeUiObject;
import static com.thirdparty.testapp.TestHelpers.waitForApp;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 21)
public class VkAppTest {

    @Before
    public void goToHomeScreen() {
        //Go to the home screen
        pressHome();
    }

    @Test
    public void launchVkAppTest() {

        //Launch Vk application
        launchApp(VK_PACKAGE_NAME);

        //Wait for it
        waitForApp(VK_PACKAGE_NAME);

        //Find News Tab button and click it
        findUiObjectById(VK_PACKAGE_NAME, "tab_news")
                .click();
        //Find more recommendation button and click it
        findUiObjectById(VK_PACKAGE_NAME, "recom_friends_btn")
                .click();
        //Scroll 3 times down
        scrollUiObjectDown(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));
        scrollUiObjectDown(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));
        scrollUiObjectDown(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));

        //Check add friend or hide buttons
        shouldSeeUiObject(findUiObjectById(VK_PACKAGE_NAME, "positive"));
        shouldSeeUiObject(findUiObjectById(VK_PACKAGE_NAME, "negative"));

        //Scroll 3 times up
        scrollUiObjectUp(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));
        scrollUiObjectUp(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));
        scrollUiObjectUp(findUiObjectById(VK_PACKAGE_NAME, "rpb_list"));

        //Check add friend or hide buttons again
        shouldSeeUiObject(findUiObjectById(VK_PACKAGE_NAME, "positive"));
        shouldSeeUiObject(findUiObjectById(VK_PACKAGE_NAME, "negative"));
    }
}