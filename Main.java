
package networking_lab_1;



import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Anmol
 */
public class Main {
    
    
    ServerSocket server;
    
    static String root_folder;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
       
        
        /*
        When the program begins, it will ask the user of this program
        for a root folder that has all the html files that the client (browser)
        will get the files from. 
        
        The prgram will end if the user does not give the root folder information.
        */
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter the root folder where the .html files are: \n");
        root_folder = scanner.nextLine();
        System.out.println("This program will look in the "+root_folder+" for all requested html files.\n\n"
                + "This program's server is listening on port 7654.\n\n");
        
        
        new Main().startServer();
    }
    
    public void startServer() throws Exception
    {
        System.out.println("\n\nServer has started...\n\n");
        /*
        Below, I have created a server at port # 7654.
        This is specifically a server-side socket.
        */
        server = new ServerSocket(7654);
        
        listen();
        
    }
    
    private void listen() throws Exception
    {
        while(true)
        {
            /*
            The client has its own socket that 'binds' to the
            server-side socket and accepts client requests. 
            */
            Socket socket = server.accept();
            
            /*
            The arguement pass to the Handler class is the
            client-socket.
            */
            Handler handler = new Handler(socket, root_folder);
            /*
            The start method will start the thread of the handler class,
            by running the run method of the handler class.
            */
            handler.start();
        }
               
    }
    
    
    
}