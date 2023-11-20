package com.foxminded.romangrygorczuk.task1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Anagram anagram = new Anagram();
        Scanner scanner = new Scanner(System.in);
        System.out.println("text: ");
        String text = scanner.nextLine();
        System.out.println(anagram.reverseText(text));
    }
}