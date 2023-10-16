package ru.lakeevda.lesson2;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import static java.util.stream.IntStream.range;

public class Task2 {
    public static void main(String[] args) {
        int[] arr = getArray();
        System.out.println("Массив: " + Arrays.toString(arr));
        System.out.println(String.format("Наличие соседних элементов, с нулевым значением: %s",
                hasTwoZeros(arr) ? "Да" : "Нет"));
    }

    public static int[] getArray() {
        int sizeArray;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Введите длину массива: ");
                sizeArray = scanner.nextInt();
                break;
            } catch (InputMismatchException ignored) {
            }
        }
        int[] arr = new int[sizeArray];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(0,10);
        }
        return arr;
    }

    private static boolean hasTwoZeros(int[] arr) {
        return range(0, arr.length-1).anyMatch(i -> arr[i] == 0 && arr[i+1] == 0);
    }
}
