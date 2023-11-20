package com.foxminded.romangrygorczuk.charcount;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CharCounter charCounter = new CharCounterCacheDecorator(new UniqueCharCounter());
        String input = "";
        do {
            input = scanner.nextLine();
            print(charCounter.count(input));
            System.out.println("");
        } while (input.length() != 0);
    }

    static void print(Map<Character, Integer> output) {
        for (Map.Entry<Character, Integer> chars :
            output.entrySet()) {
            System.out.print("\"" + chars.getKey() + "\" - ");
            System.out.println(chars.getValue());
        }
    }
}
