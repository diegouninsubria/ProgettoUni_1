package cinemax;
import java.time.LocalTime;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Proiezione {

    Date data;
    LocalTime ora;
    Film film;
    float costo;

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

    public void InserisciFilm(){
        Scanner input= new Scanner(System.in);
        String titolo;
        String genere;
        String regista;
        int anno;
        int durata;
        int etaMinima;
        titolo=inserisciTitolo(input); //inserire i controlli di inserimento
        genere=inserisciGenere(input);
        regista=inserisciRegista(input);
        anno=inserisciAnno(input);
        durata=inserisciDurata(input);
        etaMinima=inserisciEtaMinima(input);

        try{
            FileWriter writer= new FileWriter("File/Film.txt",true);//apre il file //effettuare controllo get di nascita
            writer.write(""+titolo+","+genere+","+regista+","+anno+","+durata+","+etaMinima); //scrive nel file
            writer.close();

            System.out.println("Scrittura avenuta con successo");

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
