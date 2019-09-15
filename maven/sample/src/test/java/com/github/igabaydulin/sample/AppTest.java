package com.github.igabaydulin.sample;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

class AppTest {

    private final PrintStream systemOutput = System.out;
    private final OutputStream output = new ByteArrayOutputStream();

    @BeforeEach
    void before() {
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void after() {
        System.setOut(systemOutput);
    }

    @Test
    void testPing() {
        App.main(new String[]{});
        Assertions.assertEquals("ping\n", output.toString());
    }
}
