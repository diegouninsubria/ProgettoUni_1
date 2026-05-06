package cinemax;

import java.util.Date;

public class Cliente extends Utente{
    public Cliente(String nome, String cognome, String username, String password, Date nascita){
        super(nome,cognome,username,password,nascita);
    }
}
