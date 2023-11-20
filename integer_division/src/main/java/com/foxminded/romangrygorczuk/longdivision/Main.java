package com.foxminded.romangrygorczuk.longdivision;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        LongDivision longDivision = new LongDivision();
        Scanner scanner = new Scanner(System.in);

        int dividend = scanner.nextInt();
        int divisor = scanner.nextInt();

        DivisionResult result = longDivision.divide(dividend, divisor);
        DivisionFormatter formatter = new DivisionFormatter();
        System.out.println(formatter.formatResult(result));
    }
}