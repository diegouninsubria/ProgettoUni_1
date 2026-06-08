package cinemax;

import java.util.Scanner;

/**
 * Gestisce la visualizzazione dei vari menu dell'applicazione Cinemax.
 * Ogni metodo mostra un menu specifico in base al ruolo dell'utente
 * (Guest, Cliente, Proiezionista, Bigliettaio) e restituisce la scelta
 * selezionata tramite input da tastiera.
 */

public class Menu {

    /**
     * Mostra il menu iniziale dell'applicazione, permettendo all'utente
     * di scegliere tra login, registrazione, accesso come Guest o uscita.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 4)
     */

    public int mostraMenuIniziale() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU CINEMAX =====\n");
            System.out.println("1) Login\n");
            System.out.println("2) Registrazione\n");
            System.out.println("3) Accedi come Guest\n");
            System.out.println("4) Esci\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta <= 0 || scelta >= 5);

        return scelta;
    }

    /**
     * Mostra il menu dedicato agli utenti Guest, consentendo di effettuare
     * login, registrazione o ricerca della proiezione.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 4)
     */

    public int mostraMenuGuest() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU GUEST =====\n");
            System.out.println("1) Login\n");
            System.out.println("2) Registrazione\n");
            System.out.println("3) Ricerca Proiezione\n");
            System.out.println("4) ESCI\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta <= 0 || scelta > 4);

        return scelta;
    }

    /**
     * Mostra il menu dedicato agli utenti Cliente, permettendo di visualizzare
     * o gestire le proprie prenotazioni e accedere alle funzioni disponibili.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 6)
     */

    public int mostraMenuCliente() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU CLIENTE =====\n");
            System.out.println("1) Visualizza le tue prenotazioni\n");
            System.out.println("2) Aggiungi prenotazione\n");
            System.out.println("3) Modifica prenotazione\n");
            System.out.println("4) Elimina prenotazione\n");
            System.out.println("5) Visualizza proiezioni\n");
            System.out.println("6) Logout\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta <= 0 || scelta >= 7);

        return scelta;
    }

    /**
     * Mostra il menu dedicato al Proiezionista, che può gestire film e proiezioni.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 5)
     */

    public int mostraMenuProiezionista() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("===== MENU PROIEZIONISTA =====\n");
            System.out.println("1) Aggiungi film\n");
            System.out.println("2) Aggiungi proiezione\n");
            System.out.println("3) Modifica proiezione\n");
            System.out.println("4) Elimina proiezione\n");
            System.out.println("5) Logout\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta <= 0 || scelta >= 6);

        return scelta;
    }

    /**
     * Mostra il menu dedicato al Bigliettaio, che può cercare prenotazioni
     * o effettuare il logout.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 2)
     */

    public int mostraMenuBigliettaio() {
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
        } while (scelta <= 0 || scelta >= 3);

        return scelta;
    }

    /**
     * Mostra il menu dedicato alla ricerca delle proiezioni, permettendo
     * all'utente di selezionare il criterio di ricerca desiderato.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 5)
     */

    public int mostraMenuCercaProeizione() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("=== MENU CERCA PROIEZIONE ===\n");
            System.out.println("1) Ricerca per la data della Proiezione\n");
            System.out.println("2) Ricerca per il titolo del film\n");
            System.out.println("3) Ricerca per genere del film\n");
            System.out.println("4) Ricerca per costo della proiezione\n");
            System.out.println("5) Ricerca combinata\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta <= 0 || scelta >= 6);

        return scelta;
    }

    /**
     * Mostra il menu delle opzioni di ricerca per il Bigliettaio,
     * permettendo di scegliere il criterio con cui filtrare le prenotazioni
     * (per ID, nome e cognome, data o ricerca combinata).
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 4)
     */

    public int sceltaBigliettaioRicerca(){
    Scanner input = new Scanner(System.in);
    int scelta;

    do{
        System.out.println("=== RICERCA PRENOTAZIONE ===");
        System.out.println("1) Per ID");
        System.out.println("2) Per Nome e Cognome");
        System.out.println("3) Per Data");
        System.out.println("4) Ricerca Combinata");
        System.out.print("Scelta: ");

        scelta = input.nextInt();
        input.nextLine();
    }while(scelta < 1 || scelta > 5);

    return scelta;
}

    /**
     * Mostra il menu per la scelta del genere cinematografico,
     * elencando le categorie disponibili.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 11)
     */

    public int MenuSceltaGenere() {
        Scanner input = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("=== MENU SCELTA GENERE ===\n");
            System.out.println("1) ACTION\n");
            System.out.println("2) ADVENTURE\n");
            System.out.println("3) ANIMATION\n");
            System.out.println("4) BIOGRAPHY\n");
            System.out.println("5) COMEDY\n");
            System.out.println("6) CRIME\n");
            System.out.println("7) DRAMA\n");
            System.out.println("8) FILM-NOIR\n");
            System.out.println("9) HORROR\n");
            System.out.println("10) MYSTERY\n");
            System.out.println("11) WESTERN\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        } while (scelta < 1 || scelta > 11);

        return scelta;
    }

    /**
     * Mostra il menu per la ricerca delle proiezioni in base alla data,
     * permettendo di scegliere tra ricerca dopo una data
     * o in un intervallo di date.
     *
     * @return scelta selezionata dall'utente (1 = dopo una data, 2 = intervallo di date)
     */

    public int MenuRicercaData(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do{
            System.out.println("=== MENU RICERCA DATA ===\n");
            System.out.println("1) Dopo una data\n");
            System.out.println("2) Intervallo di date\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");


            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<1 || scelta>2);
        return scelta;
    }

    /**
     * Mostra il menu per la ricerca delle proiezioni in base al prezzo,
     * permettendo di scegliere tra ricerca per prezzo massimo
     * o per intervallo di prezzi.
     *
     * @return scelta selezionata dall'utente (1 = minore di un prezzo, 2 = intervallo di prezzi)
     */

    public int MenuRicercaPrezzo(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do{
            System.out.println("=== MENU RICERCA PREZZO ===\n");
            System.out.println("1) Minore di un prezzo\n");
            System.out.println("2) Intervallo di prezzi\n");
            System.out.println("========================\n");
            System.out.print("Scelta: ");


            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<1 || scelta>2);
        return scelta;
    }

    /**
     * Mostra il menu dedicato alla modifica di una prenotazione,
     * consentendo di cambiare proiezione, numero di posti o data,
     * oppure annullare l'operazione.
     *
     * @return scelta selezionata dall'utente (valore compreso tra 1 e 4)
     */

    public int MenuModificaPrenotazione(){
        Scanner input = new Scanner(System.in);
        int scelta;

        do{
            System.out.println("\n=== MODIFICA PRENOTAZIONE ===\n");
            System.out.println("1) Modifica proiezione\n");
            System.out.println("2) Modifica numero posti\n");
            System.out.println("3) Modifica la data\n");
            System.out.println("4) Annulla\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();

        }while(scelta<1 || scelta>4);

        return scelta;
    }
}
