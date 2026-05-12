package cinemax;

import java.util.Date;

/**
 * Rappresenta un utente di tipo Bigliettaio all'interno del sistema Cinemax.
 * Il Bigliettaio è responsabile della gestione dei biglietti, come emissione,
 * controllo e operazioni correlate alla vendita.
 *
 * Questa classe estende {@link Utente} e imposta il ruolo dell'utente coe "Bigliettaio".
 */

public class Bigliettaio extends Utente{

    /**
     * Crea un nuovo "Bigliettaio" con le informazioni anagrafiche e di accesso fornite.
     *
     * @param nome         nome dell'utente
     * @param cognome      cognome dell'utente
     * @param username     nome utente per l'accesso al sistema
     * @param password     password associata all'account
     * @param nascita      data di nascita dell'utente
     * @param domicilio    indirizzo di domicilio dell'utente
     */

    public Bigliettaio(String nome, String cognome, String username, String password, Date nascita,String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Bigliettaio");
    }
}
