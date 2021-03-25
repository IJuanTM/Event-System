package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class EventTest {
    // Create stage object for testing
    public Stage testStage = new Stage(1, "TestStage");

    // Create event objects for testing
    public Festival testFestival = new Festival(1, "TestFestival", 1, testStage, LocalDate.now());
    public Gig testGig = new Gig(1, "TestGig", 2, testStage, LocalDate.now());
    public Show testShow = new Show(1, "TestShow", 3, testStage, LocalDate.now());

    @Test
    void testListEvents() {

    }

    @Test
    void testPrintEventInfo() {

    }
}