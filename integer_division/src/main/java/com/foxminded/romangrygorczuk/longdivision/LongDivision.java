package com.foxminded.romangrygorczuk.longdivision;

import java.util.ArrayList;
import java.util.List;

public class LongDivision {

    public DivisionResult divide(int dividend, int divisor) {
        int[] dividendDigits = Integer.toString(dividend).chars().map(c -> c-'0').toArray();
        List<DivisionStep> steps = new ArrayList<>();
        List<Integer> remindersList = new ArrayList<>();

        int quotient = 0;
        int remainder = 0;
        for (int i = 0; i < dividendDigits.length; i++) {
            int incompleteQuotient = remainder * 10 + dividendDigits[i];
            int quotientDigit = incompleteQuotient / divisor;
            int multiplication = quotientDigit * divisor;
            quotient = quotient * 10 + quotientDigit;
            remainder = incompleteQuotient - multiplication;
            remindersList.add(remainder);
            steps.add(new DivisionStep(incompleteQuotient, multiplication));
        }
        DivisionResult resultNew = new DivisionResult(dividend, divisor, quotient, remainder, steps, remindersList);
        return resultNew;
    }
}
