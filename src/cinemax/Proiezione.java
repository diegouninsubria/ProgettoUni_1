package cinemax;
import java.time.LocalTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Proiezione {

    private Date data;
    private LocalTime ora;
    private Film film;
    private float costo;

    public Proiezione(Date data,LocalTime ora,Film film,float costo){
        this.data=data;
        this.ora=ora;
        this.film=film;
        this.costo=costo;
    }

    public void SetData(Date data){this.data=data;}

    public void SetOra(LocalTime ora){this.ora=ora;}

    public void SetCosto(float costo){this.costo=costo;}

    public Date GetData(){return this.data;}

    public LocalTime GetOra(){return this.ora;}

    public float GetCosto(){return this.costo;}

    public String toString(){
        return "Data: "+data.toString()+"\n" +
                "Ora: "+ora.toString()+"\n" +
                "Film: "+film.toString()+"\n" +
                "Costo: "+costo+"\n";
    }
}
