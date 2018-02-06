import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void kilometersToMiles() {
        assertEquals(3.9, Main.kilometersToMiles(6.21371192));
    }
    @Test
    void milesToKilometeres() {
        assertEquals(16.1, Main.milesToKilometeres(10.0 ));
        assertEquals(20.1, Main.milesToKilometeres(12.5 ));
    }
    @Test
    void haversine1() {
        assertEquals(81.45217617231165, Main.haversine(39.1, 94.58, 38.39, 94.35));
    }

}