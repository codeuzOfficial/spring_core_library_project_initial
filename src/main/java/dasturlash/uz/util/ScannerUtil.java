package dasturlash.uz.util;

import dasturlash.uz.container.ComponentContainer;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ScannerUtil {
    public static int getAction() {
        System.out.print("Enter action: ");
        try {
            return ComponentContainer.scannerNumber.nextInt();
        } catch (InputMismatchException e) {
            ComponentContainer.scannerNumber = new Scanner(System.in);
            System.out.println("\nPlease enter number.\n");
            return -1;
        }
    }
}
