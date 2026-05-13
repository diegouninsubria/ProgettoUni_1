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
        ArrayList<Proiezione> p = new ArrayList<>();
        ArrayList<Proiezione> proiezioni=leggiProiezioni();
        Date data;
        LocalTime ora;
        String titolo;
        String regista;
        String genere;
        int durata;
        int anno;
        int costo;
        int etaMinima;
        Menu menu= new Menu();
        switch (menu.mostraMenuCercaProeizione()){

        }
        return p;
    }

    public String toString(){
        return "Nome:"+this.nome+"\n" +
                "Cognome:"+this.cognome+"\n"+
                "Username:"+this.username+"\n" +
                "Password:"+this.password+"\n" +
                "Data di nascita:"+this.nascita+"\n" +
                "Domicilio:"+this.domicilio;
    }
}
