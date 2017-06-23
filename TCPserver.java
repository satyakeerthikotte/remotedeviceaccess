package tunnel;
import java.io.*;
//import java.lang.*;
import java.net.*;
//import org.apache.soap.util.net.Relay;
//import tunnel.MultiServerThread;

public class TCPserver
{
	    public static void main(String[] args) throws IOException 
	    {
	       boolean listening = true;
	        if(args.length != 2)
	        {
	            System.err.println("Usage: java TcpServer listenport tunnelport");
	            System.exit(1);
	        }
	        int i = Integer.parseInt(args[0]);
	     //   String s = args[1];
	        int j = Integer.parseInt(args[1]);
	        ServerSocket tcptunnel = new ServerSocket(i);
            ServerSocket tcptunnel1 = new ServerSocket(j);
	        
	        try {
	        	
	            
	            while(true)
	            {
	            	 while (listening)
	     	        {
	     	       new MultiServerThread(tcptunnel.accept(),tcptunnel1.accept()).start();
	     	    //   new MultiServerThread(tcptunnel1.accept()).start();
	     	        }
	            //Socket socket = tcptunnel.accept();
	            //Socket socket1 = tcptunnel1.accept();
	        //    InetAddress remote_addr = socket1.getInetAddress();
	          //  int remote_port = tcptunnel1.getPort();
	         //   System.out.println("The Remote client's IP is" +remote_addr);
	           // System.out.println("TcpTunnel: tunnelling port " + i + " to port " + j + " on host " + s);
	           // (new Relay(socket.getInputStream(), socket1.getOutputStream())).start();
	           // (new Relay(socket1.getInputStream(), socket.getOutputStream())).start();
	            
	            }
	            
	        } 
	        catch (IOException e) 
	        {
	            System.exit(-1);
	        }

	    tcptunnel1.close();
	    tcptunnel.close();
	       
	    }
	}




	