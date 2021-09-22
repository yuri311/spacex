package com.yuri.spacex

import com.yuri.spacex.repository.ApiRepository
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testApi() {
        ApiRepository.getAllLaunches()
    }
}