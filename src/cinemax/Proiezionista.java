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

    /**
     * Rappresenta un utente con ruolo di Proiezionista nel sistema Cinemax.
     * Il Proiezionista è responsabile della gestione dei film e delle proiezioni,
     * potendo inserire nuovi film, programmare proiezioni, modificarle o eliminarle.
     *
     * La classe fornisce inoltre metodi per leggere i film dal file,
     * selezionare un film dall’elenco e verificare l’esistenza di un titolo
     * già presente nell’archivio.
     *
     * Estende {@link Utente} ereditando le informazioni anagrafiche e le
     * credenziali di accesso.
     */


    /**
     * Crea un nuovo Proiezionista con i dati specificati.
     *
     * @param nome       nome dell'utente
     * @param cognome    cognome dell'utente
     * @param username   username per l'accesso
     * @param password   password dell'account
     * @param nascita    data di nascita
     * @param domicilio  domicilio dell'utente
     */

    public Proiezionista(String nome, String cognome, String username, String password, LocalDate nascita, String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Proiezionista");
    }

    /**
     * Permette al Proiezionista di inserire un nuovo film nel sistema.
     * Richiede i dati tramite input da tastiera, verifica che il titolo
     * non sia già presente e salva il film nel file dedicato.
     */

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

    /**
     * Inserisce una nuova proiezione per il film specificato,
     * richiedendo data, orario e costo del biglietto.
     * La proiezione viene salvata nel file delle proiezioni.
     *
     * @param film film per cui creare la proiezione
     */

    public void InserisciProiezione(Film film){
        Scanner input= new Scanner(System.in);
        LocalDate data;
        LocalTime ora;
        data=Inserimenti.inserisciData(input);
        ora=Inserimenti.inserisciOra(input);
        float costo=Inserimenti.inserisciCosto(input);

        try{
            FileWriter writer= new FileWriter("data/proiezioni.csv",true);
            writer.write("\n\""+data.getYear()+"-"+data.getMonthValue()+"-"+data.getDayOfMonth()+" "
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

    /**
     * Legge il file dei film e ricostruisce la lista completa
     * degli oggetti {@link Film} presenti nell'archivio.
     *
     * @return lista dei film caricati dal file
     */

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

    /**
     * Permette al Proiezionista di modificare la data di una proiezione esistente.
     * La modifica è consentita solo se non sono presenti prenotazioni associate.
     * Al termine, il file delle proiezioni viene riscritto con i dati aggiornati.
     */

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
    System.out.println("SCELTA: ");
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
            writer.write("data,genere,regista,anno,durata,eta,costo");

            for (Proiezione pr : lista) {
                writer.write(
                        "\n\"" +
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
                                pr.GetCosto()
                );
            }

            writer.close();
            System.out.println("Proiezione modificata con successo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    /**
     * Permette al Proiezionista di eliminare una proiezione esistente.
     * L'eliminazione è consentita solo se non sono presenti prenotazioni
     * per quella proiezione. Il file delle proiezioni viene aggiornato di conseguenza.
     */

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
    System.out.println("SCELTA: ");

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
            writer.write("data,genere,regista,anno,durata,eta,costo");

            for (Proiezione pr : lista) {
                writer.write(
                        "\n\"" +
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
                                pr.GetCosto()
                );
            }

            writer.close();
            System.out.println("Proiezione eliminata con successo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    /**
     * Mostra all'utente l'elenco dei film disponibili e permette di selezionarne uno.
     *
     * @param film lista dei film tra cui scegliere
     * @return film selezionato dall'utente
     */

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

    /**
     * Verifica se un film con il titolo specificato è già presente nell'archivio.
     *
     * @param Titolo titolo del film da controllare
     * @return {@code true} se il film NON è presente, {@code false} se esiste già
     */

    public static boolean ControllaFilm(String Titolo){
        ArrayList<Film> f = leggiFilm();
        for(Film film : f){
            if(Titolo.toLowerCase().trim().equals(film.getTitolo().toLowerCase().trim()))
                return false;
        }
        return true;
    }

}
