package gestdb.Graphics;

import java.awt.Image;
        
import javax.swing.ImageIcon;

/*
Questa classe si occuperà di gestire le icone dell'interfacce grafiche corrispondenti
*/
public class Icona {
    private static String ifp="";  //dir icona finestra principale
    private static String isb="";  //dir icona segnalatore bug
    private static String icdb=""; //dir icona connessionedb
    private static String iup=""; //dir icona aggiornamento
    private static String iqf="";//dir icona queryform
    private static String ivt="";//dir icona visualizza tabella
    
    public static Image setImage(String nameClass){//posso prendere il nome della classe per settargli l'icona
        setDir();
        switch(nameClass){
           
            case "FinestraPrincipale" : return new ImageIcon(ifp).getImage();
                                       
            case "BugSegnalator" : return new ImageIcon(isb).getImage();
                                     
            case "ConnessioneDB" : return new ImageIcon(icdb).getImage();
                                  
            case "Updater" :  return new ImageIcon(iup).getImage();
            
            case "QueryForm" : return new ImageIcon(iqf).getImage();
                
            case "VisualizzaTabella" : return new ImageIcon(ivt).getImage();     
                                     
        }
        return null;
    }
    
    private static void setDir(){
      ifp=getDir()+"icona.png";  //dir icona finestra principale
      isb=getDir()+"segnalaBug.png";  //dir icona segnalatore bug
      icdb=getDir()+"ConnDB.png"; //dir icona connessionedb
      iup=getDir()+"update.png"; //dir icona aggiornamento
      iqf=getDir()+"query.png";//dir icona queryform
      ivt=getDir()+"tabella.png";//dir icona visualizza tabella
    }
    
    private static String getDir(){
     return System.getProperty("user.dir")+"\\"+"icone\\";
    }
    
    
}
