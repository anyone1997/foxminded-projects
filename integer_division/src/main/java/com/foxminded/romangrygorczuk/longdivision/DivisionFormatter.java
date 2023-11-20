package com.foxminded.romangrygorczuk.longdivision;

public class DivisionFormatter {

    public String formatResult(DivisionResult result){
        DivisionResult divisionResult = new DivisionResult(result.getDividend(), result.getDivisor(), result.getQuotient(), result.getRemainder(), result.getSteps(), result.remindersList);
        StringBuilder formattedResult = new StringBuilder();
        int multiplication = 1;
        int leftJustifying = 1;
        formatHeader(result, divisionResult, formattedResult);
        return formatSteps(result, formattedResult, leftJustifying, multiplication);
    }

    public StringBuilder formatHeader(DivisionResult result, DivisionResult divisionResult, StringBuilder formattedResult) {
        int[] dividendDigits = Integer.toString(result.getDividend()).chars().map(c -> c-'0').toArray();
        if (dividendDigits[0] < result.getDivisor()){
            result.getSteps().remove(0);
            result.remindersList.remove(0);
        }
        //1st string
        formattedResult.append("_");
        formattedResult.append(String.format("%d|%d%n", divisionResult.getDividend(), divisionResult.getDivisor()));
        //2nd string
        StringBuilder dash = new StringBuilder();
        int quotientLength = String.valueOf(divisionResult.getQuotient()).length();
        for (int i = 0; i < quotientLength; i++) {
            dash.append("-");
        }
        String singleDash = "-";
        String doubleDash = "--";
        if (dividendDigits[0] < result.getDivisor()) {
            formattedResult.append(String.format(" %-" + ++quotientLength + "d|" + dash + "%n", result.getSteps().get(0).getMultiplication()));
            //3rd string
            formattedResult.append(String.format(" %-" + quotientLength + "s|%d%n", doubleDash, divisionResult.getQuotient()));
        } else {
            formattedResult.append(String.format(" %-" + quotientLength + "d|" + dash + "%n", result.getSteps().get(0).getMultiplication()));
            //3rd string
            formattedResult.append(String.format(" %-" + quotientLength + "s|%d%n", singleDash, divisionResult.getQuotient()));
        }
        return formattedResult;
    }

    public String formatSteps(DivisionResult result, StringBuilder formattedResult, int leftJustifying, int multiplication){
        String underScore = "_";
        String space = " ";
        String singleDash = "-";
        String doubleDash = "--";
        int r = 0;
        for (int i = 1; i < result.getSteps().size(); i++) {
            if (result.getSteps().get(r).getMultiplication() >= 10 && result.remindersList.get(r) != 0) {
                leftJustifying++;
            } else if (result.getSteps().get(r).getMultiplication() >= 10 && result.remindersList.get(r) == 0) {
                leftJustifying += 2;
            } else if (result.getSteps().get(r).getMultiplication() < 10 && result.remindersList.get(r) == 0) {
                leftJustifying++;
            }
            r++;
            //1st string
            formattedResult.append(String.format("%" + leftJustifying + "s", underScore));
            formattedResult.append(String.format("%s%n", result.getSteps().get(i).getIncompleteQuotient()));
            //2nd string
            formattedResult.append(String.format("%" + leftJustifying + "s%d%n", space, result.getSteps().get(multiplication).getMultiplication()));
            //3rd string
            int dashLength = String.valueOf(result.getSteps().get(multiplication).getMultiplication()).length();
            if (dashLength == 1) {
                formattedResult.append(String.format("%" + leftJustifying + "s%s%n", space, singleDash));
            } else {
                formattedResult.append(String.format("%" + leftJustifying + "s%s%n", space, doubleDash));
            }
            multiplication++;
        }
        int preLastReminderLength = String.valueOf(result.getSteps().get(multiplication-1).getIncompleteQuotient()).length();
        if (preLastReminderLength == 2) {
            leftJustifying +=2;
            formattedResult.append(String.format("%" + leftJustifying + "s", result.getRemainder()));
        } else {
            formattedResult.append(String.format("%" + ++leftJustifying + "s", result.getRemainder()));
        }
        String completeFormattedResult = formattedResult.toString();
        return completeFormattedResult;
    }
}
