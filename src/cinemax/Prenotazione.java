package cinemax;

public class Prenotazione {
    private Cliente utente;
    private Proiezione proiezione;
    private int postiPrenotati;
    private boolean scaduta;

    public Prenotazione(Cliente utente, Proiezione proiezione, int postiPrenotati, boolean scaduta){
        this.utente=utente;
        this.proiezione=proiezione;
        this.postiPrenotati=postiPrenotati;
        this.scaduta=scaduta;
    }

    public String getProiezione(){
        return proiezione.toString();
    }

    public String getCliente(){
        return getCliente().toString();
    }

    public int getPostiPrenotati(){
        return postiPrenotati;
    }

    public void setPostiPrenotati(int numPosti){
        this.postiPrenotati=numPosti;
    }

    public boolean getScaduta(){
        return scaduta;
    }

    public float getCostoTotale(){
        return this.getPostiPrenotati()*proiezione.GetCosto();
    }

}