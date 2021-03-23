package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ShowTest extends EventTest {
    @Test
    void testCastTypeName() {
        Assertions.assertEquals("Show", testShow.castTypeName());
    }
}