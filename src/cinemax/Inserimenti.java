package cinemax;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;

public class Inserimenti {

    //inserimenti per film
    public static String inserisciTitolo(Scanner input){
        String titolo;

        do {
            System.out.println("Inserisci il titolo del film: ");
            titolo = input.nextLine().trim();
        } while (titolo.isEmpty());

        return titolo;
    }

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

    public static String inserisciRegista(Scanner input){
        String regista;

        do {
            System.out.println("Inserisci il regista del film: ");
            regista = input.nextLine().trim();
        } while (regista.isEmpty());

        return regista;
    }

    public static int inserisciAnno(Scanner input){
        int anno;

        do {
            System.out.println("Inserisci l'anno del film ");
            anno = input.nextInt();
        } while (anno<=1950 || anno>=2026);

        return anno;
    }

    public static int  inserisciDurata(Scanner input){
        int durata;

        do {
            System.out.println("Inserisci la durata del film ");
            durata = input.nextInt();
        } while (durata<0 ||durata >300);

        return durata;
    }

    public static int inserisciEtaMinima(Scanner input){
        int eta;

        do {
            System.out.println("Inserisci l'eta minima per visionare il film ");
            eta = input.nextInt();
        } while (eta<0 || eta>19);

        return eta;
    }
    public static LocalTime inserisciOra(Scanner input){
        int ora,minuti;

        do {
            System.out.println("Inserisci l'ora in cui inizierà la proiezione ");
            ora = input.nextInt();
        } while (ora<0 || ora>=25);

        do {
            System.out.println("Inserisci il minuto in cui inizierà la proiezione ");
            minuti = input.nextInt();
        } while (minuti<0 || minuti>=60);

        return LocalTime.of(ora,minuti,00);
    }

    public static float inserisciCosto(Scanner input){
        float costo;

        do {
            System.out.println("Inserisci il costo per la proiezione ");
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
     * Richiede e valida l'insserimento della password.
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
     * @return data di nascita come oggetto {@link Date}
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

    public static int InserisciPosti(Scanner input){
        int posti;

        do {
            System.out.println("Inserisci il numero di posti da prenotare");
            posti = input.nextInt();
        } while (posti<=0 || posti>=201);

        return posti;
    }
}
