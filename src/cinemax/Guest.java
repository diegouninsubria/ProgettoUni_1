package cinemax;
import java.time.LocalDate;
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

    /** Chiave utilizzata per la codifica e decodifica delle password */

    public static char chiave='a';

    /**
     * Costruttore di default che inizializza un Guest tramite il costruttore
     * vuoto della classe {@link Utente}.
     */

    public Guest(){
        super();
    }

    /**
     * Gestisce la procedura di registrazione di un nuovo utente Cliente.
     * Richiede i dati tramite input da tastiera, effettua controlli di validità
     * e salva le informazioni nel file degli utenti.
     */

    public void Registrazione(){
        //prende in input da tastiera i dati del login, effettua i controlli sui dati e li scrive nel file apposito
        String nome;
        String cognome;
        String Username;
        String Password;
        LocalDate nascita;
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

    /**
     * Esegue la procedura di login confrontando username e password
     * con i dati presenti nel file degli utenti.
     *
     * @return un oggetto {@link Utente} se le credenziali sono valide,
     * altrimenti {@code null}
     */

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

        //fine controllo/inserimento data

    /**
     * Scrive nel file degli utenti una nuova riga contenente i dati
     * dell'utente registrato.
     *
     * @param nome        nome dell'utente
     * @param cognome     cognome dell'utente
     * @param username    username scelto
     * @param password    password codificata
     * @param nascita     data di nascita
     * @param luogo       domicilio dell'utente
     * @param ruolo       ruolo dell'utente (Cliente, Bigliettaio, Proiezionista)
     */

    public static void ScriviFile(String nome,String cognome,String username,String password,LocalDate nascita, String luogo,String ruolo){
        try{
            FileWriter writer= new FileWriter("File/Utenti.txt",true);//apre il file //effettuare controllo get di nascita
            writer.write(""+nome+","+cognome+","+username+","+EncodedPsw(password)+","+nascita.getDayOfMonth()+","+nascita.getMonthValue()+","+nascita.getYear()+","+luogo+","+ruolo+"\n"); //scrive nel file
            writer.close();

            System.out.println("Scrittura avenuta con successo");
        }
        catch (IOException e){
            e.printStackTrace();//presenta l'errore
        }
    }

    /**
     * Legge il file degli utenti e ricostruisce una lista di oggetti
     * {@link Utente} in base al ruolo indicato nel file.
     *
     * @return lista degli utenti presenti nel file
     */

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

                LocalDate dataNascita = LocalDate.of(anno,mese,giorno);

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

    /**
     * Codifica una password utilizzando una chiave comune.
     *
     * @param Password password in chiaro
     * @return password codificata
     */

    public static String EncodedPsw(String Password){//codifica la password tramite la chiave comune a tuttio gli oggetti di tipo guest
        String encode="";

        for(int i=0;i<Password.length();i++){
             char c=Password.charAt(i);
             c=(char)(c*chiave);
             encode+=c;
        }
        return encode;
    }

    /**
     * Decodifica password precedentemente codificata.
     *
     * @param Password password codificata
     * @return password in chiaro
     */

    public static String DecodePsw(String Password){ //decodifica la password tramite la chiave comune a tutti
        String decode="";

        for(int i=0;i<Password.length();i++){
            char c=Password.charAt(i);
            c=(char)(c/chiave);
            decode+=c;
        }

        return decode;
    }

    /**
     * Controlla se uno username è già presente nel file degli utenti.
     *
     * @param username username da verificare
     * @return {@code true} se lo username è già utilizzato
     */

    public static boolean CheckUsername(String username){
        ArrayList<Utente> u = LeggiFile();
        if(!u.isEmpty()) {
            for (Utente ut : u) {
                if (ut.GetUsername().equals(username)) {
                    System.out.println("Utente già presente, utilizzare un altro username per registrarsi!\n");
                    return true;
                }
            }
        }
        return false;
    }
}