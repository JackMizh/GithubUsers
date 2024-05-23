@file:Suppress("UNCHECKED_CAST")

package com.githubusers.app

import androidx.lifecycle.LiveData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

object LiveDataTestUtil {
    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        liveData.observeForever { o ->
            data[0] = o
            latch.countDown()
        }
        latch.await(2, TimeUnit.SECONDS)
        return data[0] as T
    }
}