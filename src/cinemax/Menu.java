package cinemax;

import java.util.Scanner;

public class Menu {

    public int mostraMenuIniziale() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU CINEMAX =====");
            System.out.println("1) Login");
            System.out.println("2) Registrazione");
            System.out.println("3) Accedi come Guest");
            System.out.println("4) Esci");
            System.out.println("========================");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 || scelta>=5);

        return scelta;
    }
    public int mostraMenuGuest(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do {

            System.out.println("===== MENU GUEST =====\n");
            System.out.println("1) Login\n");
            System.out.println("2) Registrazione\n");
            System.out.println("3) Ricerca Proiezione\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0||scelta>=4);
        return scelta;
    }
    public int mostraMenuCliente(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU CLIENTE=====\n");
            System.out.println("1) Visualliza le tue prenotazioni\n");
            System.out.println("2) Aggiungi prenotazione\n");
            System.out.println("3) Modifica proiezione\n");
            System.out.println("4) Elimina proiezione\n");
            System.out.println("5) visualizza proiezioni\n");
            System.out.println("6) Logout\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 || scelta>=7);

        return scelta;
    }
    public int mostraMenuProiezionista(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU CI =====\n");
            System.out.println("1) Aggiungi film\n");
            System.out.println("2) Aggiungi proiezione\n");
            System.out.println("3) Modifica proiezione\n\n");
            System.out.println("4) Elimina proiezione");
            System.out.println("5) Logout\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while(scelta<=0 || scelta>=6);
        return scelta;
    }

    public int mostraMenuBigliettaio(){
        Scanner input = new Scanner(System.in);
        int scelta;
        do {
            System.out.println("===== MENU BIGLIETTAIO =====\n");
            System.out.println("1) Cerca prenotazione\n");
            System.out.println("2) Logout\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 || scelta>=3);

        return scelta;
    }

    public int mostraMenuCercaProeizione(){
        Scanner input = new Scanner(System.in);
        int scelta;
        do{
            System.out.println("===MENU CERCA PRENOTAZIONE===");
            System.out.println("1) Ricerca per la data della Proiezione");
            System.out.println("2) Ricerca per l'ora della Proiezione");
            System.out.println("3) Ricerca per il titolo del film");
            System.out.println("4) Ricerca per il regista del film ");
            System.out.println("5) Ricerca per l'anno di uscita del film");
            System.out.println("6) Ricerca per la durata del film");
            System.out.println("7) Ricerca per l'età minima del film");
            System.out.println("7) Ricerca combinata");

            System.out.println("========================");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 ||scelta>=8);

        return scelta;
    }
}
