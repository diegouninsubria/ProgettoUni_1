package cinemax;

public class Cinemax {
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
                        break;

                    case 2:
                        g.Registrazione();
                        break;

                    case 3:
                        System.out.println("Funzione ricerca proiezioni (da implementare)");
                        break;
                }
                break;
        }
    }
}
