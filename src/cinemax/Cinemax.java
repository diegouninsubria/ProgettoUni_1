package cinemax;

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

        int scelta = menu.mostraMenuIniziale();

        switch(scelta){

            case 1: 
                ut = g.Login();
                if(ut == null){
                    System.out.println("Utente non presente");
                } else {
                    System.out.println("Ti sei loggato come:");
                    System.out.println(ut.toString());
                
                 if(ut instanceof Cliente){
                        int sceltaCliente = menu.mostraMenuCliente();

                        switch(sceltaCliente){
                            case 1:
                                System.out.println("Visualizza prenotazioni (da implementare)");
                                break;

                            case 2:
                                System.out.println("Aggiungi prenotazione (da implementare)");
                                break;

                            case 3:
                                System.out.println("Modifica proiezione (da implementare)");
                                break;

                            case 4:
                                System.out.println("Elimina proiezione (da implementare)");
                                break;

                            case 5:
                                System.out.println("Visualizza proiezioni (da implementare)");
                                break;

                            case 6:
                                System.out.println("Logout...");
                                break;
                        }
                    }
                    else if(ut instanceof Bigliettaio){
                        int sceltaBig = menu.mostraMenuBigliettaio();

                        switch(sceltaBig){
                            case 1:
                                System.out.println("Cerca prenotazione (da implementare)");
                                break;

                            case 2:
                                System.out.println("Logout...");
                                break;
                        }
                    }
                    else if(ut instanceof Proiezionista){
                        int sceltaP = menu.mostraMenuProiezionista();

                        switch(sceltaP){
                            case 1:
                                System.out.println("Aggiungi film (da implementare)");
                                break;

                            case 2:
                                System.out.println("Aggiungi proiezione (da implementare)");
                                break;

                            case 3:
                                System.out.println("Modifica proiezione (da implementare)");
                                break;

                            case 4:
                                System.out.println("Elimina proiezione (da implementare)");
                                break;

                            case 5:
                                System.out.println("Logout...");
                                break;
                        }
                    }
                }
                break;

            case 2: 
                g.Registrazione();
                break;

            case 3: 
                System.out.println("Accesso come Guest...");
                int sceltaGuest = menu.mostraMenuGuest();

                switch(sceltaGuest){
                    case 1:
                        ut = g.Login();
                        if(ut == null){
                            System.out.println("Utente non presente");
                        } else {
                            System.out.println("Ti sei loggato come:");
                            System.out.println(ut.toString());

                            if(ut instanceof Cliente)
                                switch (menu.mostraMenuCliente()){

                                }
                            else if(ut instanceof Bigliettaio)
                                switch (menu.mostraMenuBigliettaio()){

                                }
                            else if(ut instanceof Proiezionista)
                                switch (menu.mostraMenuProiezionista()){

                                }
                        }
                    case 2:
                        g.Registrazione();
                        break;

                    case 3:
                        System.out.println("Funzione ricerca proiezioni (da implementare)");
                        break;
                }
                break;

            case 4:
                System.out.println("Uscita dal programma...");
                System.exit(0);
                break;
        }
    }
}

