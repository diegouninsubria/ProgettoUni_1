package cinemax;

public class Cinemax {
    public static void main(String args[]){
        Guest g=new Guest();
        //g.Registrazione();
        Utente ut=null;
        ut=g.Login();
        if(ut==null){
            System.out.println("utente non presente");
        }
        else
             System.out.println("ti sei loggato come:"+ut.toString());

    }
}
