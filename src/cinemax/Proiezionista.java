package cinemax;

import javafx.scene.Scene;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Proiezionista extends Utente{
    public Proiezionista(String nome, String cognome, String username, String password, LocalDate nascita, String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Proiezionista");
    }
    public void InserisciFilm(){
        Scanner input= new Scanner(System.in);
        String titolo;
        String genere;
        String regista;
        int anno;
        int durata;
        int etaMinima;
        titolo=Inserimenti.inserisciTitolo(input); //inserire i controlli di inserimento
        if(!ControllaFilm(titolo)){
            do{
                System.out.println("Film già presente nell'archivio");
                titolo=Inserimenti.inserisciTitolo(input);
            }while(!ControllaFilm(titolo));
        }
        genere=Inserimenti.inserisciGenere(input);
        regista=Inserimenti.inserisciRegista(input);
        anno=Inserimenti.inserisciAnno(input);
        durata=Inserimenti.inserisciDurata(input);
        etaMinima=Inserimenti.inserisciEtaMinima(input);

        try{
            FileWriter writer= new FileWriter("data/Film.txt",true);//apre il file //effettuare controllo get di nascita
            writer.write("\n"+titolo+","+genere+","+regista+","+anno+","+durata+","+etaMinima); //scrive nel file
            writer.close();

            System.out.println("Scrittura avenuta con successo");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void InserisciProiezione(Film film){
        Scanner input= new Scanner(System.in);
        LocalDate data;
        LocalTime ora;
        data=Inserimenti.inserisciData(input);
        ora=Inserimenti.inserisciOra(input);
        float costo=Inserimenti.inserisciCosto(input);

        try{
            FileWriter writer= new FileWriter("data/proiezioni.csv",true);
            writer.write("\""+data.getYear()+"-"+data.getMonthValue()+"-"+data.getDayOfMonth()+" "
                    +ora.getHour()+":"+ora.getMinute()+":"+ora.getSecond()+"\","
                    +"\""+film.getTitolo()+"\","
                    +film.getGenere()+","
                    +"\""+film.getRegista()+"\","
                    +film.getAnno()+","
                    +film.getDurata()+","
                    +film.getEtaMinima()+","
                    +costo);
            writer.close();

            System.out.println("Scrittura venuta con successo");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Film> leggiFilm(){
        ArrayList<Film> f = new ArrayList<>();

        String file= "data/Film.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            br.readLine();
            String riga;
            while((riga = br.readLine())!=null){
                String [] campi = riga.split(",");
                f.add(new Film(campi[0],campi[1],campi[2],Integer.parseInt(campi[3]),Integer.parseInt(campi[4]),Integer.parseInt(campi[5])));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        return f;
    }

    public void modificaProiezione() {
    ArrayList<Proiezione> lista = leggiProiezioni();
    Scanner input = new Scanner(System.in);

    if(lista.isEmpty()){
        System.out.println("Nessuna proiezione presente.");
        return;
    }

    System.out.println("Seleziona la proiezione da modificare:");
    for(int i=0; i<lista.size(); i++){
        System.out.println((i+1) + ") " + lista.get(i).toString());
    }

    int scelta = input.nextInt();
    input.nextLine();

    if(scelta < 1 || scelta > lista.size()){
        System.out.println("Scelta non valida.");
        return;
    }

    Proiezione p = lista.get(scelta - 1);
    if(Cliente.PostiGiaPrenoati(p)>0){
        System.out.println("impossibile modificare la proiezione, presenza di prenotazioni!\n");
    }
    else {
        System.out.println("Inserisci la nuova data:");
        LocalDate data = Inserimenti.inserisciData(input);
        p.SetData(data);
        lista.remove(scelta - 1);
        lista.add(p);
        try {
            FileWriter writer = new FileWriter("data/proiezioni.csv");
            writer.write("data,genere,regista,anno,durata,eta,costo\n");

            for (Proiezione pr : lista) {
                writer.write(
                        "\"" +
                                pr.GetData().getYear() + "-" +
                                pr.GetData().getMonthValue() + "-" +
                                pr.GetData().getDayOfMonth() + " " +
                                pr.GetOra().toString() +
                                "\"," +

                                "\"" + pr.GetFilm().getTitolo() + "\"," +
                                pr.GetFilm().getGenere() + "," +
                                "\"" + pr.GetFilm().getRegista() + "\"," +

                                pr.GetFilm().getAnno() + "," +
                                pr.GetFilm().getDurata() + "," +
                                pr.GetFilm().getEtaMinima() + "," +
                                pr.GetCosto() + "\n"
                );
            }

            writer.close();
            System.out.println("Proiezione modificata con successo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    public void eliminaProiezione() {
    ArrayList<Proiezione> lista = leggiProiezioni();
    Scanner input = new Scanner(System.in);

    if(lista.isEmpty()){
        System.out.println("Nessuna proiezione presente.");
        return;
    }

    System.out.println("Seleziona la proiezione da eliminare:");
    for(int i=0; i<lista.size(); i++){
        System.out.println((i+1) + ") " + lista.get(i).toString());
    }

    int scelta = input.nextInt();
    input.nextLine();

    if(scelta < 1 || scelta > lista.size()){
        System.out.println("Scelta non valida.");
        return;
    }
    if(Cliente.PostiGiaPrenoati(lista.get(scelta-1))>0){
        System.out.println("Impossibile eliminare la proiezione, prenotazioni già presenti!");
    }
    else {
        lista.remove(scelta - 1);

        try {
            FileWriter writer = new FileWriter("data/proiezioni.csv");
            writer.write("data,genere,regista,anno,durata,eta,costo\n");

            for (Proiezione pr : lista) {
                writer.write(
                        "\"" +
                                pr.GetData().getYear() + "-" +
                                pr.GetData().getMonthValue() + "-" +
                                pr.GetData().getDayOfMonth() + " " +
                                pr.GetOra().toString() +
                                "\"," +

                                "\"" + pr.GetFilm().getTitolo() + "\"," +
                                pr.GetFilm().getGenere() + "," +
                                "\"" + pr.GetFilm().getRegista() + "\"," +

                                pr.GetFilm().getAnno() + "," +
                                pr.GetFilm().getDurata() + "," +
                                pr.GetFilm().getEtaMinima() + "," +
                                pr.GetCosto() + "\n"
                );
            }

            writer.close();
            System.out.println("Proiezione eliminata con successo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
    public Film selezionaFilm(ArrayList<Film> film){
        Scanner input = new Scanner(System.in);
        int i=1;
        int scelta;
        do {
            i=1;
            for (Film f : film) {
                System.out.println(i + ") "+f.getTitolo()+"\n");
                i++;
            }
            System.out.println("========================\n");
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<1 || scelta> film.size());
        return film.get(scelta-1);
    }

    public static boolean ControllaFilm(String Titolo){
        ArrayList<Film> f = leggiFilm();
        for(Film film : f){
            if(Titolo.toLowerCase().trim().equals(film.getTitolo().toLowerCase().trim()))
                return false;
        }
        return true;
    }

}
