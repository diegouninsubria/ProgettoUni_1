package cinemax;

public class Film {
    private String titolo;
    private String genere;
    private String regista;
    private int anno;
    private int durata;
    private int etaMinima;

    public Film(String titolo,String genere,String regista,int anno,int durata,int etaMinima){
        this.titolo=titolo;
        this.genere=genere;
        this.regista=regista;
        this.anno=anno;
        this.durata=durata;
        this.etaMinima=etaMinima;
    }

    //metodi set
    public void SetTitolo(String titolo){
        this.titolo=titolo;
    }

    public void SetGenere(String genere){
        this.genere=genere;
    }

    public void SetRegista(String regista){
        this.regista=regista;
    }

    public void SetAnno(int anno){
        this.anno=anno;
    }

    public void SetDurata(int durata){
        this.durata=durata;
    }

    public void SetEtaMinima(int etaMinima){
        this.etaMinima=etaMinima;
    }

    //metodi get

    public String getTitolo(){
        return this.titolo;
    }

    public String getGenere(){
        return this.genere;
    }

    public String getRegista(){
        return this.regista;
    }

    public int getAnno(){
        return this.anno;
    }

    public int getDurata(){
        return this.durata;
    }

    public int getEtaMinima(){
        return this.etaMinima;
    }

    public String toString(){
        return "Titolo:"+this.titolo+"\n" +
                "Genere:"+this.genere+"\n" +
                "Regista:"+this.regista+"\n" +
                "Anno:"+this.anno+"\n" +
                "Durata:"+this.durata+" minuti \n" +
                "Età minima:"+this.etaMinima;
    }
}
