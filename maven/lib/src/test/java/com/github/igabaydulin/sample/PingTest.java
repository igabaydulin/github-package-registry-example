package com.github.igabaydulin.sample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PingTest {

    @Test
    void testPing() {
        Assertions.assertEquals("ping", Ping.ping());
    }
}
