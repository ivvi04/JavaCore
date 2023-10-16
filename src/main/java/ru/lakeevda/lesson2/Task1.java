package ru.lakeevda.lesson2;

import ru.lakeevda.lesson2.game.BullsAndCows;

import java.util.Scanner;

public class Task1 {
    static BullsAndCows game;

    public static void main(String[] args) {
        game = new BullsAndCows();
        System.out.println("Дано 5 попыток!");
        for (int i = 0; i < 5; i++) {
            String userNumber = inputNumber();
            game.checkNumber(userNumber);
            if (!game.getGuessed()) {
                System.out.println("Быков " + game.getBulls() + " и Коров " + game.getCows());
            } else {
                System.out.println("Вы угадали!");
            }
        }
        if (!game.getGuessed()) {
            System.out.println("Загаданное число: " + game.getSecretNumber());
        }
    }

    public static String inputNumber(){
        Scanner scanner = new Scanner(System.in);
        int result = 0;
        while (!game.isNumberMatch(result)) {
            System.out.println("Введите 4х значное число: ");
            result = scanner.nextInt();
        }
        return String.valueOf(result);
    }
}
