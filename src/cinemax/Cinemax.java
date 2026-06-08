package cinemax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

/**
 * Classe principale dell'applicazione Cinemax.
 * Gestisce il flusso iniziale del programma mostrando il menu principale
 * e permettendo all'utente di effettuare il login, la registrazione
 * oppure accedere come Guest.
 *
 * Il metodo {@link Cinemax#main(String[])} rappresenta il punto d'ingresso
 * dell'applicazione e coordina le interazioni tra {@link Menu}, {@link Guest}
 * e {@link Utente}.
 */

public class Cinemax {

    /**
     * Avvia l'applicazione Cinemax mostrando il menù iniziale e gestendo
     * le scelte dell'utente, come login, registrazione o accesso come Guest.
     * @param args    argomenti passati da linea di comando non utilizzati.
     */

    public static void main(String args[]) {

        Menu menu = new Menu();
        Guest g = new Guest();
        Utente ut = null;
        int scelta;
        boolean uscita = false;

        do{
            scelta = menu.mostraMenuIniziale();

            switch (scelta){
                case 1:
                    ut = g.Login();
                    if(ut == null){
                        System.out.println("Utente non presente");
                    } else {
                        System.out.println("Ti sei loggato come:");
                        System.out.println(ut.toString());
                    }
                    break;
                case 2:
                    g.Registrazione();
                    break;
                case 3:
                    ut = new Guest();
                    break;
                case 4:
                    System.out.println("Uscita dal programma...");
                    System.exit(0);
                    break;
            }

        }while (ut == null);
        do{
            if(ut instanceof Guest){
                do {
                    switch (menu.mostraMenuGuest()) {
                        case 1:
                            ut = g.Login();
                            if(ut == null){
                                System.out.println("Utente non presente");
                            } else {
                                System.out.println("Ti sei loggato come:");
                                System.out.println(ut.toString());
                            }
                            break;
                        case 2:
                            g.Registrazione();
                            break;
                        case 3:
                            ArrayList<Proiezione> p = ut.CercaProiezione();
                            if(p.isEmpty())
                                System.out.println("Non ci sono proiezioni che rispettino le scelte dell'utente!\n");
                            else
                               System.out.println(ut.SelezioneProiezione(p));
                            break;
                        case 4:
                            System.out.println("Uscita dal programma...");
                            System.exit(0);
                            break;
                    }
                }while(ut instanceof Guest);//controllare condizione
            }
            else if (ut instanceof Cliente){
                Cliente c = (Cliente) ut;
                do{
                    switch (menu.mostraMenuCliente()){
                        case 1:
                            ArrayList<Prenotazione> p = c.LeggiPrenotazioniPersonali();
                            if(p.isEmpty())
                                System.out.println("Non hai Prenotazioni!");
                            else
                                for(Prenotazione pren : p){
                                    System.out.println(pren.VisualizzaPrenotazione()+"\n");
                                }
                            break;
                        case 2:
                            ArrayList<Proiezione> pro = c.ProiezioniDisponibili();
                            c.InserisciPrenotazione(c.SelezioneProiezione(pro));
                            break;
                        case 3:
                            System.out.println("Caricamento...\n");
                            c.modificaPrenotazione();
                            break;
                        case 4:
                            c.eliminaPrenotazione();
                            break;
                        case 5:
                            ArrayList<Proiezione> proiezioni = c.CercaProiezione();
                            if(proiezioni.isEmpty())
                                System.out.println("Non ci sono proiezioni che rispettino le scelte dell'utente!\n");
                            else
                                System.out.println(c.SelezioneProiezione(proiezioni).toString());
                            break;
                        case 6:
                            ut = new Guest();
                            break;
                    }
                }while(ut instanceof Cliente);
            }
            else if(ut instanceof Bigliettaio){

    Bigliettaio b = (Bigliettaio) ut;
    Scanner input = new Scanner(System.in);

    do {
        switch (menu.mostraMenuBigliettaio()) {

            case 1:

                ArrayList<Prenotazione> pren = new ArrayList<>();
                ArrayList<Prenotazione> p = Bigliettaio.LeggiPrenotazioni();

                switch (menu.sceltaBigliettaioRicerca()){

                    case 1:
                        System.out.print("Inserisci ID: ");
                        int id = input.nextInt();
                        input.nextLine();
                        pren = b.RicercaPrenotazione(id,p);
                        break;

                    case 2:
                        String nome = Inserimenti.inserisciNome(input);
                        String cognome = Inserimenti.inserisciCognome(input);
                        pren = b.RicercaPrenotazione(nome, cognome,p);
                        break;

                    case 3:
                        switch (menu.MenuRicercaData()){
                            case 1:
                                LocalDate d = Inserimenti.inserisciData(input);
                                pren = b.RicercaPrenotazione(d,p);
                                break;
                            case 2:
                                LocalDate d1 = Inserimenti.inserisciData(input);
                                LocalDate d2 = Inserimenti.inserisciData(input);
                                if(d1.isBefore(d2))
                                    pren = b.RicercaPrenotazione(d1,d2,p);
                                else
                                    pren = b.RicercaPrenotazione(d2,d1,p);

                                break;
                        }
                        break;

                    case 4:
                        pren = b.RicercaPrenotazioneCombinata();
                        break;
                }

                if(pren.isEmpty())
                    System.out.println("Non ci sono prenotazioni con questi campi inseriti");
                else
                    System.out.println(b.ScegliPrenotazione(pren).VisualizzaPrenotazione());

                break;

            case 2:
                ut = new Guest();
                break;
        }

    }while(ut instanceof Bigliettaio);
}

            else{//proiezionista
                Proiezionista p = (Proiezionista) ut ;
                do {
                    switch (menu.mostraMenuProiezionista()) {
                        case 1: p.InserisciFilm();
                            break;
                        case 2:
                            ArrayList <Film> film = Proiezionista.leggiFilm();
                            p.InserisciProiezione(p.selezionaFilm(film));
                            break;
                        case 3:
                            p.modificaProiezione();
                            break;
                        case 4:
                            p.eliminaProiezione();
                            break;
                        case 5:
                            ut= new Guest();
                            break;
                    }
                }while(ut instanceof Proiezionista);
            }

        }while(!uscita);
    }
}