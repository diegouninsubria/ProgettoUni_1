package cinemax;
import java.util.*;
public class Guest extends Utente{
    public Guest(){
        super();
    }
    public void Registrazione(){
        String nome;
        String cognome;
        String Username;
        String Password;
        Date nascita;
        String Domicilio;
        Scanner input = new Scanner(System.in);
        nome=inserisciNome(input);
        cognome=inserisciCognome(input);
        Username=inserisciUsername(input);
        Password=inserisciPassword(input);
        //nascita=inserisciData(input);
        Domicilio=inserisciDomicilio(input);
        //scrittura su file + creazione direttamente di un oggetto cliente?
    }
    public static String inserisciNome(Scanner input) {
        String nome;

        do {
            System.out.println("Inserisci il tuo nome: ");
            nome = input.nextLine().trim();
        } while (nome.isEmpty());

        return nome;
    }

    public static String  inserisciCognome(Scanner input){
        String cognome;

        do{
            System.out.println("Inserisci il tuo cognome: ");
            cognome = input.nextLine().trim();
        }while(cognome.isEmpty());

        return cognome;
    }

    public static String inserisciUsername(Scanner input){
        String username;

        do{
            System.out.println("Inserisci il tuo username: ");
            username=input.nextLine().trim();
        }while(username.isEmpty());

        return username;
    }

    public static String inserisciPassword(Scanner input){
        String password;
        System.out.println("La password deve essere lunga almeno 4 caratteri!");

        do{
            System.out.println("Inserisci la password: ");
            password=input.nextLine().trim();
        }while(password.isEmpty() || password.length()<4);

        return password;
    }

   // public static String inserisciData(Scanner input){
        //decide se è meglio usare Local data che controlla effettivamente se la data in questione vada bene o meno
    //}

    public static String inserisciDomicilio(Scanner input){
        String domicilio;

        do{
            System.out.println("inserisci domicilio: ");
            domicilio=input.nextLine().trim();
        }while(domicilio.isEmpty());

        return domicilio;
    }
}
