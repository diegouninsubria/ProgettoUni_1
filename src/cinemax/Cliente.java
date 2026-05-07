package cinemax;

import java.util.Date;

public class Cliente extends Utente{
    public Cliente(String nome, String cognome, String username, String password, Date nascita,String domicilio){
        super(nome,cognome,username,password,nascita,domicilio);
    }
}
