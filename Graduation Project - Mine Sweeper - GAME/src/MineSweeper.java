import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class MineSweeper {

    private final Scanner sc = new Scanner(System.in);
    private final Random rm = new Random();
    private int satir;
    private int sutun;
    private String[][] mineField;
    private String[][] revealed;

    private int mineCount;

    public void start() {
        System.out.println("MAYIN TARLASINA HOSGELDINIZ ");
        System.out.print("SATIR SAYISI BELİRLEYİNİZ : ");
        satir = sc.nextInt();
        System.out.print("SUTUN SAYISI BELİRLEYİNİZ : ");
        sutun = sc.nextInt();

        mineCount = (satir * sutun) / 4;

        mineField = new String[satir][sutun];
        revealed = new String[satir][sutun];

        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {

                mineField[i][j] = "-";
                revealed[i][j] = "-";
            }

        }

        while (mineCount > 0) {

            int i = rm.nextInt(satir);
            int j = rm.nextInt(sutun);

            if (mineField[i][j].equals("-")) {

                mineField[i][j] = "*";
                mineCount--;

            }

        }
        printRevealed();
        play();
    }
    private void play() {
        boolean finish = false;
        while (!finish) {

            System.out.print("SATIR GIRINIZ : ");
            int seRow = sc.nextInt();
            System.out.print("SUTUN GIRINIZ  : ");
            int seCol = sc.nextInt();
            int number = 0;

            if (seRow < satir && seCol < sutun) {
                if (mineField[seRow][seCol].equals("-") && revealed[seRow][seCol].equals("-")) {
                    for (int i = seRow - 1; i < seRow + 2; i++) {
                        for (int j = seCol - 1; j < seCol + 2; j++) {
                            if (i >= 0 && j >= 0 && i < satir && j < sutun && mineField[i][j].equals("*")) {
                                number++;
                                revealed[seRow][seCol] = Integer.toString(number);
                            }
                            else {
                                revealed[seRow][seCol] = Integer.toString(number);
                            }
                        }
                    }
                    printRevealed();
                    if (!checkWin()) {
                        System.out.println("!!! TEBRIKLER KAZANDINIZ !!!.");
                        printMine();
                        finish = true;
                    }

                } else if (mineField[seRow][seCol].equals("*")) {

                    System.out.println("MAYINI BULDUNUZ MAYIN PATLADI.");
                    printMine();
                    finish = true;

                } else if (!revealed[seRow][seCol].equals("-")) {

                    System.out.println("FARKLI BIR YER SECINIZ \nBUNU DAHA ONCEDEN SECTINIZ ");

                }

            } else {
                System.out.println("ALAN ICINDE SECIM YAPINIZ \nHATALI SECIM YAPTINIZ ");
            }

        }

    }

    private void printRevealed() {
        System.out.println("---------------------------------");

        for (int i = 0; i < revealed.length; i++) {
            for (int j = 0; j < revealed[i].length; j++) {
                System.out.print(revealed[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    private void printMine() {
        System.out.println("---------------------------------");
        for (int i = 0; i < mineField.length; i++) {
            for (int j = 0; j < mineField[i].length; j++) {
                System.out.print(mineField[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------------");
    }

    private boolean checkWin() {
        int count = 0;
        int mineNumber = 0;
        for (int i = 0; i < revealed.length; i++) {
            for (int j = 0; j < revealed[i].length; j++) {

                if (revealed[i][j].equals("-")) {
                    count++;
                }
                if (mineField[i][j].equals("*")) {
                    mineNumber++;
                }
            }
        }

        if (mineNumber == count) {

            return false;

        }

        return true;
    }

}