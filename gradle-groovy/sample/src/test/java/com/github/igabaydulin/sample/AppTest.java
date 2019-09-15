package com.github.igabaydulin.sample;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private PrintStream systemOutput = System.out;
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void before() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void after() {
        System.setOut(systemOutput);
    }

    @Test
    void testAppHasPing() {
        App.main(new String[]{});
        assertEquals("ping\n", output.toString());
    }
}