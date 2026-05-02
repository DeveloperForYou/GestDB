package gestdb.Update;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import gestdb.init.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import java.lang.management.ManagementFactory;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

@Deprecated
public class Update{
     
     private static  final String APP_KEY = "dpuviamm2c0mrnz";
     private static  final String APP_SECRET = "pvczc8k7ojh5uy1";
     private String vc;//versione corrente del programma
     private static final String ACCESS_TOKEN = "WDEGu0_LRsAAAAAAAAAADmPZNrfndNv-JgbNiNq6PRbI4IDr-qCOBpr3AHbOSC5M";
     public  String vu;//versione nuova del programma
     
     public Update(String vc)
     {
       this.vc=vc;
     }
     
    public  void  download() throws IOException, DbxException
     {
       DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
       DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/").build();
       DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
       FullAccount account = client.users().getCurrentAccount();
       ListFolderResult result = client.files().listFolder("");
        while (true) {
           for (Metadata metadata : result.getEntries()){
                vu=metadata.getName();
           }
           if(!result.getHasMore()) 
             break;
          result = client.files().listFolderContinue(result.getCursor());
        }
        if(controlloUpdate()){//prima di scaricare controlla la versione su server
           DbxDownloader<FileMetadata> downloader = client.files().download("/"+vu);
           try {
               Path ap=Paths.get(System.getProperty("user.dir")).getParent();
               String po=ap.toString();
               OutputStream out = new FileOutputStream(po+File.separator+vu);
               downloader.download(out);
               aggiorna();
               out.close();
           }catch (DbxException | FileNotFoundException | RuntimeException ex) {
             System.out.println(ex.getMessage());
           }
     }
   } 
      
  private  boolean controlloUpdate()
  {
    if(vc.compareTo(vu)==0){//caso in cui nessun aggiornamento
        JOptionPane.showMessageDialog(null, "nessun"
                +"  aggiornamento", "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
        return false;
    }
    else{
       if(vc.compareTo(vu)>0){//caso versione più recente
          JOptionPane.showMessageDialog(null, "nessun"
                + "  aggiornamento:\n versione più recente in esecuzione ", "Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
        return false;
        }
    }
    JOptionPane.showMessageDialog(null, "Aggiornamento disponibile"
              + "\n Nuova Versione disponibile: "+vu
                ,"Utility di Aggiornamento", JOptionPane.INFORMATION_MESSAGE);
  return true;
 }

 //Questo metodo si preoccupa di estrarre il contenuto del file compresso e di applicare l'aggiornamento 
 private void aggiorna() 
 {
   if(vu!=null && !vu.equals("")){//../+vu
    Path ap=Paths.get(System.getProperty("user.dir")).getParent();
    String po=ap.toString();
    File file=new File(po+File.separator+vu);
    try {
           Estrattore.unzip(new FileInputStream(file), po);
       } catch (FileNotFoundException ex) {
           JOptionPane.showMessageDialog(null, "Estrazione Impossibile", "Utility di Aggiornamento: Errore di I/O", JOptionPane.ERROR_MESSAGE);
       } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Estrazione Impossibile", "Utility di Aggiornamento: Errore di I/O", JOptionPane.ERROR_MESSAGE);   
       }
    }
    else{
       eccezioni();
   }
   Applicatore();
 }
 
  public void Applicatore()
  {
   JOptionPane.showMessageDialog(null,"Avvio Aggionamento in corso..."
           + "\n Si prega di attendere "
           + "\n non spegnere il computer", "Utility di Aggiornamento", 
           JOptionPane.INFORMATION_MESSAGE);
   Applicatore.applica(Paths.get(System.getProperty("user.dir")).getParent()+File.separator+"Update"+File.separator+"Update"
           , System.getProperty("user.dir"));
    try {
         RestartAPP();
         } catch (IOException ex) {
             Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
         }
  }
 
 private void eccezioni()
 {
  try{
    throw new FileNotFoundException("File corrotto o non presente ");
    }
    catch (FileNotFoundException ex) {

     }
     
  try {
     throw new IOException("Errore di scrittura o lettura del file: I/O Error");
     } 
     catch (IOException ex) {

     }
 
  try{
     JOptionPane.showMessageDialog(null, "Si è verificato un errore"
             + "\nIl programma si riavvierà"
             ,"Errore Grave",JOptionPane.ERROR_MESSAGE);
      RestartAPP();
    }
    catch(RuntimeException |IOException  ex)
    {
    
    }
 }
  
 public void RestartAPP() throws IOException
 {
   StringBuilder cmd = new StringBuilder();
   cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
   ManagementFactory.getRuntimeMXBean().getInputArguments().forEach((jvmArg) -> {
         cmd.append(jvmArg).append(" ");
      });
   cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
   cmd.append(Main.class.getName()).append(" ");
   String [] args = new String[4];
   for (String arg : args) 
      cmd.append(arg).append(" ");
   Runtime.getRuntime().exec(cmd.toString());
   System.exit(0);
 }
}