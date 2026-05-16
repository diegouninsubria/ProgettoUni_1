package cinemax;

import java.time.LocalTime;
import java.util.Date;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Proiezionista extends Utente{
    public Proiezionista(String nome, String cognome, String username, String password, Date nascita,String domicilio){
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

    public void InserisciProiezione(Film film){
        Scanner input= new Scanner(System.in);
        Date data;
        LocalTime ora;
        float costo;
        data=Guest.inserisciData(input);
        ora=inserisciOra(input);
        costo=inserisciCosto(input);

        try{
            FileWriter writer= new FileWriter("File/proiezioni.csv",true);
            writer.write(""+data.getYear()+"-"+data.getMonth()+"-"+data.getDay()+" "+ora.getHour()+":"+ora.getMinute()+":"+ora.getSecond()+","+film.getTitolo()+","+film.getGenere()+","+film.getRegista()+","+film.getAnno()+","+film.getDurata()+","+film.getEtaMinima());
            writer.close();

            System.out.println("Scrittura venuta con successo");
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
    public static LocalTime inserisciOra(Scanner input){
        int ora,minuti;

        do {
            System.out.println("Inserisci l'ora in cui inizierà la proiezione ");
            ora = input.nextInt();
        } while (ora<0 || ora>=25);

        do {
            System.out.println("Inserisci il minuto in cui inizierà la proiezione ");
            minuti = input.nextInt();
        } while (minuti<0 || minuti>=60);

        return LocalTime.of(ora,minuti,00);
    }

    public static float inserisciCosto(Scanner input){
        float costo;

        do {
            System.out.println("Inserisci il costo per la proiezione ");
            costo = input.nextFloat();
        } while (costo<=0.0);

        return costo;
    }

    public static ArrayList<Film> leggiFilm(){
        ArrayList<Film> f = new ArrayList<>();

        String file= "File/Film.txt";

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

    System.out.println("Inserisci nuovo costo:");
    float nuovoCosto = input.nextFloat();
    input.nextLine();

    p.SetCosto(nuovoCosto);

    try {
        FileWriter writer = new FileWriter("File/proiezioni.csv");
        writer.write("data,genere,regista,anno,durata,eta,costo\n");

        for(Proiezione pr : lista){
            writer.write(
                pr.GetData().getYear() + "-" +
                pr.GetData().getMonth() + "-" +
                pr.GetData().getDate() + " " +
                pr.GetOra().toString() + "," +
                pr.film.getTitolo() + "," +
                pr.film.getGenere() + "," +
                pr.film.getRegista() + "," +
                pr.film.getAnno() + "," +
                pr.film.getDurata() + "," +
                pr.film.getEtaMinima() + "," +
                pr.GetCosto() + "\n"
            );
        }

        writer.close();
        System.out.println("Proiezione modificata con successo.");

    } catch(Exception e){
        e.printStackTrace();
    }
}

    public void eliminaProiezione(){

    }
}
