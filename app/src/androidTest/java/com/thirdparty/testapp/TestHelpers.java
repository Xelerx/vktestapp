package com.thirdparty.testapp;

import android.content.Context;
import android.content.Intent;

import androidx.test.uiautomator.By;
import androidx.test.uiautomator.Direction;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import junit.framework.AssertionFailedError;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class TestHelpers {

    public static UiDevice mDevice = UiDevice.getInstance(getInstrumentation());

    public static final String VK_PACKAGE_NAME = "com.vkontakte.android";

    public static final int DEFAULT_TIMEOUT = 3000;
    public static final int SCROLL_TIMEOUT = 1000;

    public static final int APP_LAUNCH_TIMEOUT = 10000;

    public static final int CENTER_OF_SCREEN = mDevice.getDisplayWidth() / 2;
    private static final int TOP_POINT_OF_SCREEN = mDevice.getDisplayHeight() / 4;
    public static final int BOTTOM_POINT_OF_SCREEN = mDevice.getDisplayHeight();
    private static final int RIGHT_POINT_OF_SCREEN = mDevice.getDisplayWidth() * 9 / 10;
    private static final int LEFT_POINT_OF_SCREEN = mDevice.getDisplayWidth() / 10;

    public static void sleepStatement(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForApp(String pkg) {
        mDevice.wait(Until.hasObject(By.pkg(pkg).depth(0)), APP_LAUNCH_TIMEOUT);
    }

    public static void launchApp(String pkg) {
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(pkg);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public static void shouldSeeUiObject(UiObject2... ob) {
        for (UiObject2 item : ob) {
            try {
                assertNotNull(item);
            } catch (AssertionFailedError e) {
                fail("UiObject is not found!");
            }
        }
    }

    public static UiObject2 findUiObjectById(String vk, String id) {
        return mDevice.wait(Until.findObject(By.res(vk, id)), DEFAULT_TIMEOUT);
    }

    public static void scrollUiObjectDown(UiObject2 object) {
        object.scroll(Direction.DOWN, 1f);
        sleepStatement(SCROLL_TIMEOUT);
    }

    public static void scrollUiObjectUp(UiObject2 object) {
        object.scroll(Direction.UP, 1f);
        sleepStatement(SCROLL_TIMEOUT);
    }

    public static boolean pressHome() {
        return mDevice.pressHome();
    }

    public static boolean pressBack() { return mDevice.pressBack(); }

    public static void customUpSwipe() {
        mDevice.swipe(CENTER_OF_SCREEN, RIGHT_POINT_OF_SCREEN,
                CENTER_OF_SCREEN, LEFT_POINT_OF_SCREEN, 5);
        sleepStatement(DEFAULT_TIMEOUT);
    }

    public static void customDownSwipe() {
        mDevice.swipe(CENTER_OF_SCREEN, LEFT_POINT_OF_SCREEN,
                CENTER_OF_SCREEN, RIGHT_POINT_OF_SCREEN, 5);
        sleepStatement(DEFAULT_TIMEOUT);
    }

}