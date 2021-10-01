import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilTest {

    @Test
    void toUpperCase() {
        String testInput = "testing";
        String actualValue = StringUtil.toUpperCase(testInput);
        Assertions.assertEquals("TESTING", actualValue);
    }
}