package gestdb.ServiziDB;

import java.util.HashMap;
/*
 Classe di Mapping delle connessione al database 
*/
public class MappaConnessioni {
   private static HashMap<Integer,String> servizi=new HashMap(QuantiServizi());
   private static HashMap<Integer,Boolean> connessione=new HashMap(QuantiServizi());
   private static int indexConn=0;
    
   public static void gestisciMappaConnessione(int indice,boolean esito){
     connessione.replace(indice, connessione.get(indice), esito);
     setIndexConn(indice);
   }
    
   public static boolean NoConnessione(){//serve per sapere se non ce stato almeno un tentativo di connessione
     boolean esito=true;
     for(int i=0;i<connessione.size();i++){
       if(connessione.get(i)==false)
          esito=false;
       else
           return true;
     }  
    return esito;
   }

    public static int getIndexConn() {
        return indexConn;
    }

    private static void setIndexConn(int indexConn) {
        MappaConnessioni.indexConn = indexConn;
    }
   
   public static void OttieniServizi(){
      ServiziDBSupportati[] sdb=ServiziDBSupportati.values();
      for(int i=0;i<sdb.length;i++){
       servizi.put(i, sdb[i].toString());
      }
   }
   
   public static void InializzaMappaConnessione(){//inizializzo la mappa delle connessioni a false
    for(int i=0;i<QuantiServizi();i++){
       connessione.put(i, false);
    }
   }
   
   public static int QuantiServizi(){
    return ServiziDBSupportati.getQuantiValori();
   }
}