package cinemax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Rappresenta un utente di tipo Bigliettaio all'interno del sistema Cinemax.
 * Il Bigliettaio è responsabile della gestione dei biglietti, come emissione,
 * controllo e operazioni correlate alla vendita.
 *
 * Questa classe estende {@link Utente} e imposta il ruolo dell'utente coe "Bigliettaio".
 */

public class Bigliettaio extends Utente{

    /**
     * Crea un nuovo "Bigliettaio" con le informazioni anagrafiche e di accesso fornite.
     *
     * @param nome         nome dell'utente
     * @param cognome      cognome dell'utente
     * @param username     nome utente per l'accesso al sistema
     * @param password     password associata all'account
     * @param nascita      data di nascita dell'utente
     * @param domicilio    indirizzo di domicilio dell'utente
     */

    public Bigliettaio(String nome, String cognome, String username, String password, Date nascita,String domicilio){
        super(nome,cognome,username,password,nascita,domicilio,"Bigliettaio");
    }

    public ArrayList<Prenotazione> RicercaPrenotazione(int id){
        ArrayList<Prenotazione> p = LeggiPrenotazioni();
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        for(Prenotazione pren : p){
            if(pren.getId() == id)
                prenotazioni.add(pren);

        }
        return prenotazioni;
    }

    public ArrayList<Prenotazione> RicercaPrenotazione(String nome, String cognome){
        ArrayList<Prenotazione> p = LeggiPrenotazioni();
        ArrayList<Prenotazione> prenotazioni= new ArrayList<>();
        for(Prenotazione pren : p)
            if(pren.getCliente().GetNome().equals(nome) && pren.getCliente().GetCognome().equals(cognome))
                prenotazioni.add(pren);
        return prenotazioni;
    }

    public ArrayList<Prenotazione> RicercaPrenotazione(Date data){
        ArrayList<Prenotazione> p = LeggiPrenotazioni();
        ArrayList<Prenotazione> prenotazioni= new ArrayList<>();
        for(Prenotazione pren : p)
            if(data.compareTo(pren.getProiezione().GetData())>=0)
                prenotazioni.add(pren);
        return prenotazioni;
    }

    public ArrayList<Prenotazione> RicercaPrenotazione(Date data1,Date data2){
        ArrayList<Prenotazione> p = LeggiPrenotazioni();
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        Date d;
        for(Prenotazione pren : p) {
            d = pren.getProiezione().GetData();
            if (d.compareTo(data1) >= 0 && d.compareTo(data2) <= 0)
                prenotazioni.add(pren);
        }
        return prenotazioni;
    }

    public ArrayList<Prenotazione> RicercaPrenotazioni(String titolo){
        ArrayList<Prenotazione> p = LeggiPrenotazioni();
        ArrayList<Prenotazione> prenotazioni = new ArrayList<>();
        for(Prenotazione pren : p)
            if(Utente.CheckString(titolo,pren.getProiezione().GetFilm().getTitolo()))
                prenotazioni.add(pren);
        return prenotazioni;
    }
    public Prenotazione ScegliPrenotazione(ArrayList<Prenotazione> p){
        int scelta;
        Scanner input = new Scanner(System.in);
        int i=1;
        for(Prenotazione pren : p){
            System.out.println(i+") "+ pren.getProiezione().GetFilm().getTitolo()+" , +"+pren.getCliente().GetUsername()+"\n-------------------------------\n");//decidere
            i++;
        }
        do{
            System.out.print("Scelta: ");

            scelta = input.nextInt();
            input.nextLine();
        }while(scelta<=0 || scelta>p.size());
        return p.get(scelta-1);
    }

    public static ArrayList<Prenotazione> LeggiPrenotazioni(){
        ArrayList<Prenotazione> p = new ArrayList<>();

        String file = "File/Prenotazioni.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine();
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                Cliente c = SelezionaCliente(campi[1],campi[2],campi[3],Guest.DecodePsw(campi[4]));

                String[] datiD=campi[5].split("-");
                String[] datiO=campi[6].split(":");

                int giorno = Integer.parseInt(datiD[2]);
                int mese = Integer.parseInt(datiD[1]);
                int anno = Integer.parseInt(datiD[0]);
                Date data = new Date(anno,mese,giorno);

                int ora = Integer.parseInt(datiO[0]);
                int minuti=Integer.parseInt(datiO[1]);
                int secondi=Integer.parseInt(datiO[2]);

                LocalTime time = LocalTime.of(ora,minuti,secondi);
                Proiezione pro = SelezionaProiezione(data,time,campi[7]);
                p.add(new Prenotazione(Integer.parseInt(campi[0]),c,pro,Integer.parseInt(campi[8]),CheckScaduta(data,time)));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return  p;
    }

    public static Cliente SelezionaCliente(String nome,String cognome,String username,String password){
        ArrayList<Utente> utenti = Guest.LeggiFile();
        for(Utente u: utenti){
            if(u instanceof Cliente)
                if(u.GetNome().equals(nome) && u.GetCognome().equals(cognome) && u.GetUsername().equals(username) && u.GetPassword().equals(password))
                    return (Cliente) u;
        }
        return null;
    }

    public static Proiezione SelezionaProiezione(Date data, LocalTime ora,String titolo){
        ArrayList<Proiezione> proiezioni = Utente.leggiProiezioni();

        for( Proiezione p : proiezioni){
            if(p.GetData().equals(data) && p.GetOra().equals(ora) && p.GetFilm().getTitolo().equals(titolo))
                return p;
        }
        return null;
    }

    public static boolean CheckScaduta(Date data, LocalTime ora){
        Date dataOdierna = new Date();
        LocalTime oraOdierna = LocalTime.now();
        if(data.compareTo(dataOdierna) <0){
            return true;
        }
        else if(data.compareTo(dataOdierna) ==0)
            if(ora.isBefore(oraOdierna))
                return true;
            else return false;
        else return false;
    }

    public ArrayList<Prenotazione> RicercaPrenotazioneCombinata() {

    Scanner input = new Scanner(System.in);
    ArrayList<Prenotazione> tutte = LeggiPrenotazioni();
    ArrayList<Prenotazione> risultato = new ArrayList<>();

    System.out.println("=== RICERCA COMBINATA ===");

    System.out.println("Vuoi filtrare per nome e cognome? (s/n)");
    boolean filtroNome = input.nextLine().trim().equalsIgnoreCase("s");
    String nome = "", cognome = "";
    if(filtroNome){
        System.out.print("Nome: ");
        nome = input.nextLine().trim();
        System.out.print("Cognome: ");
        cognome = input.nextLine().trim();
    }

    System.out.println("Vuoi filtrare per titolo del film? (s/n)");
    boolean filtroTitolo = input.nextLine().trim().equalsIgnoreCase("s");
    String titolo = "";
    if(filtroTitolo){
        System.out.print("Titolo: ");
        titolo = input.nextLine().trim();
    }

    System.out.println("Vuoi filtrare per data? (s/n)");
    boolean filtroData = input.nextLine().trim().equalsIgnoreCase("s");
    Date data = null;
    if(filtroData){
        data = Guest.inserisciData(input);
    }

    System.out.println("Vuoi filtrare per intervallo di date? (s/n)");
    boolean filtroIntervallo = input.nextLine().trim().equalsIgnoreCase("s");
    Date d1 = null, d2 = null;
    if(filtroIntervallo){
        System.out.println("Inserisci data iniziale:");
        d1 = Guest.inserisciData(input);
        System.out.println("Inserisci data finale:");
        d2 = Guest.inserisciData(input);
    }

    for(Prenotazione p : tutte){

        boolean ok = true;

        if(filtroNome)
            if(!(p.getCliente().GetNome().equals(nome) && p.getCliente().GetCognome().equals(cognome)))
                ok = false;

        if(filtroTitolo)
            if(!Utente.CheckString(titolo, p.getProiezione().GetFilm().getTitolo()))
                ok = false;

        if(filtroData)
            if(!(p.getProiezione().GetData().equals(data)))
                ok = false;

        if(filtroIntervallo){
            Date d = p.getProiezione().GetData();
            if(!(d.compareTo(d1) >= 0 && d.compareTo(d2) <= 0))
                ok = false;
        }

        if(ok)
            risultato.add(p);
    }

    return risultato;
}

}
