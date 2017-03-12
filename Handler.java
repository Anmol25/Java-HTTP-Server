//Anmol Rathi 1001030212
package networking_lab_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anmol
 * This class is used to handle the connection being brought
 * forth by client-side request.
 *
 * This class takes a parameter of type Socket and the name of the root folder.
 */
public class Handler extends Thread{
    
    Socket socket;//this is the client-side socket
    String root_folder;
    
  
    BufferedReader reader;
    DataOutputStream outputStream;
    FileInputStream fileInputStream;
   
    String response;
    String filename;
    
    String requestline;
    
    public Handler(Socket socket, String root) throws Exception
    {
        
        this.socket = socket;
        this.root_folder = root;
      }

   
    
    /*
    The run method below will read the HTTP request
    */
    @Override
    public void run() 
    {
        
        
        try
        {
         response();

        //Here, I have created a 3000 byte buffer to contain the file information.
        byte[] buffer = new byte[3000];
        int bytes = 0;
        
        while((bytes = fileInputStream.read(buffer)) != -1)
        {
            //This loop to output all the byte-information gathered inside the buffer.
            outputStream.write(buffer, 0, bytes);
        }
        
        fileInputStream.close();
        outputStream.close();
        socket.close();
        reader.close();
        reader.close();
        
                
                
        }catch(FileNotFoundException e)
       {
           //System.out.println("\nFile not found. Error 404.\n");
       }catch(Exception e)
       {
           //System.out.println("\nBad request. Error 400.\n");
           
       }
        
        
    }
    
    public void response() throws IOException
           
    {
        
        
        //Get the input stream from the client-side socket.
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //Get the output stream from the client-side socket.
        outputStream = new DataOutputStream(socket.getOutputStream());
        
        //Read the first line of the http request.
        requestline = reader.readLine();
        StringTokenizer t = new StringTokenizer(requestline);
        t.nextToken();
        //Tokenize the first line of the request to get the file name that is being requested from client.
        filename = t.nextToken();
        filename = root_folder+filename;
        
        //With the given file name, create a input stream for that file.
        this.fileInputStream = new FileInputStream(filename);
        
      
                 
        } 
        
    }


