package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GigTest extends EventTest {
    @Test
    void testCastTypeName() {
        Assertions.assertEquals("Gig", testGig.castTypeName());
    }
}