package cinemax;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public abstract class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private LocalDate nascita;
    private String domicilio;
    private String mansione;

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
    public Utente(String nome,String cognome,String username,String password,String domicilio,String mansione){
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.domicilio=domicilio;
        this.mansione=mansione;
    }
    public Utente(){}
    //per utente guest

    //metodi set
    public void SetNome(String nome){
        this.nome=nome;
    }

    public void SetCognome(String cognome){
        this.cognome=cognome;
    }

    public void SetUsername(String username){
        this.username=username;
    }

    public void SetPassword(String password){
        this.password=password;
    }

    public void SetNascita(LocalDate nascita){
        this.nascita=nascita;
    }

    public void SetDomicilio(String domicilio){
        this.domicilio=domicilio;
    }

    //metodi get

    public String GetNome(){
        return this.nome;
    }

    public String GetCognome(){
        return this.cognome;
    }

    public String GetUsername(){
        return this.username;
    }

    public String GetPassword(){
        return this.password;
    }

    public String GetNascita(){
        if(this.nascita==null)
            return "Data di nascita non presente";
        else
            return this.nascita.toString();
    }
    public int GetAnni(){
        return Period.between(this.nascita,LocalDate.now()).getYears();
    }

    public String GetDomicilio(){
        return this.domicilio;
    }

    public String GetMansione(){return this.mansione;}

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

    public static ArrayList<Proiezione> leggiProiezioni() {
        ArrayList<Proiezione> pr = new ArrayList<>();

        String file = "File/proiezioni.csv";

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

                int ora = Integer.parseInt(datiO[0]);
                int minuti = Integer.parseInt(datiO[1]);
                int secondi = Integer.parseInt(datiO[2]);

                LocalTime time = LocalTime.of(ora, minuti, secondi);

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

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pr;
    }

    public ArrayList<Proiezione> CercaProiezione(){
        Scanner Input = new Scanner(System.in);
        Menu m =new Menu();
        LocalDate data;
        LocalDate data1;
        String titolo;
        String genere;
        float costo;
        float costo1;
        Menu menu= new Menu();
        switch (menu.mostraMenuCercaProeizione()){
            case 1:
                switch (m.MenuRicercaData()){
                    case 1:
                        data=Guest.inserisciData(Input);
                        return RicercaPerData(data);
                    case 2:
                        data=Guest.inserisciData(Input);
                        data1=Guest.inserisciData(Input);
                        if(data.isBefore(data1))
                            return RicercaPerData(data,data1);
                        return RicercaPerData(data1,data);
                }
            case 2:
                titolo = Proiezionista.inserisciTitolo(Input);
                return RicercaPerTitolo(titolo);
            case 3:
                genere = Proiezionista.inserisciGenere(Input);
                return RicercaPerGenere(genere);
            case 4:
                switch (m.MenuRicercaPrezzo()){
                    case 1:
                        costo = Proiezionista.inserisciCosto(Input);
                        RicercaPerPrezzo(costo);
                    case 2:
                        costo=Proiezionista.inserisciCosto(Input);
                        costo1=Proiezionista.inserisciCosto(Input);
                        if(costo>=costo1)
                            return RicercaPerPrezzo(costo1,costo);
                        return RicercaPerPrezzo(costo,costo1);
                }
            case 5:

        }
        return null;
    }

    public String toString(){
        return "Nome:"+this.nome+"\n" +
                "Cognome:"+this.cognome+"\n"+
                "Username:"+this.username+"\n" +
                "Password:"+this.password+"\n" +
                "Data di nascita:"+this.nascita+"\n" +
                "Domicilio:"+this.domicilio;
    }
    public static boolean CheckString(String ins, String obj){
        return obj.toLowerCase().contains(ins.toLowerCase());
    }

    public static ArrayList<Proiezione> RicercaPerTitolo(String titolo){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(CheckString(titolo,proiezione.GetFilm().getTitolo()))
                pro.add(proiezione);
        return pro;
    }

    public static ArrayList<Proiezione> RicercaPerData(LocalDate data){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(data.isBefore(proiezione.GetData()))
                pro.add(proiezione);
        return pro;
    }

    public static ArrayList<Proiezione> RicercaPerData(LocalDate data1,LocalDate data2 ){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p){
           LocalDate d = proiezione.GetData();
           if(d.isAfter(data1) && d.isBefore(data2))
               pro.add(proiezione);
        }
         return pro;
    }

    public static ArrayList<Proiezione> RicercaPerPrezzo(float prezzo){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(proiezione.GetCosto()<=prezzo)
                pro.add(proiezione);
        return pro;
    }

    public static ArrayList<Proiezione> RicercaPerPrezzo(float prezzo1,float prezzo2){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(proiezione.GetCosto()>=prezzo1 && proiezione.GetCosto()<=prezzo2)
                pro.add(proiezione);
        return pro;
    }

    public static ArrayList<Proiezione> RicercaPerGenere(String genere){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(genere.equals(proiezione.GetFilm().getGenere()))
                pro.add(proiezione);
        return pro;
    }
}
