package cinemax;

import java.util.Scanner;

public class Menu {

    public int mostraMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("===== MENU CINEMAX =====");
        System.out.println("1) Login");
        System.out.println("2) Registrazione");
        System.out.println("3) Esci");
        System.out.println("========================");
        System.out.print("Scelta: ");

        int scelta = input.nextInt();
        input.nextLine(); // pulizia buffer

        return scelta;
    }
}
