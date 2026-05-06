package cinemax;

import java.util.*;
public abstract class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private Date nascita;
    private String domicilio;

    public Utente(String nome, String cognome,String username,String password,Date nascita,String domicilio){
        this.nome=nome;
        this.cognome=cognome;
        this.username=username;
        this.password=password;
        this.nascita=nascita;
        this.domicilio=domicilio;
        //effettuare controlli di inserimento
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

    public String toString(){
        return "Nome:"+this.nome+"\n" +
                "Cognome:"+this.cognome+"\n"+
                "Username:"+this.username+"\n" +
                "Password:"+this.password+"\n" +
                "Data di nascita:"+this.nascita+"\n" +
                "Domicilio:"+this.domicilio;
    }
}
