package cinemax;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

 /**
        * Classe di utilità che raccoglie tutti i metodi dedicati all'inserimento
        * e alla validazione dei dati forniti dall'utente tramite tastiera.
        *
        * I metodi gestiscono l'inserimento di informazioni relative ai film,
        * alle proiezioni e agli utenti, garantendo che i valori inseriti
 * rispettino i vincoli richiesti dal sistema CinemaX.
 */

public class   Inserimenti {

    //inserimenti per film

     /**
      * Richiede e valida l'inserimento del titolo di un film.
      *
      * @param input scanner per la lettura da tastiera
      * @return titolo del film
      */

    public static String inserisciTitolo(Scanner input){
        String titolo;

        do {
            System.out.println("Inserisci il titolo del film: ");
            titolo = input.nextLine().trim();
        } while (titolo.isEmpty());

        return titolo;
    }

     /**
      * Permette di selezionare il genere del film tramite menu dedicato.
      *
      * @param input scanner per la lettura da tastiera
      * @return genere selezionato
      */

    public static String inserisciGenere(Scanner input){
        Menu m = new Menu();
        switch (m.MenuSceltaGenere()){
            case 1:
                return "Action";
            case 2 :
                return "Adventure";
            case 3:
                return "Animation";
            case 4 :
                return "Biography";
            case 5:
                return "Comedy";
            case 6 :
                return "Crime";
            case 7:
                return "Drama";
            case 8 :
                return "Film-Noir";
            case 9:
                return "Horror";
            case 10 :
                return "Mistery";
            case 11:
                return "Western";
        }
        return null;
    }

     /**
      * Richiede e valida l'inserimento del nome del regista.
      *
      * @param input scanner per la lettura da tastiera
      * @return nome del regista
      */

    public static String inserisciRegista(Scanner input){
        String regista;

        do {
            System.out.println("Inserisci il regista del film: ");
            regista = input.nextLine().trim();
        } while (regista.isEmpty());

        return regista;
    }

     /**
      * Richiede e valida l'anno di uscita del film.
      *
      * @param input scanner per la lettura da tastiera
      * @return anno del film
      */

    public static int inserisciAnno(Scanner input){
        int anno;

        do {
            System.out.println("Inserisci l'anno del film ");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            anno = input.nextInt();
        } while (anno<=1950 || anno>=2026);

        return anno;
    }

     /**
      * Richiede e valida la durata del film in minuti.
      *
      * @param input scanner per la lettura da tastiera
      * @return durata del film
      */

    public static int  inserisciDurata(Scanner input){
        int durata;

        do {
            System.out.println("Inserisci la durata del film ");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            durata = input.nextInt();
        } while (durata<0 ||durata >300);

        return durata;
    }

     /**
      * Richiede e valida l'età minima per la visione del film.
      *
      * @param input scanner per la lettura da tastiera
      * @return età minima richiesta
      */

    public static int inserisciEtaMinima(Scanner input){
        int eta;

        do {
            System.out.println("Inserisci l'eta minima per visionare il film ");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            eta = input.nextInt();
        } while (eta<0 || eta>19);

        return eta;
    }

     /**
      * Richiede e valida l'orario di inizio della proiezione.
      *
      * @param input scanner per la lettura da tastiera
      * @return orario della proiezione
      */

    public static LocalTime inserisciOra(Scanner input){
        int ora,minuti;

        do {
            System.out.println("Inserisci l'ora in cui inizierà la proiezione ");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            ora = input.nextInt();
        } while (ora<0 || ora>=25);

        do {
            System.out.println("Inserisci il minuto in cui inizierà la proiezione ");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next(); // scarta input sbagliato
            }
            minuti = input.nextInt();
        } while (minuti<0 || minuti>=60);

        return LocalTime.of(ora,minuti,00);
    }

     /**
      * Richiede e valida il costo della proiezione.
      *
      * @param input scanner per la lettura da tastiera
      * @return costo della proiezione
      */

    public static float inserisciCosto(Scanner input){
        float costo;

        do {
            System.out.println("Inserisci il costo per la proiezione ");
            while (!input.hasNextFloat()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            costo = input.nextFloat();
        } while (costo<=0.0);

        return costo;
    }

    //inserimenti per utenti

    /**
     * Richiede e valida l'inserimento del nome da tastiera.
     *
     * @param input scanner per la lettura da tastiera
     * @return nome inserito dall'utente
     */

    public static String inserisciNome(Scanner input) {//controlla che l'inserimento del nome non sia vuoto-> fare ulteriori controlli
        String nome;

        do {
            System.out.println("Inserisci il tuo nome: ");
            nome = input.nextLine().trim();
        } while (nome.isEmpty());

        return nome;
    }

    /**
     * Richiede e valida l'inserimento del cognome da tastiera.
     *
     * @param input scanner per la lettura da tastiera
     * @return cognome inserito dall'utente
     */

    public static String  inserisciCognome(Scanner input){//controlla che il campo del cognome non sia vuoto->aggiungere altri controlli
        String cognome;

        do{
            System.out.println("Inserisci il tuo cognome: ");
            cognome = input.nextLine().trim();
        }while(cognome.isEmpty());

        return cognome;
    }

    /**
     * Richiede e valida l'inserimento dell'username.
     *
     * @param input scanner per la lettura da tastiera
     * @return username inserito dall'utente
     */

    public static String inserisciUsername(Scanner input){
        String username;

        do{
            System.out.println("Inserisci il tuo username: ");
            username=input.nextLine().trim();
        }while(username.isEmpty());

        return username;
    }

    /**
     * Richiede e valida l'inserimento della password.
     * La password deve avere almeno 4 caratteri.
     *
     * @param input scanner per la lettura da tastiera
     * @return password valida inserita dall'utente
     */

    public static String inserisciPassword(Scanner input){
        String password;
        System.out.println("La password deve essere lunga almeno 4 caratteri!");

        do{
            System.out.println("Inserisci la password: ");
            password=input.nextLine().trim();
        }while(password.length()<4);

        return password;
    }

    /**
     * Richiede e valida l'inserimento del domicilio.
     *
     * @param input scanner per la lettura da tastiera
     * @return domicilio inserito dall'utente
     */

    public static String inserisciDomicilio(Scanner input){
        String domicilio;

        do{
            System.out.println("Inserisci il domicilio: ");
            domicilio=input.nextLine().trim();
        }while(domicilio.isEmpty());

        return domicilio;
    }

    /**
     * Richiede e valida l'inserimento della data di nascita.
     *
     * @param input scanner per la lettura da tastiera
     * @return data di nascita come oggetto {@link LocalDate}
     */

    //controllo data
    public static LocalDate inserisciData(Scanner input) {
        int giorno = 0;
        int mese = 0;
        int anno = 0;
        int GM = 31;
        while (anno < 1900 || anno > 2026) {
            System.out.println("Inserisci l'anno: ");
            if (input.hasNextInt()) {
                anno = input.nextInt();
            } else {
                input.next();
            }
        }

        while (mese < 1 || mese > 12) {
            System.out.println("Inserisci il mese: ");
            if (input.hasNextInt()) {
                mese = input.nextInt();
            } else {
                input.next();
            }
        }

        if (mese == 4 || mese == 6 || mese == 9 || mese == 11) {GM = 30;}
        if (mese == 2) {GM = 28;}

        while (giorno < 1 || giorno > GM) {
            System.out.println("Inserisci il giorno: ");
            if (input.hasNextInt()) {
                giorno = input.nextInt();
            } else {
                input.next();
            }

        }

        input.nextLine();

        return LocalDate.of(anno,mese,giorno);
    }

     /**
      * Richiede e valida il numero di posti da prenotare.
      *
      * @param input scanner per la lettura da tastiera
      * @return numero di posti richiesti
      */

    public static int InserisciPosti(Scanner input){
        int posti;

        do {
            System.out.println("Inserisci il numero di posti da prenotare");
            while (!input.hasNextInt()) {
                System.out.println("Errore: devi inserire un numero!");
                input.next();
            }
            posti = input.nextInt();
        } while (posti<=0 || posti>=201);

        return posti;
    }
}
