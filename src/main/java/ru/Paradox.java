package ru;

import java.util.Random;

public class Paradox {
    private static final int NUMBER_DOOR = 3;
    private final Random random = new Random();
    private static int carDoor;

    public Paradox() {
        carDoor = random.nextInt(1,NUMBER_DOOR+1);
    }

    public int chooseDoor(){ return random.nextInt(1,NUMBER_DOOR+1);}
    public int openDoorLead(int selectedDoor) {
        int openedDoor;
        do {
            openedDoor = chooseDoor();
        } while (openedDoor == selectedDoor || openedDoor == carDoor);
        return openedDoor;
    }

    public int changeDoor(int selectedDoor, int openedDoor) {
        int changedDoor;
        do {
            changedDoor = chooseDoor();
        } while (changedDoor == selectedDoor || changedDoor == openedDoor);
        return changedDoor;
    }

    public void startGame(int countGames) {
        int constantWins = 0;
        int changeWins = 0;
        int selectedDoor;
        int openedDoor;
        int changedDoor;

        for (int i = 0; i < countGames; i++) {
            selectedDoor = chooseDoor();
            openedDoor = openDoorLead(selectedDoor);

            if (selectedDoor == carDoor) {
                constantWins++;
            }
        }

        for (int i = 0; i < countGames; i++) {
            selectedDoor = chooseDoor();
            openedDoor = openDoorLead(selectedDoor);
            changedDoor = changeDoor(selectedDoor, openedDoor);

            if (changedDoor == carDoor) {
                changeWins++;
            }
        }

        double constantWinsPercent = constantWins * 100. / countGames;
        double changeWinsPercent = changeWins * 100. / countGames;
        System.out.printf("""
                Number of the game: %d
                Number and percent of constant doors wins: %d (%.2f)
                Number and percent of changed doors wins: %d (%.2f)
                """, countGames, constantWins, constantWinsPercent, changeWins, changeWinsPercent);

        Main.latch.countDown();
    }
}
