package cinemax;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Rappresenta un utente di tipo Cliente all'interno del sistema Cinemax.
 * Il cliente è l'utente finale che può effettuare operazioni come la
 * registrazione, il login e l'interazione con le funzionalità dedicate
 * alla consultazione e prenotazione.
 *
 * Queste classe estente {@link Utente} e imposta automaticamente il ruolo dell'utente come "Cliente".
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

    public void InserisciPrenotazione(Proiezione p ){
        Scanner input = new Scanner(System.in);

        int numPosti = Inserimenti.InserisciPosti(input);
        if(numPosti + PostiGiaPrenoati(p) >200)
            System.out.println("Numero di posti disponibili Superato!");
        else{
            Prenotazione pren= new Prenotazione(this,p,numPosti,false);
            try{
                FileWriter writer= new FileWriter("File/Prenotazioni.txt",true);//apre il file //effettuare controllo get di nascita
                writer.write(""+pren.getId()+","+pren.getCliente().GetNome()+","+pren.getCliente().GetCognome()+","+pren.getCliente().GetUsername()+","+Guest.EncodedPsw(pren.getCliente().GetPassword())+","+pren.getProiezione().GetData().getYear()+"-"+pren.getProiezione().GetData().getMonthValue()+"-"+pren.getProiezione().GetData().getDayOfMonth()+","+pren.getProiezione().GetOra().getHour()+":"+pren.getProiezione().GetOra().getMinute()+":"+pren.getProiezione().GetOra().getSecond()+","+pren.getProiezione().GetFilm().getTitolo()+","+numPosti+"\n"); //scrive nel file
                writer.close();

                System.out.println("Scrittura avenuta con successo");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public ArrayList<Prenotazione> LeggiPrenotazioniPersonali(){
        ArrayList<Prenotazione> personali= new ArrayList<>();
        ArrayList<Prenotazione> p = Bigliettaio.LeggiPrenotazioni();
        for(Prenotazione pren: p){
            if(this.GetUsername().equals(pren.getCliente().GetUsername()) && this.GetPassword().equals(pren.getCliente().GetPassword()))
                personali.add(pren);
        }
        return personali;
    }
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

    int scelta = input.nextInt();
    input.nextLine();

    if(scelta < 1 || scelta > personali.size()){
        System.out.println("Scelta non valida.");
        return;
    }

    Prenotazione pren = personali.get(scelta - 1);

    switch(m.MenuModificaPrenotazione()){

        case 1: // modifica proiezione
            ArrayList<Proiezione> disponibili = ProiezioniDisponibili();
            if(disponibili.isEmpty()){
                System.out.println("Non ci sono proiezioni disponibili.");
                return;
            }

            Prenotazione nuova = new Prenotazione(pren.getCliente(),SelezioneProiezione(disponibili),pren.getPostiPrenotati(),false);
            tutte.remove(scelta-1);
            tutte.add(nuova);
            break;

        case 2: // modifica posti
            int nuoviPosti = Inserimenti.InserisciPosti(input);
            pren.setPostiPrenotati(nuoviPosti);
            tutte.remove(scelta-1);
            tutte.add(pren);
            break;

        case 3:
            System.out.println("Modifica annullata.");
            return;
    }

    try{
        FileWriter writer = new FileWriter("File/Prenotazioni.txt");
        writer.write("ID,Nome,Cognome,Username,Password,data,ora,film,posti prenotati\n");

        for(Prenotazione p : tutte){
            writer.write(
                p.getId() + "," +
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
                p.getPostiPrenotati() + "\n"
            );
        }

        writer.close();
        System.out.println("Prenotazione modificata con successo!");

    }catch(IOException e){
        e.printStackTrace();
    }
}


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
                FileWriter writer = new FileWriter("File/Prenotazioni.txt");
                writer.write("ID,Nome,Cognome,Username,Password,data,ora,film,posti prenotati\n");
                for (Prenotazione preno : p) {
                    writer.write("" + preno.getId() + "," + preno.getCliente().GetNome() + "," + preno.getCliente().GetCognome() + "," + preno.getCliente().GetUsername() + "," + Guest.EncodedPsw(preno.getCliente().GetPassword()) + "," + preno.getProiezione().GetData().getYear() + "-" + preno.getProiezione().GetData().getMonthValue() + "-" + preno.getProiezione().GetData().getDayOfMonth() + "," + preno.getProiezione().GetOra().getHour() + ":" + preno.getProiezione().GetOra().getMinute() + ":" + preno.getProiezione().GetOra().getSecond() + "," + preno.getProiezione().GetFilm() + "," + preno.getPostiPrenotati() + "\n");
                }

                writer.close();
                System.out.println("Proiezione eliminata con successo.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Proiezione> ProiezioniDisponibili(){
        ArrayList<Proiezione> p = Proiezionista.leggiProiezioni();
        ArrayList<Proiezione> pro = new ArrayList<>();

        for(Proiezione proiezione : p){
            if(this.GetAnni()>=proiezione.GetFilm().getEtaMinima() && PostiGiaPrenoati(proiezione)<200)
                pro.add(proiezione);
        }
        return pro;
    }

    public static int PostiGiaPrenoati(Proiezione p){
        int posti = 0;
        ArrayList<Prenotazione> prenotazioni = Bigliettaio.LeggiPrenotazioni();
        for(Prenotazione pren: prenotazioni){
            if(p.equals(pren.getProiezione()))
                posti+=pren.getPostiPrenotati();
        }
        return posti;
    }
}
