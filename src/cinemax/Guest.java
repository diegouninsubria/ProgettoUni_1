package cinemax;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Rappresenta un utente Guest del sistema Cinemax.
 * Il Guest può effettuare operazioni di registrazione e login,
 * oltre a inserire i propri dati personali tramite input da tastiera.
 *
 * La classe fornisce inoltre funzionalità per la lettura e scrittura
 * dei dati dagli utenti su file, oltre a metodi di codifica e decodifica delle password.
 *
 * Estende {@link Utente} utilizzando il costruttore di default.
 */

public class Guest extends Utente{
    public static char chiave='a';
    public Guest(){
        super();
    }
    public void Registrazione(){
        //prende in input da tastiera i dati del login, effettua i controlli sui dati e li scrive nel file apposito
        String nome;
        String cognome;
        String Username;
        String Password;
        Date nascita;
        String Domicilio;
        Scanner input = new Scanner(System.in);//creazione di oggetto per lettura da tastiera
        nome=inserisciNome(input);//inserimento del nome
        cognome=inserisciCognome(input);//inserimento del cognome
        do {
            Username = inserisciUsername(input);//inserimento dell'username
        }while(CheckUsername(Username));
        Password=inserisciPassword(input);//inserimento della password
        nascita = inserisciData(input);//inserimento data di nascita dell'utente
        Domicilio=inserisciDomicilio(input);//inserimento del comicilio
        ScriviFile(nome,cognome,Username,Password,nascita,Domicilio,"Cliente");
        //il ruole nella registrazione è solo del cliente, le altre entità hanno già le credenziali già registrate
    }

    public Utente Login(){
        ArrayList<Utente> u=LeggiFile();
        Scanner input=new Scanner(System.in);
        Utente ut=null;
        boolean trovato=false;
        String username= inserisciUsername(input);
        String password=inserisciPassword(input);
        for(Utente c: u){
            if(username.equals(c.GetUsername()) && password.equals(c.GetPassword())){
                trovato=true;
                ut=c;
                break;
            }
        }
        if(trovato)
            return ut;
        else
            return null;
    }

   /* public ArrayList<Proiezione> CercaProiezione(String genere){

    }*/
    public static String inserisciNome(Scanner input) {//controlla che l'inserimento del nome non sia vuoto-> fare ulteriori controlli
        String nome;

        do {
            System.out.println("Inserisci il tuo nome: ");
            nome = input.nextLine().trim();
        } while (nome.isEmpty());

        return nome;
    }

    public static String  inserisciCognome(Scanner input){//controlla che il campo del cognome non sia vuoto->aggiungere altri controlli
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

   public static String inserisciDomicilio(Scanner input){
        String domicilio;

        do{
            System.out.println("Inserisci il domicilio: ");
            domicilio=input.nextLine().trim();
        }while(domicilio.isEmpty());

        return domicilio;
   }
        //controllo data
   public static Date inserisciData(Scanner input) {
        int giorno = 0;
        int mese = -1;
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

            while (mese < 0 || mese > 11) {
            System.out.println("Inserisci il mese: ");
            if (input.hasNextInt()) {
            mese = input.nextInt()-1;
        } else {
            input.next(); 
        }
        }

       if (mese == 3 || mese == 5 || mese == 8 || mese == 10) {GM = 30;}
       if (mese == 1) {GM = 28;}
       
        while (giorno < 1 || giorno > GM) {
            System.out.println("Inserisci il giorno: ");
            if (input.hasNextInt()) {
            giorno = input.nextInt();
        } else {
            input.next(); 
        }

        }

       input.nextLine();

        return new Date(anno , mese , giorno);
    }

        //fine controllo/inserimento data


    public static void ScriviFile(String nome,String cognome,String username,String password,Date nascita, String luogo,String ruolo){
        try{
            FileWriter writer= new FileWriter("File/Utenti.txt",true);//apre il file //effettuare controllo get di nascita
            writer.write(""+nome+","+cognome+","+username+","+EncodedPsw(password)+","+nascita.getDate()+","+nascita.getMonth()+","+nascita.getYear()+","+luogo+","+ruolo+"\n"); //scrive nel file
            writer.close();

            System.out.println("Scrittura avenuta con successo");
        }
        catch (IOException e){
            e.printStackTrace();//presenta l'errore
        }
    }
    public static ArrayList<Utente> LeggiFile(){
        ArrayList<Utente> u =new ArrayList<>();

        String file = "File/Utenti.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(file))){

            br.readLine();
            String riga;
            while((riga = br.readLine())!=null){
                String [] campi = riga.split(",");

                int giorno = Integer.parseInt(campi[4]);
                int mese = Integer.parseInt(campi[5]);
                int anno = Integer.parseInt(campi[6]);

                Date dataNascita = new Date(anno, mese, giorno);

                switch (campi[8]){
                    case "Cliente":
                        u.add(new Cliente(campi[0],campi[1],campi[2],DecodePsw(campi[3]),dataNascita,campi[7]));
                        break;
                    case "Bigliettaio":
                        u.add(new Bigliettaio(campi[0],campi[1],campi[2],DecodePsw(campi[3]),dataNascita,campi[7]));
                        break;
                    case "Proiezionista":
                        u.add(new Proiezionista(campi[0],campi[1],campi[2],DecodePsw(campi[3]),dataNascita,campi[7]));
                        break;
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return u;
    }
    public static String EncodedPsw(String Password){//codifica la password tramite la chiave comune a tuttio gli oggetti di tipo guest
        String encode="";

        for(int i=0;i<Password.length();i++){
             char c=Password.charAt(i);
             c=(char)(c*chiave);
             encode+=c;
        }
        return encode;
    }

    public static String DecodePsw(String Password){ //decodifica la password tramite la chiave comune a tutti
        String decode="";

        for(int i=0;i<Password.length();i++){
            char c=Password.charAt(i);
            c=(char)(c/chiave);
            decode+=c;
        }

        return decode;
    }
    public static boolean CheckUsername(String username){
        ArrayList<Utente> u = LeggiFile();
        for(Utente ut:u){
            if(ut.GetUsername().equals(username)){
                System.out.println("Utente già presente, utilizzare un altro username per registrarsi!\n");
                return true;
            }
        }
        return false;
    }
}