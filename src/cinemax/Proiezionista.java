package cinemax;

import java.util.Date;

public class Proiezionista extends Utente{
    public Proiezionista(String nome, String cognome, String username, String password, Date nascita,String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Proiezionista");
    }
}
