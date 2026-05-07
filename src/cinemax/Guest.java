package cinemax;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class Guest extends Utente{
    public static char chiave='a';
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
        nascita=new Date(2006,7,7);
        Domicilio=inserisciDomicilio(input);
        ScriviFile(nome,cognome,Username,Password,nascita,Domicilio,"Cliente");
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

    public static void ScriviFile(String nome,String cognome,String username,String password,Date nascita, String luogo,String ruolo){
        try{
            FileWriter writer= new FileWriter("../File/Utenti.txt",true);
            writer.write(""+nome+","+cognome+","+username+","+EncodedPsw(password)+","+nascita.toString()+","+luogo+"\n");
            writer.close();

            System.out.println("Scrittura avenuta con successo");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String EncodedPsw(String Password){
        String encode="";

        for(int i=0;i<Password.length();i++){
             char c=Password.charAt(i);
             c=(char)(c*chiave);
             encode+=c;
        }
        return encode;
    }

    public static String DecodePsw(String Password){
        String decode="";

        for(int i=0;i<Password.length();i++){
            char c=Password.charAt(i);
            c=(char)(c/chiave);
            decode+=c;
        }

        return decode;
    }
}
