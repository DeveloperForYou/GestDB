package gestdb.Update;

import gestdb.Graphics.MessaggiGrafici;
import gestdb.init.Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.lang.management.ManagementFactory;

import java.net.URL;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UpdateV2 {    //PRIMA DEL RILASCIO TESTARE ANCHE L'APPLICATORE, rimuovendo commento a riga 96
    
    private String url="https://albertogavazzi.it/File/Progetti/GestDB_Beta/"; //percorso dove si trovano i file di aggiornamento 
    private String vc="";// versione corrente dell'app
    private String vu = "";// nuova versione del programma che si trova sul sito web
    private String filename="";//nome del file completo
    
    public UpdateV2(String vc){
       this.vc=vc;
    }
    
    public void Pre_Download(){
        try {
           Document doc = Jsoup.connect(url).get();
           vu=doc.getElementsByAttributeValueContaining("href", "GestDB").text().replace(".zip", "");//nome del file senza estenzione
           filename=doc.getElementsByAttributeValueContaining("href", "GestDB").attr("href");
           if(controlloUpdate()){
             DowloadAndUpdate();
           }   
        } catch (IOException ex) {
            Logger.getLogger(UpdateV2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean controlloUpdate() {
        if (vc.compareTo(vu) == 0) {//caso in cui nessun aggiornamento
            MessaggiGrafici.NessunAggiornamentoDisponibile();
            return false;
        } else {
            if (vc.compareTo(vu) > 0) {//caso versione più recente
                MessaggiGrafici.VersioneRecente();
                return false;
            }
        }
        MessaggiGrafici.AggiornamentoDisponibile(vu);
        return true;
    }

    private void DowloadAndUpdate() {
        if (vu != null && !vu.equals("")) {
            try {  
                Path ap = Paths.get(System.getProperty("user.dir")).getParent();
                String po = ap.toString()+"\\Update";//direcotory di estrazione del file zip
                BufferedInputStream in = new BufferedInputStream(new URL(url+filename).openStream());
                FileOutputStream fos = new FileOutputStream(filename);
                byte dataBuffer[] = new byte[4096];
                int bytesRead;
                while ((bytesRead = in.read(dataBuffer, 0, 4096)) != -1) 
                 fos.write(dataBuffer);
                fos.close();
                File file = new File(filename); 
                File fileR=new File(System.getProperty("user.dir")+"\\"+vu+".zip");
                FileUtils.moveFile(file, fileR);
                Estrattore.unzip(new FileInputStream(fileR), po);
            } catch (FileNotFoundException ex) {
                MessaggiGrafici.EstrazioneImpossibile();
            } catch (IOException ex) {
                MessaggiGrafici.EstrazioneImpossibile();
            }
        } else {
            eccezioni();
        }
       //Applicatore();
    }

    private void Applicatore() {
        MessaggiGrafici.AggiornamentoAvviato();
        Applicatore.applica(Paths.get(System.getProperty("user.dir")).getParent() + File.separator + "Update",
                 System.getProperty("user.dir"));
        try {
            RestartAPP();
        } catch (IOException ex) {
            MessaggiGrafici.AggiornamentoInterrotto();
            ex.printStackTrace();
        }
    }

    private void eccezioni() {
        try {
            throw new FileNotFoundException("File corrotto o non presente ");
        } catch (FileNotFoundException ex) {

        }

        try {
            throw new IOException("Errore di scrittura o lettura del file: I/O Error");
        } catch (IOException ex) {

        }

        try {
            MessaggiGrafici.AggiornamentoInterrotto();
            RestartAPP();
        } catch (RuntimeException | IOException ex) {

        }
    }

    public void RestartAPP() throws IOException {
        StringBuilder cmd = new StringBuilder();
        cmd.append(System.getProperty("java.home")).append(File.separator).append("bin").append(File.separator).append("java ");
        ManagementFactory.getRuntimeMXBean().getInputArguments().forEach((jvmArg) -> {
            cmd.append(jvmArg).append(" ");
        });
        cmd.append("-cp ").append(ManagementFactory.getRuntimeMXBean().getClassPath()).append(" ");
        cmd.append(Main.class.getName()).append(" ");
        String[] args = new String[4];
        for (String arg : args) {
            cmd.append(arg).append(" ");
        }
        Runtime.getRuntime().exec(cmd.toString());
        System.exit(0);
    }
}