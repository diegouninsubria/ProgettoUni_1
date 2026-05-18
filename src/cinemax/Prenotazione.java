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
     * Restituisce una rappresentazione testuale della proiezione associata.
     *
     * @return stringa descrittiva della proiezione
     */

    public String getProiezione(){
        return proiezione.toString();
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

    public int getId(){return this.id;}
    public Cliente getCliente(){return this.utente;}

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

}