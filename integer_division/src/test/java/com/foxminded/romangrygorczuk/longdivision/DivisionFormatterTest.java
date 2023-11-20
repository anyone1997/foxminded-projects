package com.foxminded.romangrygorczuk.longdivision;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DivisionFormatterTest extends LongDivisionTest {

    DivisionFormatter divisionFormatter = new DivisionFormatter();

    @Test
    void formatResult_shouldFormatNumbers_whenSpecificDividendAndDivisorProvided() {
        int divident = 78945;
        int divisor = 4;
        DivisionResult divisionResult = longDivision.divide(divident, divisor);
        String actual = String.valueOf(divisionFormatter.formatResult(divisionResult));
        String expected =
                        "_78945|4" + System.lineSeparator() +
                        " 4    |-----" + System.lineSeparator() +
                        " -    |19736" + System.lineSeparator() +
                        "_38" + System.lineSeparator() +
                        " 36" + System.lineSeparator() +
                        " --" + System.lineSeparator() +
                        " _29" + System.lineSeparator() +
                        "  28" + System.lineSeparator() +
                        "  --" + System.lineSeparator() +
                        "  _14" + System.lineSeparator() +
                        "   12" + System.lineSeparator() +
                        "   --" + System.lineSeparator() +
                        "   _25" + System.lineSeparator() +
                        "    24" + System.lineSeparator() +
                        "    --" + System.lineSeparator() +
                        "     1";
        assertEquals(expected, actual);
    }

    @Test
    void formatResult_shouldFormatNumbers_whenSpecificDividendAndDivisorProvided2() {
        int divident = 4554;
        int divisor = 7;
        DivisionResult divisionResult = longDivision.divide(divident, divisor);
        String actual = String.valueOf(divisionFormatter.formatResult(divisionResult));
        String expected =
                        "_4554|7" + System.lineSeparator() +
                        " 42  |---" + System.lineSeparator() +
                        " --  |650" + System.lineSeparator() +
                        " _35" + System.lineSeparator() +
                        "  35" + System.lineSeparator() +
                        "  --" + System.lineSeparator() +
                        "   _4" + System.lineSeparator() +
                        "    0" + System.lineSeparator() +
                        "    -" + System.lineSeparator() +
                        "    4";
        assertEquals(expected, actual);
    }

    @Test
    void formatResult_shouldFormatNumbers_whenSpecificDividendAndDivisorProvided3() {
        int divident = 724;
        int divisor = 4;
        DivisionResult divisionResult = longDivision.divide(divident, divisor);
        String actual = String.valueOf(divisionFormatter.formatResult(divisionResult));
        String expected =
                        "_724|4" + System.lineSeparator() +
                        " 4  |---" + System.lineSeparator() +
                        " -  |181" + System.lineSeparator() +
                        "_32" + System.lineSeparator() +
                        " 32" + System.lineSeparator() +
                        " --" + System.lineSeparator() +
                        "  _4" + System.lineSeparator() +
                        "   4" + System.lineSeparator() +
                        "   -" + System.lineSeparator() +
                        "   0";
        assertEquals(expected, actual);
    }

    @Test
    void formatResult_shouldFormatNumbers_whenSpecificDividendAndDivisorProvided4() {
        int divident = 189;
        int divisor = 4;
        DivisionResult divisionResult = longDivision.divide(divident, divisor);
        String actual = String.valueOf(divisionFormatter.formatResult(divisionResult));
        String expected =
                        "_189|4" + System.lineSeparator() +
                        " 16 |--" + System.lineSeparator() +
                        " -- |47" + System.lineSeparator() +
                        " _29" + System.lineSeparator() +
                        "  28" + System.lineSeparator() +
                        "  --" + System.lineSeparator() +
                        "   1";
        assertEquals(expected, actual);
    }

    @Test
    void formatResult_shouldFormatNumbers_whenSpecificDividendAndDivisorProvided5() {
        int divident = 455;
        int divisor = 4;
        DivisionResult divisionResult = longDivision.divide(divident, divisor);
        String actual = String.valueOf(divisionFormatter.formatResult(divisionResult));
        String expected =
                        "_455|4" + System.lineSeparator() +
                        " 4  |---" + System.lineSeparator() +
                        " -  |113" + System.lineSeparator() +
                        " _5" + System.lineSeparator() +
                        "  4" + System.lineSeparator() +
                        "  -" + System.lineSeparator() +
                        " _15" + System.lineSeparator() +
                        "  12" + System.lineSeparator() +
                        "  --" + System.lineSeparator() +
                        "   3";
        assertEquals(expected, actual);
    }
}