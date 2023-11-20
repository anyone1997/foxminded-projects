package com.foxminded.romangrygorczuk.longdivision;

import java.util.List;

public class DivisionResult {

    private int dividend;
    private int divisor;
    private int quotient;
    private int remainder;
    private List<DivisionStep> steps;

    List<Integer> remindersList;

    public DivisionResult(int divident, int divisor, int quotient, int remainder, List<DivisionStep> steps, List<Integer> remindersList) {
        this.dividend = divident;
        this.divisor = divisor;
        this.quotient = quotient;
        this.remainder = remainder;
        this.steps = steps;
        this.remindersList = remindersList;
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getQuotient() {
        return quotient;
    }

    public int getRemainder() {
        return remainder;
    }

    public List<DivisionStep> getSteps() {
        return steps;
    }
}
