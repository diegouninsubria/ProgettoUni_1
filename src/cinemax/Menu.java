package cinemax;

import java.util.Scanner;

/**
 * Gestisce la visualizzaione dei vari menu dell'applicazione Cinemax.
 * Ogni metodo mostra un menu specifico in base al ruolo dell'utente
 * (Guest, Cliente, Proiezionista, Bigliettaio) e restituisce la scelta
 * selezionata tramite input da tastiera
 */

public class Menu {

    /**
     * Mostra il menu iniziale dell'applicazione, permettendo all'utente
     * di scegliere tra login, registrazioneaccesso come Guest o uscita.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 4)
     */

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

    /**
     * Mostra il menu dedicato agli utenti Guest, consentendo di effettuare
     * login, registrazione o ricerca della proiezione.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 3)
     */

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

    /**
     * Mostra il menu dedicato agli utenti Cliente, permettendo di visualizzare
     * o gestire le proprie prenotazioni e accedere alle funzioni disponibili.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 6)
     */

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

    /**
     * Mostra il menu dedicato al Proiezionista, che può gestire film e proiezioni.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 5)
     */

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

    /**
     * Mostra il menu edicato al Bigliettaio, che può cercare prenotazioni
     * o effettuare il logout.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 2)
     */

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

    /**
     * Mostra il menu dedicato alla ricerca delle proiezioni, permettendo
     * all'utente di selezionare il criterio di ricerca desiderato
     * (data, ora, titolo, genere, regista, anno, durata, età minima,
     * costo oppure ricerca combinata).
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 10)
     */

    public int mostraMenuCercaProeizione(){
        Scanner input = new Scanner(System.in);
        int scelta;
        do{
            System.out.println("===MENU CERCA PRENOTAZIONE===");
            System.out.println("1) Ricerca per la data della Proiezione");
            System.out.println("2) Ricerca per il titolo del film");
            System.out.println("3) Ricerca per genere del film");
            System.out.println("4) Ricerca per costo della proeizione");
            System.out.println("5) Ricerca combinata");

            System.out.println("========================");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 ||scelta>=6);

        return scelta;
    }

    public int sceltaBigliettaioRicerca(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do{
            System.out.println("===MENU CERCA PRENOTAZIONE===");
            System.out.println("1) Ricerca per ID");
            System.out.println("2) Ricerca nome e cognome");
            System.out.println("3) Ricerca per titolo del film");
            System.out.println("4) Ricerca intervallo data");
            System.out.println("========================");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<1 || scelta>4);

        return scelta;
    }

}
