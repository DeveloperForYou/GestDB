package gestdb.Internet;

import java.io.IOException;

import java.net.Socket;
import java.net.InetSocketAddress;

public class CheckInternet {
   public static boolean checkInternetConnection()
    {
     boolean status = false;
     Socket sock = new Socket();
     InetSocketAddress address = new InetSocketAddress("www.google.com", 80);
     try{
        sock.connect(address, 3000);
        if(sock.isConnected()){
          status = true;
        } 
     }
     catch(IOException e)
     {
         status = false;       
     }
     finally
     {
        try
         {
            sock.close();
         }
         catch(IOException e){
         
         }
     }
    return status;
    }

}
