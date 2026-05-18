package cinemax;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<Prenotazione> LeggiPrenotazioni(){
        ArrayList<Prenotazione> p = new ArrayList<>();

        String file = "File/Prenotazioni.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {

            br.readLine();
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(",");
                Cliente c = SelezionaCliente(campi[1],campi[2],campi[3],campi[4]);

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
}
