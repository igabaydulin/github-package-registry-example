package com.github.igabaydulin.sample

import org.junit.After
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import java.io.PrintStream
import java.io.ByteArrayOutputStream
import kotlin.test.assertEquals

class AppTest {

    private val systemOutput = System.out
    private val output = ByteArrayOutputStream()

    @Before
    fun before() {
        System.setOut(PrintStream(output))
    }

    @After
    fun after() {
        System.setOut(systemOutput)
    }

    @Test
    fun testAppHasPing() {
        main()
        assertEquals("ping\n", output.toString())
    }
}
