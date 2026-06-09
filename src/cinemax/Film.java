/*
Fabio Proserpio 765841 CO
Tommaso Demontis 764582 CO
Diego Piantalunga 765613 CO
*/
package cinemax;

/**
 *Rappresenta un film presente nel sistema Cinemax.
 * Contiene le informazioni principali relative all'opera cinematografica,
 * come titolo, genere, regista, anno di uscita, durata ed età minima consigliata.
 *
 * La classe fornisce i metodi getter e setter per accedere e modificare
 * le informazioni del film.
 */

public class Film {
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMinima;

    /**
     * Crea un nuovo oggetto Film con i dati specificati.
     *
     * @param titolo       titolo del film
     * @param genere       genere cinematografico
     * @param regista      nome del regista
     * @param anno         anno di uscita del film
     * @param durata       durata del film in minuti
     * @param etaMinima    età minima consigliata per la visione
     */

    public Film(String titolo,String genere,String regista,int anno,int durata,int etaMinima){
        this.titolo=titolo;
        this.genere=genere;
        this.regista=regista;
        this.anno=anno;
        this.durata=durata;
        this.etaMinima=etaMinima;
    }

    //metodi set

    /**
     * Imposta il titolo del film
     *
     * @param titolo    nuovo titolo
     */

    public void SetTitolo(String titolo){
        this.titolo=titolo;
    }

    /**
     * Imposta il genere del film
     *
     * @param genere    nuovo genere
     */

    public void SetGenere(String genere){
        this.genere=genere;
    }

    /**
     * Imposta il regista del film
     *
     * @param regista    nuovo regista
     */

    public void SetRegista(String regista){
        this.regista=regista;
    }

    /**
     * Imposta l'anno di uscita del film
     *
     * @param anno    nuovo anno di uscita
     */

    public void SetAnno(int anno){
        this.anno=anno;
    }

    /**
     * Imposta la durata del film
     *
     * @param durata    nuova durata in minuti
     */

    public void SetDurata(int durata){
        this.durata=durata;
    }

    /**
     * Imposta l'età minima consigliata
     *
     * @param etaMinima    nuova età minima
     */

    public void SetEtaMinima(int etaMinima){
        this.etaMinima=etaMinima;
    }

    //metodi get

    /**
     * Restituisce il titolo del film
     *
     * @return titolo
     */

    public String getTitolo(){
        return this.titolo;
    }

    /**
     * Restituisce il genere del film
     *
     * @return genere
     */

    public String getGenere(){
        return this.genere;
    }

    /**
     * Restituisce il nome del regista del film
     *
     * @return regista
     */

    public String getRegista(){
        return this.regista;
    }

    /**
     * Restituisce l'anno di uscita del film
     *
     * @return anno di uscita
     */

    public int getAnno(){
        return this.anno;
    }

    /**
     * Restituisce la durata del film in minuti
     *
     * @return durata
     */

    public int getDurata(){
        return this.durata;
    }

    /**
     * Restituisce l'età minima consigliata per la visione
     *
     * @return età minima
     */

    public int getEtaMinima(){
        return this.etaMinima;
    }

    /**
     * Restituisce una rappresentazione testuale del fim,
     * contenente tutte le sue informazioni principali.
     *
     * @return stringa descrittiva del film
     */

    public String toString(){
        return "Titolo:"+this.titolo+"\n" +
                "Genere:"+this.genere+"\n" +
                "Regista:"+this.regista+"\n" +
                "Anno:"+this.anno+"\n" +
                "Durata:"+this.durata+" minuti \n" +
                "Età minima:"+this.etaMinima;
    }
}
