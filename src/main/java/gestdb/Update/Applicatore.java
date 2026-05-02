package gestdb.Update;

import gestdb.Graphics.MessaggiGrafici;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils; 

public class Applicatore {
   
  /**
    @param dirUP:  directory cartella dei file di aggiornamento 
    @param dirPRO: directory cartella dei file attuali 
  */
   public static void applica(String dirUP,String dirPRO)
   {
     boolean fatto=false;
     File up=new File(dirUP);
     File pr=new File(dirPRO);
     for(File scor : up.listFiles())
     {
      try{
          FileUtils.copyToDirectory(scor, pr);
          fatto=true;
        }
        catch (IOException ex) {
              MessaggiGrafici.AggiornamentoNonCompletato(ex);
              ex.printStackTrace();
             }
        }
       if(fatto){
         MessaggiGrafici.AggiornamentoCompletato();
       }
   }
}