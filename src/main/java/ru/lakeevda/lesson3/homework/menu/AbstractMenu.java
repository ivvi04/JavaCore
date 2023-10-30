package ru.lakeevda.lesson3.homework.menu;

import java.util.Scanner;

public abstract class AbstractMenu {
    public static String readMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static void printSeparator() {
        System.out.println("---------------------------------------------------");
    }

    public static void printMenu(){
        printSeparator();
    }

    public static void viewMenu() {

    }
}
