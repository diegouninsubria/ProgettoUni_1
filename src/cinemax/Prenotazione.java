/*
Fabio Proserpio 765841 CO
Tommaso Demontis 764582 CO
Diego Piantalunga 765613 CO
*/
package cinemax;

import java.util.ArrayList;

/**
 * Rappresenta una prenotazione effettuata da un cliente per una specifica
 * proiezione cinematografica.
 * Contiene le informazioni relative all'utente, alla proiezione scelta,
 * al numero di posti prenotati e allo stato della prenotazione (attiva o scaduta).
 */

public class Prenotazione {
    private int id;
    private Cliente utente;
    private Proiezione proiezione;
    private int postiPrenotati;
    private boolean scaduta;

    /**
     * Crea una nuova prenotazione con i dati specificati.
     *
     * @param utente            cliente che ha effettuato la prenotazione
     * @param proiezione        proiezione scelta dal cliente
     * @param postiPrenotati    numero di posti prenotati
     * @param scaduta           indica se la prenotazione è scaduta
     */

    public Prenotazione(Cliente utente, Proiezione proiezione, int postiPrenotati, boolean scaduta){
        this.utente=utente;
        this.proiezione=proiezione;
        this.postiPrenotati=postiPrenotati;
        this.scaduta=scaduta;
        this.id=InserisciId();
    }

    /**
     * Crea una prenotazione utilizzando un ID già esistente, tipicamente
     * ricostruita da file o da una sorgente esterna.
     *
     * @param id              identificativo univoco della prenotazione
     * @param utente          cliente che ha effettuato la prenotazione
     * @param proiezione      proiezione scelta dal cliente
     * @param postiPrenotati  numero di posti prenotati
     * @param scaduta         indica se la prenotazione è scaduta
     */

    public Prenotazione(int id,Cliente utente, Proiezione proiezione, int postiPrenotati, boolean scaduta){
        this.id=id;
        this.utente=utente;
        this.proiezione=proiezione;
        this.postiPrenotati=postiPrenotati;
        this.scaduta=scaduta;
    }

    /**
     * Restituisce la proiezione associata alla prenotazione.
     *
     * @return oggetto {@link Proiezione} relativo alla prenotazione
     */

    public Proiezione getProiezione(){
        return this.proiezione;
    }

    /**
     * Restituisce il numero di posti prenotati.
     *
     * @return numero di posti prenotati
     */

    public int getPostiPrenotati(){
        return postiPrenotati;
    }

    /**
     * Modifica il numero di posti prenotati.
     *
     * @param numPosti nuovo numero di posti
     */

    public void setPostiPrenotati(int numPosti){
        this.postiPrenotati=numPosti;
    }

    /**
     * Indica se la prenotazione è scaduta.
     *
     * @return {@code true} se la prenotazione è scaduta, altrimenti {@code false}
     */

    public boolean getScaduta(){
        return scaduta;
    }

    /**
     * Calcola il costo totale della prenotazione in base al numero di posti
     * prenotati e il costo del biglietto della proiezione.
     *
     * @return costo totale della prenotazione
     */

    public float getCostoTotale(){
        return this.getPostiPrenotati()*proiezione.GetCosto();
    }

    /**
     * Restituisce l'identificativo univoco della prenotazione.
     *
     * @return ID della prenotazione
     */

    public int getId(){return this.id;}

    /**
     * Restituisce il cliente che ha effettuato la prenotazione.
     *
     * @return oggetto {@link Cliente} associato alla prenotazione
     */

    public Cliente getCliente(){return this.utente;}

    /**
     * Genera automaticamente un nuovo ID per la prenotazione,
     * basandosi sull'ultimo ID presente nel file delle prenotazioni.
     *
     * @return nuovo ID incrementale
     */

    public static int InserisciId(){
        int id=0;
        ArrayList<Prenotazione> p= Bigliettaio.LeggiPrenotazioni();
        if(p.isEmpty())
            return 1;
        for(Prenotazione pren : p)
        {
            id=pren.getId();
        }
        return id+1;
    }

    /**
     * Restituisce una rappresentazione testuale completa della prenotazione,
     * includendo informazioni sul cliente, sulla proiezione, sui posti prenotati
     * e sul costo totale.
     *
     * @return stringa descrittiva della prenotazione
     */

    public String VisualizzaPrenotazione(){
        return "ID prenotazione: "+this.getId()+"\n" +
                "Nome e Cognome Cliente: "+this.getCliente().GetNome()+" "+this.getCliente().GetCognome()+"\n" +
                "Giorno Proiezione: "+this.getProiezione().GetData().toString()+"\n" +
                "Ora Proiezione: "+this.getProiezione().GetOra().toString()+"\n" +
                "Numero Biglietti: "+this.getPostiPrenotati()+"\n" +
                "Costo Unitario: "+this.getProiezione().GetCosto()+"\n" +
                "Importo finale: "+this.getCostoTotale()+"\n";
    }

}
