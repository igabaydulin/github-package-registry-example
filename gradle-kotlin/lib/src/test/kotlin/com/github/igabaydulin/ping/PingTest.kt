package com.github.igabaydulin.ping

import kotlin.test.Test
import kotlin.test.assertEquals

class PingTest {

    @Test
    fun testPing() {
        assertEquals("ping", ping())
    }
}
