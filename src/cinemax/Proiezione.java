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

    public static String inserisciTitolo(Scanner input){
        String titolo;

        do {
            System.out.println("Inserisci il titolo del film: ");
            titolo = input.nextLine().trim();
        } while (titolo.isEmpty());

        return titolo;
    }

    public static String inserisciGenere(Scanner input){
        String genere;

        do {
            System.out.println("Inserisci il genere del film: ");
            genere = input.nextLine().trim();
        } while (genere.isEmpty());

        return genere;
    }

    public static String inserisciRegista(Scanner input){
        String regista;

        do {
            System.out.println("Inserisci il regista del film: ");
            regista = input.nextLine().trim();
        } while (regista.isEmpty());

        return regista;
    }

    public static int inserisciAnno(Scanner input){
        int anno;

        do {
            System.out.println("Inserisci l'anno del film ");
            anno = input.nextInt();
        } while (anno<=1950 || anno>=2026);

        return anno;
    }

    public static int  inserisciDurata(Scanner input){
        int durata;

        do {
            System.out.println("Inserisci la durata del film ");
            durata = input.nextInt();
        } while (durata<0 ||durata >300);

        return durata;
    }

    public static int inserisciEtaMinima(Scanner input){
        int eta;

        do {
            System.out.println("Inserisci l'eta minima per visionare il film ");
            eta = input.nextInt();
        } while (eta<0 || eta>19);

        return eta;
    }


}
