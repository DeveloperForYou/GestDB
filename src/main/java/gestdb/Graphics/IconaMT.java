package gestdb.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconaMT {
    private static String mf="";  //dir icona menu funzioni
    private static String mi="";  //dir icona menu informazioni
    private static String mva=""; //dir icona menu verifica aggiornamento
    private static String imp=""; //dir icona menu impostazioni
    private static String isb=""; //dir icona menu segnala bug
    
    public static Icon setImageMT(String menuItem){//posso prendere il nome dell'elemento che appartiene al menu per settargli l'icona
        setDir();
        switch(menuItem){
           
            case "Funzioni" : return  new ImageIcon(mf);
                                       
            case "Informazioni" : return new ImageIcon(mi);
                                     
            case "Verifica Aggiornamenti" : return new ImageIcon(mva);
                                  
            case "Impostazioni" :  return  new ImageIcon(imp);
            
            case "Segnala un bug" : return  new ImageIcon(isb);
                                       
        }
        return null;
    }
    
    private static void setDir(){
      mf=getDir()+"funzioni.png";  //dir icona menu funzioni
      mi=getDir()+"";              //dir icona menu informazioni
      mva=getDir()+"";             //dir icona menu verifica aggiornamento
      imp=getDir()+"";             //dir icona menu impostazioni
      isb=getDir()+"";             //dir icona menu segnala bug
    }
    private static String getDir(){
     return System.getProperty("user.dir")+"\\"+"icone\\mt\\";
    }
}
