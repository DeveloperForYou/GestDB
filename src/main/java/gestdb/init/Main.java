package gestdb.init;

import gestdb.Internet.CheckInternet;
import gestdb.Graphics.FinestraPrincipale;
import gestdb.ServiziDB.MappaConnessioni;
import gestdb.Update.UpdateV2;

import java.io.File;
import java.io.IOException;

import java.lang.management.ManagementFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;


public class Main {
    
    public static void main(String []args) {
       pulizia();
       Launcher();
       JOptionPane.showMessageDialog(null,
               "Questo programma  è in fase di sviluppo, "
       + "\n si prega  di utilizzarlo con cautela nei vostri database!"
       + "\n Buon utilizzo del programma! ","informazione importante sul programma", JOptionPane.INFORMATION_MESSAGE);
       //Grafica gr=new Grafica();
       FinestraPrincipale gr=new FinestraPrincipale();
       int scelta=JOptionPane.showConfirmDialog(null, "Vuoi verificare la presenza di nuovi aggiornamenti per l'app?"
               , "Utility di aggiornamento", JOptionPane.INFORMATION_MESSAGE);
       if(scelta==JOptionPane.OK_OPTION)
       {
         CheckInternet ci=new CheckInternet();
         if(ci.checkInternetConnection()){
            JOptionPane.showMessageDialog(null, "Controllo presenza Connessione a internet "
                     ,"Utility di Controllo", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null, "Verifico Aggiornamenti in corso"
                + "\n In caso di aggiornamento disponibile,"
                + "\n Esso sarà installato automaticamente", "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
            UpdateV2 u=new UpdateV2(gr.getTitolo());
            u.Pre_Download();
        }
       }
       else 
       {
        JOptionPane.showMessageDialog(null,"Verifica Aggiornamenti Annullata"
                + "\n La verifica degli aggionamenti verrà richiesta al prossimo avvio"
                + "\n Avvio  dell'app in corso..","Utility di Aggiornamento" , JOptionPane.INFORMATION_MESSAGE);
       }
       gr.setVisible(true);
       
       MappaConnessioni.OttieniServizi();
       MappaConnessioni.InializzaMappaConnessione();
    }
    
    public static void Launcher(){//carica automaticamente le librerie 
     StringBuilder cmd = new StringBuilder();
     cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
     ManagementFactory.getRuntimeMXBean().getInputArguments().forEach((jvmArg) -> {
         cmd.append(jvmArg).append(" ");
      });
     cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
      try {
            Runtime.getRuntime().exec(cmd.toString());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void pulizia()//pulisce la directory del programma dalla cartella di aggiornamento prodotta dall'estrattore e dal file zippato
    {
     String m=Paths.get(System.getProperty("user.dir")).getParent()+File.separator+"Update"+File.separator+"Update";
     File unzip=new File(m);
     if(unzip.isDirectory()) {
         try {
             FileUtils.forceDelete(unzip);
             FileUtils.forceDelete(unzip.getParentFile());
         } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
      if(unzip.isDirectory()) {//Ridondante ma necessario per eliminare corretamente la directory del file di Update
         try {
             FileUtils.forceDelete(unzip);
             FileUtils.forceDelete(unzip.getParentFile());
         } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
        }
      Path p=Paths.get(System.getProperty("user.dir")).getParent();
      String n=p.toString();
      String f=null;
      File zip=null;
       for(File w: p.toFile().listFiles())
       {
        f=w.getName();
        if(f.endsWith(".zip"))
        {
          zip=new File(n+File.separator+f);
          try {
              FileUtils.forceDelete(zip);
          } catch (IOException ex) {
              Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
          }
       }
    }
   }
 }