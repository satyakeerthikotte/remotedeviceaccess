package tunnel;
import java.net.*;
import java.io.*;
/* Inherits Thread class */
public class MultiServerThread extends Thread
{
	public int count =0;
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
				/* A new thread is created that Relays the data from client to remote device */
				(new Relay(socket.getInputStream(), socket1.getOutputStream())).start();

			}

			if(socket1.getInputStream()!=null)
			{
				System.out.println("socket1 has input");
				/* A new thread is created that Relays the data from remote device to client */
				(new Relay(socket1.getInputStream(), socket.getOutputStream())).start();

			}

		}

		catch (IOException e)

		{
			e.printStackTrace();
		}
	}


}


