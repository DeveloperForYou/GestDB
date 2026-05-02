package gestdb.Setting;

import java.io.File;
import java.io.IOException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SettFile {
   boolean esiste=false;
   File prop;
   Properties p1;
   
   public SettFile()
   {
    if(esiste==false)
       createFile();
   
   
   
   }   
   
   public void createFile() 
   {
     prop=new File(System.getProperty("user.dir")+File.separator+"setting.prop");
     System.out.println(prop.getPath());
       try {
           prop.createNewFile();
       } catch (IOException ex) {
           Logger.getLogger(SettFile.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   public void Setting()
   {
   
   
   
   }
   
   public void closeFile()
   {
    
   
   
   }
   
   public void write()
   {
    
   
   
   }
   
   public void deleteFile()
   {
    
   
   
   }
  
}
