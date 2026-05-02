package gestdb.DBFile;

import gestdb.Update.Zippatore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipOutputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.DatabaseMetaData;

import org.apache.commons.io.FileUtils;

public class Odb {
    
    public void  prova(String nameFile, File b){
      Path file=Paths.get(b.getPath()).getParent();
      File o=new File(file+File.separator+nameFile);
      File estr=new File(file+File.separator+"DatabaseFileOf "+nameFile); 
      System.out.println(o.getName());
      String pr=(o.getName().substring(0, o.getName().length()-4));
      System.out.println(pr);       
      File r=new File(file+File.separator+pr+".zip");
      o.renameTo(r);
      try {
            estr.mkdir();
            FileOutputStream fos = new FileOutputStream(o);
            ZipOutputStream zipOS = new ZipOutputStream(fos);
            Zippatore.writeToZipFile(o.getPath(), zipOS);
            Zippatore.unzip(r.getPath(), estr.getPath());
            Path work=Paths.get(estr.getPath()+File.separator+"database");
            System.out.println(work.toString());
            File y=new File(estr.getPath()+File.separator+"CreateNecessaryFile");
            y.mkdir();
            File[] f= work.toFile().listFiles();
            for(int i=0;i<f.length;i++)
              FileUtils.moveFileToDirectory(f[i],y,false);
            File[] x=y.listFiles();
            File scor=null;
            for(int i=0;i<f.length;i++){
              System.out.println(x[i].getName());
              scor=new File(y.getPath()+File.separator+pr+"."+x[i].getName());
              x[i].renameTo(scor);
            }
            Class.forName ("org.hsqldb.jdbc.JDBCDriver");
            Connection c=DriverManager.getConnection("jdbc:hsqldb:file="+nameFile);
            
            DatabaseMetaData meta = c.getMetaData();
            ResultSet rs=meta.getTables(null,null, "%", null);
            while (rs.next()) {
              String db = rs.getString(3);
              System.out.println("\n"+db);
             }

             
                    
        } catch (IOException ex) {
            Logger.getLogger(Odb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Odb.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Odb.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
}
