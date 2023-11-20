package com.foxminded.romangrygorczuk.longdivision;

public class DivisionStep {

    private int incompleteQuotient;
    private int multiplication;

    public DivisionStep(int incompleteQuotient, int multiplication) {
        this.incompleteQuotient = incompleteQuotient;
        this.multiplication = multiplication;
    }

    public int getIncompleteQuotient() {
        return incompleteQuotient;
    }

    public int getMultiplication() {
        return multiplication;
    }
}
