package cinemax;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Proiezione {

    /**
     * Rappresenta una proiezione cinematografica nel sistema Cinemax.
     * Contiene le informazioni relative alla data, all'orario, al film
     * proiettato e al costo del biglietto.
     * La classe fornisce i metodi getter e setter per accedere e odificare
     * i dati della proiezione.
     */

    private LocalDate data;
    private LocalTime ora;
    private Film film;
    private float costo;

    /**
     * Crea una nuova proiezione con i dati specificati.
     *
     * @param data     data della proiezione
     * @param ora      orario della proiezione
     * @param film     film proiettato
     * @param costo    costo del biglietto
     */

    public Proiezione(LocalDate data,LocalTime ora,Film film,float costo){
        this.data=data;
        this.ora=ora;
        this.film=film;
        this.costo=costo;
    }

    /**
     * Imposta la data della proiezione.
     *
     * @param data    nuova data
     */

    public void SetData(LocalDate data){this.data=data;}

    /**
     * Imposta l'orario della proiezione.
     *
     * @param ora    nuova orario
     */

    public void SetOra(LocalTime ora){this.ora=ora;}

    /**
     * Imposta il costo del biglietto.
     *
     * @param costo    nuovo costo
     */

    public void SetCosto(float costo){this.costo=costo;}

    /**
     * Restituisce la data della proiezione.
     *
     * @return data della proiezione
     */

    public LocalDate GetData(){return this.data;}

    /**
     * Restituisce l'orario della proiezione.
     *
     * @return orario della proiezione
     */

    public LocalTime GetOra(){return this.ora;}

    /**
     * Restituisce il costo del biglietto.
     *
     * @return costo del biglietto
     */

    public float GetCosto(){return this.costo;}

    public Film GetFilm(){return this.film;}

    /**
     * Restituisce una rappresentazione testuale completa della proiezione,
     * includendo data, ora, film e costo.
     *
     * @return stringa descrittiva della proiezione
     */

    public String toString(){
        return "Data: "+data.toString()+"\n" +
                "Ora: "+ora.toString()+"\n" +
                "Film: "+film.toString()+"\n" +
                "Costo: "+costo+"\n";
    }
}