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
        nascita = inserisciData(input);
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

        //controllo data
   public static Date inserisciData(Scanner input) {
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

       if (mese == 4 || mese == 6 || mese == 9 || mese == 11) {GM = 30} 
       if (mese == 2) {GM = 28}
       
        while (giorno < 1 || giorno > GM) {
            System.out.println("Inserisci il giorno: ");
            if (input.hasNextInt()) {
            giorno = input.nextInt();
        } else {
            input.next(); 
        }

        }

       input.nextLine();

        return new Date(anno - 1900, mese - 1, giorno);
    }

        //fine controllo/inserimento data


    public static void ScriviFile(String nome,String cognome,String username,String password,Date nascita, String luogo,String ruolo){
        try{
            FileWriter writer= new FileWriter("File/utenti.txt",true);
            writer.write(""+nome+","+cognome+","+username+","+EncodedPsw(password)+","+nascita.toString()+","+luogo+","+ruolo+"\n");
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
