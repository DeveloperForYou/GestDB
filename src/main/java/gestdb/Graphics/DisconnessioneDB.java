package gestdb.Graphics;

import gestdb.ServiziDB.MappaConnessioni;
import gestdb.ServiziDB.MySQL;
import gestdb.ServiziDB.PostgreDB;
import gestdb.ServiziDB.ServiziDBSupportati;

public class DisconnessioneDB {
   public static void Disconnessione(){
      if(MappaConnessioni.NoConnessione()==false) 
          MessaggiGrafici.MessaggioConnessioneMancante();
      else{
           int indiceServizio=MappaConnessioni.getIndexConn();
           String Servizio=ServiziDBSupportati.OttieniEnum(indiceServizio);
           if(Servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.ONE))){//servizio=MySQL
             if(MySQL.disconnetti())
                 MessaggiGrafici.ConnessioneChiusaSenzaErrori();
             else
                 MessaggiGrafici.ConnessioneChiusaConErrori();
           }
           if(Servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.TWO))){//servizio=Postgre
             if(PostgreDB.disconnetti())
                 MessaggiGrafici.ConnessioneChiusaSenzaErrori();
             else
                 MessaggiGrafici.ConnessioneChiusaConErrori();
           }
        MappaConnessioni.InializzaMappaConnessione();
      } 
   }
}