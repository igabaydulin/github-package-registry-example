package com.github.igabaydulin.ping;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PingTest {

    @Test
    void testPing() {
        Assertions.assertEquals("ping", Ping.ping());
    }
}
