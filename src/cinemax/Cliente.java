package cinemax;

import java.util.ArrayList;
import java.util.Date;

/**
 * Rappresenta un utente di tipo Cliente all'interno del sistema Cinemax.
 * Il cliente è l'utente finale che può effettuare operazioni come la
 * registrazione, il login e l'interazione con le funzionalità dedicate
 * alla consultazione e prenotazione.
 *
 * Queste classe estente {@link Utente} e imposta automaticamente il ruolo dell'utente come "Cliente".
 */

public class Cliente extends Utente{

    /**
     * Crea un nuovo Cliente con le informazioni anagrafiche e di accesso fornite.
     *
     * @param nome         nome dell'utente
     * @param cognome      cognome dell'utente
     * @param username     nome utente per l'accesso al sistema
     * @param password     password associata all'account
     * @param nascita      data di nascita dell'utente
     * @param domicilio    indirizzo di domicilio dell'utente
     */

    public Cliente(String nome, String cognome, String username, String password, Date nascita,String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Cliente");
    }

    public void InserisciPrenotazione(Proiezione p ){

    }
    public ArrayList<Prenotazione> LeggiPrenotazioniPersonali(){
        ArrayList<Prenotazione> personali= new ArrayList<>();
        ArrayList<Prenotazione> p = Bigliettaio.LeggiPrenotazioni();
        for(Prenotazione pren: p){
            if(this.GetUsername().equals(pren.getCliente().GetUsername()) && this.GetPassword().equals(pren.getCliente().GetPassword()))
                personali.add(pren);
        }
        return personali;
    }
    public void modificaPrenotazione(){

    }

    public void eliminaPrenotazione(){

    }
}
