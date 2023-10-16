package ru.lakeevda.lesson2.game;

import java.util.Random;

public class BullsAndCows {

    private String bulls;
    private String cows;
    private String secretNumber;
    private boolean guessed = false;

    public BullsAndCows() {
        startNew();
    }

    public void startNew() {
        Random random = new Random();
        int rnd;
        while (isNotNumberMatch(rnd = random.nextInt(9900) + 100)) ;
        this.secretNumber = String.format("%04d", rnd);
    }

    public boolean isNotNumberMatch(int num) {
        String str = String.format("%04d", num);
        if (str.length() == 4 && str.matches("(?!.*(.).*\\1)\\d{4}")) {
            return false;
        }
        return true;
    }

    public void checkNumber(String userNumber) {
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < 4; i++) {
            if (userNumber.charAt(i) == secretNumber.charAt(i)) {
                bulls++;
            } else if (secretNumber.contains(String.valueOf(userNumber.charAt(i)))) {
                cows++;
            }
        }
        if (bulls == 4) {
            this.guessed = true;
        }
        this.bulls = String.valueOf(bulls);
        this.cows = String.valueOf(cows);
    }

    public String getSecretNumber() {
        return secretNumber;
    }

    public boolean getGuessed() {
        return guessed;
    }

    public String getBulls() {
        return bulls;
    }

    public String getCows() {
        return cows;
    }
}
