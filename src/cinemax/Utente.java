package cinemax;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public abstract class Utente {

    /**
     * Classe astratta che rappresenta un utente del sistema Cinemax.
     * Contiene le informazioni anagrafiche e di accesso comuni a tutte
     * le tipologie di utenti (Cliente, Proiezionista, Bigliettaio, Guest).
     *
     * La classe fornisce metodi getter e setter per la gestione dei dati
     * personali, oltre a funzionalità condivise come la selezione di una
     * proiezione e la lettura delle proiezioni disponibili dal file.
     *
     * Le classi derivate estendono questa struttura aggiungendo comportamenti
     * specifici in base al ruolo dell’utente.
     */

    private String nome;
    private String cognome;
    private String username;
    private String password;
    private LocalDate nascita;
    private String domicilio;
    private String mansione;

    /**
     * Crea un nuovo utente con tutti i dati anagrafici e di accesso.
     *
     * @param nome       nome dell'utente
     * @param cognome    cognome dell'utente
     * @param username   username per l'accesso
     * @param password   password dell'account
     * @param nascita    data di nascita
     * @param domicilio  domicilio dell'utente
     * @param mansione   ruolo dell'utente nel sistema (Cliente, Proiezionista, ecc.)
     */

    public Utente(String nome, String cognome,String username,String password,LocalDate nascita,String domicilio,String mansione){
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.nascita=nascita;
        this.domicilio=domicilio;
        this.mansione=mansione;
        //effettuare controlli di inserimento
    }

    /**
     * Crea un utente senza specificare la data di nascita.
     * Utilizzato principalmente per utenti Guest o dati parziali.
     *
     * @param nome       nome dell'utente
     * @param cognome    cognome dell'utente
     * @param username   username per l'accesso
     * @param password   password dell'account
     * @param domicilio  domicilio dell'utente
     * @param mansione   ruolo dell'utente nel sistema
     */

    public Utente(String nome,String cognome,String username,String password,String domicilio,String mansione){
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.domicilio=domicilio;
        this.mansione=mansione;
    }

    /**
     * Costruttore vuoto utilizzato per creare un utente non inizializzato.
     */

    public Utente(){}
    //per utente guest

    //metodi set

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome nuovo nome
     */

    public void SetNome(String nome){
        this.nome=nome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome nuovo cognome
     */

    public void SetCognome(String cognome){
        this.cognome=cognome;
    }

    /**
     * Imposta lo username dell'utente.
     *
     * @param username nuovo username
     */

    public void SetUsername(String username){
        this.username=username;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password nuova password
     */

    public void SetPassword(String password){
        this.password=password;
    }

    /**
     * Imposta la data di nascita dell'utente.
     *
     * @param nascita nuova data di nascita
     */

    public void SetNascita(LocalDate nascita){
        this.nascita=nascita;
    }

    /**
     * Imposta il domicilio dell'utente.
     *
     * @param domicilio nuovo domicilio
     */

    public void SetDomicilio(String domicilio){
        this.domicilio=domicilio;
    }

    //metodi get

    /**
     * Restituisce il nome dell'utente.
     *
     * @return nome dell'utente
     */

    public String GetNome(){
        return this.nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return cognome dell'utente
     */

    public String GetCognome(){
        return this.cognome;
    }

    /**
     * Restituisce lo username dell'utente.
     *
     * @return username dell'utente
     */

    public String GetUsername(){
        return this.username;
    }

    /**
     * Restituisce la password dell'utente.
     *
     * @return password dell'utente
     */

    public String GetPassword(){
        return this.password;
    }

    /**
     * Restituisce la data di nascita come stringa.
     * Se la data non è presente, restituisce un messaggio informativo.
     *
     * @return data di nascita o messaggio alternativo
     */

    public String GetNascita(){
        if(this.nascita==null)
            return "Data di nascita non presente";
        else
            return this.nascita.toString();
    }

    /**
     * Calcola e restituisce l'età dell'utente in anni.
     *
     * @return età dell'utente
     */

    public int GetAnni(){
        return Period.between(this.nascita,LocalDate.now()).getYears();
    }

    /**
     * Restituisce il domicilio dell'utente.
     *
     * @return domicilio dell'utente
     */

    public String GetDomicilio(){
        return this.domicilio;
    }

    /**
     * Restituisce la mansione dell'utente (ruolo nel sistema).
     *
     * @return mansione dell'utente
     */

    public String GetMansione(){return this.mansione;}

    /**
     * Mostra all'utente l'elenco delle proiezioni disponibili e permette
     * di selezionarne una tramite input da tastiera.
     *
     * @param p lista delle proiezioni disponibili
     * @return proiezione selezionata dall'utente
     */

    public Proiezione SelezioneProiezione(ArrayList<Proiezione>p) {
        int scelta;
        Scanner input = new Scanner(System.in);
        int i = 1;
        do {
            i=1;
            for (Proiezione proiezione : p) {
                System.out.println(i + ") " + proiezione.toString() + "\n");
                i++;
            }
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<1 || scelta>p.size());
        return p.get(scelta-1);
    }

    /**
     * Legge il file delle proiezioni e ricostruisce la lista delle proiezioni
     * future, ignorando quelle già passate.
     *
     * @return lista delle proiezioni future presenti nel file
     */

    public static ArrayList<Proiezione> leggiProiezioni() {
        ArrayList<Proiezione> pr = new ArrayList<>();

        String file = "data/proiezioni.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine();
            String riga;

            while ((riga = br.readLine()) != null) {

                String[] campi = riga.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                for (int i = 0; i < campi.length; i++) {
                    campi[i] = campi[i].replace("\"", "").trim();
                }

                String[] dataOra = campi[0].split(" ");
                String[] datiD = dataOra[0].split("-");
                String[] datiO = dataOra[1].split(":");

                int giorno = Integer.parseInt(datiD[2]);
                int mese = Integer.parseInt(datiD[1]);
                int anno = Integer.parseInt(datiD[0]);

                LocalDate data = LocalDate.of(anno,mese,giorno);

                if(data.isAfter(LocalDate.now())) {

                    int ora = Integer.parseInt(datiO[0]);
                    int minuti = Integer.parseInt(datiO[1]);

                    LocalTime time = LocalTime.of(ora, minuti,0);

                    Film film = new Film(
                            campi[1],
                            campi[2],
                            campi[3],
                            Integer.parseInt(campi[4]),
                            Integer.parseInt(campi[5]),
                            Integer.parseInt(campi[6])
                    );

                    Proiezione p = new Proiezione(
                            data,
                            time,
                            film,
                            Float.parseFloat(campi[7])
                    );

                    pr.add(p);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pr;
    }

    /**
     * Permette all'utente di cercare proiezioni in base a diversi criteri:
     * data, intervallo di date, titolo, genere, prezzo o combinazione di essi.
     *
     * @return lista delle proiezioni che soddisfano i criteri di ricerca
     */

    public ArrayList<Proiezione> CercaProiezione(){
        Scanner Input = new Scanner(System.in);
        ArrayList<Proiezione> p = leggiProiezioni();
        Menu m =new Menu();
        LocalDate data;
        LocalDate data1;
        String titolo;
        String genere;
        float costo;
        float costo1;
        switch (m.mostraMenuCercaProiezione()){
            case 1:
                switch (m.MenuRicercaData()){
                    case 1:
                        data=Inserimenti.inserisciData(Input);
                        return RicercaPerData(data,p);
                    case 2:
                        data=Inserimenti.inserisciData(Input);
                        data1=Inserimenti.inserisciData(Input);
                        if(data.isBefore(data1))
                            return RicercaPerData(data,data1,p);
                        return RicercaPerData(data1,data,p);
                }
            case 2:
                titolo = Inserimenti.inserisciTitolo(Input);
                return RicercaPerTitolo(titolo,p);
            case 3:
                genere = Inserimenti.inserisciGenere(Input);
                return RicercaPerGenere(genere,p);
            case 4:
                switch (m.MenuRicercaPrezzo()){
                    case 1:
                        costo = Inserimenti.inserisciCosto(Input);
                        return RicercaPerPrezzo(costo,p);
                    case 2:
                        costo=Inserimenti.inserisciCosto(Input);
                        costo1=Inserimenti.inserisciCosto(Input);
                        if(costo>=costo1)
                            return RicercaPerPrezzo(costo1,costo,p);
                        return RicercaPerPrezzo(costo,costo1,p);
                }
            case 5:
            return RicercaCombinata(p);
        }
        return null;
    }

    /**
     * Restituisce una rappresentazione testuale dei dati dell'utente.
     *
     * @return stringa descrittiva dell'utente
     */

    public String toString(){
        return "Nome:"+this.nome+"\n" +
                "Cognome:"+this.cognome+"\n"+
                "Username:"+this.username+"\n" +
                "Password:"+this.password+"\n" +
                "Data di nascita:"+this.nascita+"\n" +
                "Domicilio:"+this.domicilio;
    }

    /**
     * Verifica se una stringa è contenuta in un'altra, ignorando le maiuscole.
     *
     * @param ins stringa da cercare
     * @param obj stringa in cui effettuare la ricerca
     * @return {@code true} se la stringa è contenuta, altrimenti {@code false}
     */

    public static boolean CheckString(String ins, String obj){
        return obj.toLowerCase().contains(ins.toLowerCase());
    }

    /**
     * Cerca le proiezioni il cui titolo contiene la stringa specificata.
     *
     * @param titolo titolo da cercare
     * @param p lista delle proiezioni
     * @return lista delle proiezioni corrispondenti
     */

    public static ArrayList<Proiezione> RicercaPerTitolo(String titolo,ArrayList<Proiezione> p ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(CheckString(titolo,proiezione.GetFilm().getTitolo()))
                pro.add(proiezione);
        return pro;
    }

    /**
     * Cerca le proiezioni successive alla data indicata.
     *
     * @param data data di riferimento
     * @param p lista delle proiezioni
     * @return lista delle proiezioni trovate
     */

    public static ArrayList<Proiezione> RicercaPerData(LocalDate data,ArrayList<Proiezione> p ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(data.isBefore(proiezione.GetData()))
                pro.add(proiezione);
        return pro;
    }

    /**
     * Cerca le proiezioni comprese tra due date.
     *
     * @param data1 data iniziale
     * @param data2 data finale
     * @param p lista delle proiezioni
     * @return lista delle proiezioni trovate
     */

    public static ArrayList<Proiezione> RicercaPerData(LocalDate data1,LocalDate data2,ArrayList<Proiezione> p  ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p){
           LocalDate d = proiezione.GetData();
           if(d.isAfter(data1) && d.isBefore(data2))
               pro.add(proiezione);
        }
         return pro;
    }

    /**
     * Cerca le proiezioni con costo minore o uguale al prezzo indicato.
     *
     * @param prezzo prezzo massimo
     * @param p lista delle proiezioni
     * @return lista delle proiezioni trovate
     */

    public static ArrayList<Proiezione> RicercaPerPrezzo(float prezzo,ArrayList<Proiezione> p ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(proiezione.GetCosto()<=prezzo)
                pro.add(proiezione);
        return pro;
    }

    /**
     * Cerca le proiezioni con costo compreso tra due valori.
     *
     * @param prezzo1 prezzo minimo
     * @param prezzo2 prezzo massimo
     * @param p lista delle proiezioni
     * @return lista delle proiezioni trovate
     */

    public static ArrayList<Proiezione> RicercaPerPrezzo(float prezzo1,float prezzo2,ArrayList<Proiezione> p ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(proiezione.GetCosto()>=prezzo1 && proiezione.GetCosto()<=prezzo2)
                pro.add(proiezione);
        return pro;
    }

    /**
     * Cerca le proiezioni appartenenti al genere specificato.
     *
     * @param genere genere da cercare
     * @param p lista delle proiezioni
     * @return lista delle proiezioni trovate
     */

    public static ArrayList<Proiezione> RicercaPerGenere(String genere,ArrayList<Proiezione> p ){
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(genere.equals(proiezione.GetFilm().getGenere()))
                pro.add(proiezione);
        return pro;
    }

    /**
     * Permette di applicare più filtri di ricerca in sequenza:
     * data, intervallo di date, titolo, genere, prezzo o intervallo di prezzo.
     *
     * @param p lista delle proiezioni da filtrare
     * @return lista delle proiezioni che soddisfano tutti i filtri applicati
     */

    public static ArrayList<Proiezione>RicercaCombinata(ArrayList<Proiezione> p){
        ArrayList<Proiezione> risultato = p;
        Scanner input = new Scanner(System.in);
        System.out.println("=== RICERCA COMBINATA ===");

        System.out.println("Vuoi filtrare per data (s/n)");
        boolean filtroData = input.nextLine().trim().equalsIgnoreCase("s");
        LocalDate d1;
        if(filtroData){
            d1= Inserimenti.inserisciData(input);
            risultato = RicercaPerData(d1,risultato);
        }

        System.out.println("Vuoi filtrare per intervallo di date (s/n)");
        boolean filtroIntervalloData = input.nextLine().trim().equalsIgnoreCase("s");
        LocalDate d2;
        if(filtroIntervalloData){
            d1= Inserimenti.inserisciData(input);
            d2= Inserimenti.inserisciData(input);
            if(d1.isAfter(d2))
                risultato=RicercaPerData(d2,d1,risultato);
            else
                risultato=RicercaPerData(d1,d2,risultato);
        }

        System.out.println("Vuoi filtrare per titolo del film (s/n)");
        boolean filtroTitolo = input.nextLine().trim().equalsIgnoreCase("s");
        String titolo;
        if(filtroTitolo){
            titolo = Inserimenti.inserisciTitolo(input);
            risultato = RicercaPerTitolo(titolo,risultato);
        }

        System.out.println("Vuoi filtrare per genere del film (s/n)");
        boolean filtroGenere = input.nextLine().trim().equalsIgnoreCase("s");
        String genere;
        if(filtroGenere){
            genere = Inserimenti.inserisciGenere(input);
            risultato = RicercaPerGenere(genere,risultato);
        }

        System.out.println("Vuoi filtrare per prezzo della proiezione (s/n)");
        boolean filtroPrezzo = input.nextLine().trim().equalsIgnoreCase("s");
        float prezzo;
        if(filtroPrezzo){
            prezzo = Inserimenti.inserisciCosto(input);
            risultato = RicercaPerPrezzo(prezzo,risultato);
        }

        System.out.println("Vuoi filtrare per intervallo di prezzo della proiezione(s/n)");
        boolean filtroIntervalloPrezzo = input.nextLine().trim().equalsIgnoreCase("s");
        float prezzo1;
        if(filtroIntervalloPrezzo){
            prezzo = Inserimenti.inserisciCosto(input);
            prezzo1 = Inserimenti.inserisciCosto(input);
            if(prezzo>prezzo1)
                risultato = RicercaPerPrezzo(prezzo1,prezzo,risultato);
            else
                risultato = RicercaPerPrezzo(prezzo,prezzo1,risultato);
        }

        return risultato;
    }
}
