package gestdb.Update;

import gestdb.Graphics.MessaggiGrafici;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Estrattore {

   public static ArrayList unzip(InputStream inputStream, String destinationDir) throws IOException {
    MessaggiGrafici.InizioEstrazione();
    ArrayList result = null;
    final int BUFFER = 2048;
    if (inputStream != null && destinationDir != null) {
        result = new ArrayList();
        ZipInputStream zis = null;
        BufferedOutputStream bos = null;
        try {
             zis = new ZipInputStream(new BufferedInputStream(inputStream));
             ZipEntry entry = null;
             while((entry = zis.getNextEntry()) != null) 
             { 
               if (entry.isDirectory()) 
               { 
                 File dir = new File(destinationDir + File.separator + entry.getName());
                 dir.mkdirs();
               }  
    else 
    {
        int count;
        byte data[] = new byte[BUFFER];
        String fileName = destinationDir + File.separator + entry.getName();
        FileOutputStream fos = new FileOutputStream(fileName);
        bos = new BufferedOutputStream(fos, BUFFER);
        while ((count = zis.read(data, 0, BUFFER)) != -1) {
            bos.write(data, 0, count);
        }
       bos.flush();
       result.add(fileName);
       
    }
}
 zis.close();
} 
 finally {
  if (bos != null) 
  { 
   try{ 
      bos.close(); 
    } 
    catch (IOException ioe) { 
       MessaggiGrafici.EstrazioneConErrori(ioe);
       ioe.printStackTrace();
     } 
   }
  if (zis != null) 
  { 
    try { 
        zis.close(); 
     } 
    catch (IOException ioe) { 
       MessaggiGrafici.EstrazioneConErrori(ioe);
       ioe.printStackTrace();
    } 
   }
  }
 }
  if(result!=null)
    MessaggiGrafici.EstrazioneCompletata();
 return result;
 }
}