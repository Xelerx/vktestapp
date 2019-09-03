package com.thirdparty.testapp;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.thirdparty.testapp.TestHelpers.VK_PACKAGE_NAME;
import static com.thirdparty.testapp.TestHelpers.launchApp;
import static com.thirdparty.testapp.TestHelpers.pressHome;
import static com.thirdparty.testapp.TestHelpers.scrollUiObjectDown;
import static com.thirdparty.testapp.TestHelpers.scrollUiObjectUp;
import static com.thirdparty.testapp.TestHelpers.shouldSeeUiObject;
import static com.thirdparty.testapp.TestHelpers.waitForApp;
import static com.thirdparty.testapp.VkAppMatchers.vkDiscoverTab;
import static com.thirdparty.testapp.VkAppMatchers.vkFeedbackTab;
import static com.thirdparty.testapp.VkAppMatchers.vkFriendsRecBtn;
import static com.thirdparty.testapp.VkAppMatchers.vkFriendsRecList;
import static com.thirdparty.testapp.VkAppMatchers.vkIcon;
import static com.thirdparty.testapp.VkAppMatchers.vkMenuTab;
import static com.thirdparty.testapp.VkAppMatchers.vkMessagesTab;
import static com.thirdparty.testapp.VkAppMatchers.vkNegative;
import static com.thirdparty.testapp.VkAppMatchers.vkNewsTab;
import static com.thirdparty.testapp.VkAppMatchers.vkPhoto;
import static com.thirdparty.testapp.VkAppMatchers.vkPositive;
import static com.thirdparty.testapp.VkAppMatchers.vkSubtitle;
import static com.thirdparty.testapp.VkAppMatchers.vkSubtitle2;
import static com.thirdparty.testapp.VkAppMatchers.vkTitle;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 21)
public class VkAppTest {

    @Before
    public void goToHomeScreen() {
        //Go to the home screen
        pressHome();
    }

    @Test
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
}