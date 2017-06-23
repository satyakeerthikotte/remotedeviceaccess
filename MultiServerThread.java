package tunnel;
import java.net.*;
import java.io.*;
import org.apache.soap.util.net.Relay;
public class MultiServerThread extends Thread
{
	public int count =0;
	//public String request;
	public Socket socket = null;
	public Socket socket1 = null;
	public MultiServerThread(Socket socket, Socket socket1) 
	{
	super("MultiServerThread");
	this.socket = socket;
	this.socket1 = socket1;
	}

	public void run() 
	{

	try 
	{
	  
		InetAddress remote_addr = socket1.getInetAddress();
		 System.out.println("The Remote client's IP is" +remote_addr);
	
			 if(socket.getInputStream()!=null)
		 
		 {
			 System.out.println("socket has input");
			 //System.out.println(socket.getInputStream().toString());
		 (new Relay(socket.getInputStream(), socket1.getOutputStream())).start();
		 
		 }

		 if(socket1.getInputStream()!=null)
		 {
			 System.out.println("socket1 has input");
			// System.out.println(socket.getInputStream().toString());
         (new Relay(socket1.getInputStream(), socket.getOutputStream())).start();
         
		 }
		 
		 System.out.println(socket.getInputStream().toString());
		 if(socket.getInputStream().toString().contains("location"))
		 {
			 System.out.println("in location loop");
		 }
		 

	     }
	catch (IOException e)
	{
 e.printStackTrace();
	}
	}
	

}


