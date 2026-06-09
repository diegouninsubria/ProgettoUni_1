package cinemax;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Rappresenta un utente di tipo Cliente all'interno del sistema Cinemax.
 * Il cliente è l'utente finale che può effettuare operazioni come la
 * registrazione, il login e l'interazione con le funzionalità dedicate
 * alla consultazione e prenotazione.
 *
 * Questa classe estende {@link Utente} e imposta automaticamente il ruolo dell'utente come "Cliente".
 */

public class Cliente extends Utente{

    /**
     * Crea un nuovo Cliente con le informazioni anagrafiche e di accesso fornite.
     *
     * @param nome         nome dell'utente
     * @param cognome      cognome dell'utente
     * @param username     nome utente per l'accesso al sistema
     * @param password     password associata all'account
     * @param nascita      data di nascita dell'utente
     * @param domicilio    indirizzo di domicilio dell'utente
     */

    public Cliente(String nome, String cognome, String username, String password, LocalDate nascita, String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Cliente");
    }

    /**
     * Inserisce una nuova prenotazione per la proiezione indicata,
     * richiedendo il numero di posti e verificando la disponibilità.
     * In caso positivo, la prenotazione viene salvata nel file dedicato.
     *
     * @param p proiezione scelta dal cliente
     */

    public void InserisciPrenotazione(Proiezione p ){
        Scanner input = new Scanner(System.in);

        int numPosti = Inserimenti.InserisciPosti(input);
        if(numPosti + PostiGiaPrenoati(p) >200)
            System.out.println("Numero di posti disponibili Superato!");
        else{
            Prenotazione pren= new Prenotazione(this,p,numPosti,false);
            try{
                FileWriter writer= new FileWriter("data/Prenotazioni.txt",true);//apre il file //effettuare controllo get di nascita
                writer.write("\n"+pren.getId()+","+pren.getCliente().GetNome()+","+pren.getCliente().GetCognome()+","+pren.getCliente().GetUsername()+","+Guest.EncodedPsw(pren.getCliente().GetPassword())+","+pren.getProiezione().GetData().getYear()+"-"+pren.getProiezione().GetData().getMonthValue()+"-"+pren.getProiezione().GetData().getDayOfMonth()+","+pren.getProiezione().GetOra().getHour()+":"+pren.getProiezione().GetOra().getMinute()+":"+pren.getProiezione().GetOra().getSecond()+","+pren.getProiezione().GetFilm().getTitolo()+","+numPosti); //scrive nel file
                writer.close();

                System.out.println("Scrittura avenuta con successo");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Restituisce tutte le prenotazioni effettuate dal cliente corrente,
     * confrontando username e password con quelle presenti nel file.
     *
     * @return lista delle prenotazioni personali del cliente
     */

    public ArrayList<Prenotazione> LeggiPrenotazioniPersonali(){
        ArrayList<Prenotazione> personali= new ArrayList<>();
        ArrayList<Prenotazione> p = Bigliettaio.LeggiPrenotazioni();
        for(Prenotazione pren: p){
            if(this.GetUsername().equals(pren.getCliente().GetUsername()) && this.GetPassword().equals(pren.getCliente().GetPassword()))
                personali.add(pren);
        }
        return personali;
    }

    /**
     * Permette al cliente di modificare una propria prenotazione,
     * scegliendo se cambiare proiezione, numero di posti o data,
     * quando consentito.
     */

    public void modificaPrenotazione() {

    ArrayList<Prenotazione> tutte = Bigliettaio.LeggiPrenotazioni();
    ArrayList<Prenotazione> personali = LeggiPrenotazioniPersonali();
    Menu m = new Menu();
    Scanner input = new Scanner(System.in);

    if(personali.isEmpty()){
        System.out.println("Non hai prenotazioni da modificare!");
        return;
    }

    System.out.println("Seleziona la prenotazione da modificare:\n");
    for(int i=0; i<personali.size(); i++){
        System.out.println((i+1) + ") " + personali.get(i).VisualizzaPrenotazione());
    }
    System.out.println("SCELTA: ");
    int scelta = input.nextInt();
    input.nextLine();

    if(scelta < 1 || scelta > personali.size()){
        System.out.println("Scelta non valida.");
        return;
    }

    Prenotazione pren = personali.get(scelta - 1);
    tutte.remove(RicercaPrenotazione(pren,tutte));
    Prenotazione nuova;

    switch(m.MenuModificaPrenotazione()){

        case 1: // modifica proiezione
            System.out.println("caricamento...");
            ArrayList<Proiezione> disponibili = ProiezioniDisponibili();
            if(disponibili.isEmpty()){
                System.out.println("Non ci sono proiezioni disponibili.");
                return;
            }
            nuova = new Prenotazione(pren.getId(),pren.getCliente(),SelezioneProiezione(disponibili),pren.getPostiPrenotati(),false);
            tutte.add(nuova);
            break;

        case 2: // modifica posti
            int nuoviPosti = Inserimenti.InserisciPosti(input);
            nuova = pren;
            nuova.setPostiPrenotati(nuoviPosti);
            tutte.add(nuova);
            break;
        case 3:
            LocalDate oggi = LocalDate.now();
            nuova=pren;
            if(pren.getProiezione().GetData().isBefore(oggi)){
                System.out.println("non è possibile modificare la prenotazione!");
                return;
            }
            NuovaData(pren,pren.getPostiPrenotati(),nuova);
            tutte.add(nuova);
            break;

        case 4:
            System.out.println("Modifica annullata.");
            return;
    }

    try{
        FileWriter writer = new FileWriter("data/Prenotazioni.txt");
        writer.write("ID,Nome,Cognome,Username,Password,data,ora,film,posti prenotati");

        for(Prenotazione p : tutte){
            writer.write(
                "\n"+p.getId() + "," +
                p.getCliente().GetNome() + "," +
                p.getCliente().GetCognome() + "," +
                p.getCliente().GetUsername() + "," +
                Guest.EncodedPsw(p.getCliente().GetPassword()) + "," +
                p.getProiezione().GetData().getYear() + "-" +
                p.getProiezione().GetData().getMonthValue() + "-" +
                p.getProiezione().GetData().getDayOfMonth() + "," +
                p.getProiezione().GetOra().getHour() + ":" +
                p.getProiezione().GetOra().getMinute() + ":" +
                p.getProiezione().GetOra().getSecond() + "," +
                p.getProiezione().GetFilm().getTitolo() + "," +
                p.getPostiPrenotati()
            );
        }

        writer.close();
        System.out.println("Prenotazione modificata con successo!");

    }catch(IOException e){
        e.printStackTrace();
    }
}

    /**
     * Permette al cliente di eliminare una propria prenotazione,
     * se la proiezione non è ancora avvenuta.
     */

    public void eliminaPrenotazione(){
        ArrayList<Prenotazione> p = Bigliettaio.LeggiPrenotazioni();
        ArrayList<Prenotazione> personali = LeggiPrenotazioniPersonali();
        Scanner input = new Scanner(System.in);

        if(personali.isEmpty()){
            System.out.println("Non ci sono prenotazioni!");
            return;
        }

        System.out.println("Seleziona la prenotazione da eliminare: \n");
        for(int i=0;i<personali.size();i++){
            System.out.println((i+1)+") "+personali.get(i).VisualizzaPrenotazione());
        }
        System.out.println("SCELTA: ");
        int scelta = input.nextInt();
        input.nextLine();

        if(scelta < 1 || scelta > personali.size()){
            System.out.println("Scelta non valida.");
            return;
        }
        LocalDate oggi = LocalDate.now();
        Prenotazione pren = personali.get(scelta-1);
        if(pren.getProiezione().GetData().isAfter(oggi))
        {
            System.out.println("non è possibile eliminare la prenotazione!");
        }
        else {
            p.remove(pren);

            try {
                FileWriter writer = new FileWriter("data/Prenotazioni.txt");
                writer.write("ID,Nome,Cognome,Username,Password,data,ora,film,posti prenotati");
                for (Prenotazione preno : p) {
                    writer.write("\n" + preno.getId() + "," + preno.getCliente().GetNome() + "," + preno.getCliente().GetCognome() + "," + preno.getCliente().GetUsername() + "," + Guest.EncodedPsw(preno.getCliente().GetPassword()) + "," + preno.getProiezione().GetData().getYear() + "-" + preno.getProiezione().GetData().getMonthValue() + "-" + preno.getProiezione().GetData().getDayOfMonth() + "," + preno.getProiezione().GetOra().getHour() + ":" + preno.getProiezione().GetOra().getMinute() + ":" + preno.getProiezione().GetOra().getSecond() + "," + preno.getProiezione().GetFilm() + "," + preno.getPostiPrenotati());
                }

                writer.close();
                System.out.println("Proiezione eliminata con successo.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Restituisce tutte le proiezioni disponibili per il cliente,
     * filtrando in base all'età minima richiesta e alla disponibilità
     * dei posti.
     *
     * @return lista delle proiezioni disponibili
     */

    public ArrayList<Proiezione> ProiezioniDisponibili(){
        ArrayList<Proiezione> p = Proiezionista.leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();

        for(Proiezione proiezione : p){
            if(this.GetAnni()>=proiezione.GetFilm().getEtaMinima() && PostiGiaPrenoati(proiezione)<200)
                pro.add(proiezione);
        }
        return pro;
    }

    /**
     * Calcola il numero totale di posti già prenotati per una determinata
     * proiezione.
     *
     * @param p proiezione da analizzare
     * @return numero di posti già prenotati
     */

    public static int PostiGiaPrenoati(Proiezione p){
        int posti = 0;
        ArrayList<Prenotazione> prenotazioni = Bigliettaio.LeggiPrenotazioni();
        for(Prenotazione pren: prenotazioni){
            if(p.equals(pren.getProiezione()))
                posti+=pren.getPostiPrenotati();
        }
        return posti;
    }

    /**
     * Permette di selezionare una nuova proiezione per una prenotazione esistente,
     * mostrando all'utente tutte le proiezioni dello stesso film che dispongono
     * di posti sufficienti. Se la nuova proiezione è valida, aggiorna data, ora
     * e costo della prenotazione indicata.
     *
     * @param p      prenotazione originale da modificare
     * @param posti  numero di posti richiesti
     * @param nuova  prenotazione da aggiornare con la nuova proiezione scelta
     */

    public static void NuovaData(Prenotazione p, int posti, Prenotazione nuova){
        Scanner input = new Scanner(System.in);
        int scelta;
        ArrayList<Proiezione> pro= leggiProiezioni();
        ArrayList<Proiezione> filtro = new ArrayList<>();
        for(Proiezione proiezione : pro)
            if(proiezione.GetFilm().getTitolo().equals(p.getProiezione().GetFilm().getTitolo()) && (posti + Cliente.PostiGiaPrenoati(proiezione)<=200))
                filtro.add(proiezione);
        if(filtro.isEmpty()){
            System.out.println("non ci sono proiezioni disponibili");
        }
        else {
            do {
                System.out.println("Seleziona la proiezione: \n");
                for (int i = 0; i < filtro.size(); i++) {
                    System.out.println((i + 1) + ") " + filtro.get(i).toString());
                }
                System.out.println("SCELTA: ");
                scelta = input.nextInt();
                input.nextLine();
            } while (scelta < 1 || scelta > filtro.size());
            if(filtro.get(scelta-1).GetData().isBefore(LocalDate.now()))
                System.out.println("Non è possibile cambiare data");
            else {
                nuova.getProiezione().SetData(filtro.get(scelta - 1).GetData());
                nuova.getProiezione().SetCosto(filtro.get(scelta - 1).GetCosto());
                nuova.getProiezione().SetOra(filtro.get(scelta - 1).GetOra());
            }
        }
    }

    /**
     * Ricerca una prenotazione specifica all'interno di una lista,
     * confrontando data, ora e titolo del film.
     *
     * @param p      prenotazione da cercare
     * @param tutte  lista completa delle prenotazioni
     * @return la prenotazione corrispondente, oppure {@code null} se non trovata
     */

    public static Prenotazione RicercaPrenotazione(Prenotazione p,ArrayList<Prenotazione> tutte ){
        for(Prenotazione prenotazione : tutte)
            if(p.getProiezione().GetData().equals(prenotazione.getProiezione().GetData()) && p.getProiezione().GetOra().equals(prenotazione.getProiezione().GetOra()) && p.getProiezione().GetFilm().getTitolo().equals(prenotazione.getProiezione().GetFilm().getTitolo()))
                return prenotazione;
        return null;
    }
}
