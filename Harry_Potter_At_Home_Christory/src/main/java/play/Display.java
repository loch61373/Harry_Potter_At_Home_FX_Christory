package play;

import java.util.Scanner;

public class Display {
    static Scanner scanner = new Scanner(System.in);

    public void printMessage(String message){
        System.out.println(message);
    }

    //clearing out the console
    public static void clearConsole() {
        for (int i = 0; i < 100; i++)
            System.out.println();
    }

    //separator with length n
    public static void printSeparator(int n) {
        for (int i = 0; i < n; i++)
            System.out.print("-");
        System.out.println();
    }

    //print a heading
    public static void printHeading(String title) {
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    public static void anythingToContinue() {
        System.out.println("\nPress any key to continue...");
        scanner.nextLine();
    }

}
