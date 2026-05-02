package gestdb.ServiziDB;

import gestdb.DBFile.Access;
import gestdb.Graphics.MessaggiGrafici;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/*
  Questa classe dovrebbe gestire  le chiamate al database scegliendo la classe opportuna
*/
public class GestioneChiamateDB {
    
    private static String Servizio;
    private static String FileName;
    
    public static String getServizio() {
        return Servizio;
    }
    
    public static String getFileName() {
        return FileName;
    }

    public static void setServizio(String servizio) {
        Servizio = servizio;
    }
    
    public static void setFileName(String filename) {
        FileName=filename;
    }
    
    public static void Azione(String Azione,String query){
       String servizio=getServizio();
       if(servizio!=null){//se servizio!=null Allora GestDB contatta il servizio giusto altrimenti contatta la classe giusta a seconda dell'estenzione del file
        if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.ONE))){//servizio=MySQL
         switch(Azione){
             case "Qualsiasi Comando" : MySQL.AllExecuteCommands(query);
                                        break;
             
             case "Selezione" :         if(StringUtils.startsWithIgnoreCase(query, "SELECT")){
                                          MySQL.ExecuteQuery(query);
                                        }
                                        break;
             
             case "Mostra Tabella" :  MySQL.MostraGrafica(query);
                                      break;
            }
       }
       if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.TWO))){//servizio=Postgre
           switch(Azione){
             case "Qualsiasi Comando" : PostgreDB.AllExecuteCommands(query);
                                        break;
             
             case "Selezione" :         if(StringUtils.startsWithIgnoreCase(query, "SELECT")){
                                          PostgreDB.ExecuteQuery(query);
                                        }
                                        break;
             
             case "Mostra Tabella" :  PostgreDB.MostraGrafica(query);
                                      break;
            }
        }
      }
      else{
           String filename=getFileName();
           String ext=FilenameUtils.getExtension(filename);//estensione del file di input
           System.out.println(filename+" "+ext);
           switch(ext){
              case ".odb": MessaggiGrafici.FileNonSupportato();
                           //odb o=new odb();
                           //o.prova(a.getName(),a);
                           break;
                                                                       
              case ".mdb": Access mdb=new Access();
                           SwitchAzioneAccess(Azione,query,mdb);                                             
                           break;
                                                                         
              case ".accdb": Access accdb=new Access();
                             SwitchAzioneAccess(Azione,query,accdb);                                                
                             break;
                                                                           
              case ".xlsx":  MessaggiGrafici.FileNonSupportato();
                             break;
                                                                          
              default: MessaggiGrafici.FileSupportati();
                       break;
            }           
        } 
    } 
    
    private static void SwitchAzioneAccess(String Azione,String query,Access acc){
           switch(Azione){
             case "Qualsiasi Comando" : acc.AllExecuteCommands(query);
                                        break;
             
             case "Selezione" :         if(StringUtils.startsWithIgnoreCase(query, "SELECT")){
                                          acc.ExecuteQuery(query);
                                        }
                                        break;
             
             case "Mostra Tabella" :  acc.MostraGrafica(query);
                                      break;
            }
    }
    
    public static boolean ValidaURL(String url){//controlla sintatticamente il formato del'url
        boolean valido=false;
        if(!url.contains(":") & !url.contains("/") | url.contains(":") & !url.contains("/") | !url.contains(":") & url.contains("/") ){
         MessaggiGrafici.FormatoUrlErrato();
         return valido;
        }
        else{
            if(url.substring(0, url.indexOf(":")).length()>0 & url.substring(url.indexOf(":"),url.indexOf("/" )).length()>0  & url.substring(url.indexOf("/" )).length()>0 ){
              valido=true;
            }
        }   
      return valido;  
    }
    
    public static String[] getServiziSupportati(){
       ServiziDBSupportati[] sd=ServiziDBSupportati.values();
       String[] val=new String[sd.length];
       for(int i=0;i<val.length;i++)
        val[i]=sd[i].toString();
       return val;
    }
    
    public static boolean PreparaConnessione(String url,String user,String pw,String servizio){
       boolean prepara=false;
       if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.ONE))){//servizio=MySQL
         MySQL mysql;
         if(pw.isEmpty())
             mysql=new MySQL(url,user,"");
         else
             mysql=new MySQL(url,user,pw);
          if(mysql.connetti()!=null)
              prepara=true;
          MappaConnessioni.gestisciMappaConnessione(ServiziDBSupportati.OrdineEnum(ServiziDBSupportati.ONE),true);
       }
       if(servizio.equals(ServiziDBSupportati.getValue(ServiziDBSupportati.TWO))){//servizio=Postgre
          PostgreDB postgre;
          if(pw.isEmpty())
              postgre=new PostgreDB(url,user,"");
          else
              postgre=new PostgreDB(url,user,pw);
          if(postgre.connetti()!=null)
              prepara=true;
          MappaConnessioni.gestisciMappaConnessione(ServiziDBSupportati.OrdineEnum(ServiziDBSupportati.TWO),true);
       }
     return prepara;
    }
  
}