package com.udacity.gradle.builditbigger;

import android.text.TextUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This test case is for testing the app module {@link EndpointsAsyncTask} if it retrieves a non-empty String
 * see: https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
 */
@RunWith(JUnit4.class)
public class EndpointsAsyncTaskTest {
    // create  a signal to let us know when our task is done.
    final CountDownLatch signal = new CountDownLatch(1);

    private String result = "";

    @Test
    public void testEndpointsAsyncTask_ReturnsNonEmptyString(){
        new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncRespone() {
            @Override
            public void onDataLoaded(String joke) {
                result = joke;
                signal.countDown();// notify the count down latch
            }
        }).execute();
        try {
            signal.await();// wait for callback
            // The task is done, and if its result is empty it asserts to fail!
            assertFalse("AsyncTask result is empty.",  TextUtils.isEmpty(result));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}