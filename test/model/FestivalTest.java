package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FestivalTest extends EventTest {
    @Test
    void testCastTypeName() {
        Assertions.assertEquals("Festival", testFestival.castTypeName());
    }
}