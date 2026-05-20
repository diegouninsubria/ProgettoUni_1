package cinemax;

import java.time.LocalTime;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public abstract class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Date nascita;
    private String domicilio;
    private String mansione;

    public Utente(String nome, String cognome,String username,String password,Date nascita,String domicilio,String mansione){
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

    public void SetNascita(Date nascita){
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

    public String GetDomicilio(){
        return this.domicilio;
    }

    public String GetMansione(){return this.mansione;}

    public static ArrayList<Proiezione> leggiProiezioni(){
        ArrayList<Proiezione> pr = new ArrayList<>();

        String file="File/proiezioni.csv";
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine();
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                String[] dataOra = campi[0].split(" ");
                String[] datiD=dataOra[0].split("-");
                String[] datiO=dataOra[1].split(":");

                int giorno = Integer.parseInt(datiD[2]);
                int mese = Integer.parseInt(datiD[1]);
                int anno = Integer.parseInt(datiD[0]);
                Date data = new Date(anno,mese,giorno);

                int ora = Integer.parseInt(datiO[0]);
                int minuti=Integer.parseInt(datiO[1]);
                int secondi=Integer.parseInt(datiO[2]);
                LocalTime time = LocalTime.of(ora,minuti,secondi);

                Film film= new Film(campi[1],campi[2],campi[3],Integer.parseInt(campi[4]),Integer.parseInt(campi[5]),Integer.parseInt(campi[6]));
                Proiezione p =new Proiezione(data,time,film,Float.parseFloat(campi[7]));
                pr.add(p);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return pr;
    }

    public ArrayList<Proiezione> CercaProiezione(){
        Scanner Input = new Scanner(System.in);
        Date data;
        String titolo;
        String genere;
        int costo;
        Menu menu= new Menu();
        switch (menu.mostraMenuCercaProeizione()){
            case 1:

                break;
            case 2:
                titolo = Proiezionista.inserisciTitolo(Input);
                return RicercaPerTitolo(titolo);
                break;
            case 3:
                genere = Proiezionista.inserisciGenere(Input);
                return RicercaPerGenere(genere);
                break;
            case 4:
                break;
            case 5:
                break;

        }
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

    public static ArrayList<Proiezione> RicercaPerOra(Date data){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p)
            if(proiezione.GetData().compareTo(data)>=0)
                pro.add(proiezione);
        return pro;
    }

    public static ArrayList<Proiezione> RicercaPerOra(Date data1,Date data2 ){
        ArrayList<Proiezione> p =leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();
        for(Proiezione proiezione : p){
           Date d = proiezione.GetData();
           if(d.compareTo(data1)>=0 && d.compareTo(data2)<=0)
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
