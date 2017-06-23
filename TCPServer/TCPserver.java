package tunnel;
import java.io.*;
import java.net.*;
/* MultiThreaded TCP Server */ 
public class TCPserver
{
	public static void main(String[] args) throws IOException 
	{
		boolean listening = true;
		if(args.length != 3)
		{
			System.err.println("Usage: java TcpServer listenport tunnelport");
			System.exit(1);
		}
		int i = Integer.parseInt(args[0]);
		int j = Integer.parseInt(args[1]);
		int k = Integer.parseInt(args[2]);
		/* Server socket that connects to remote device */
		ServerSocket retain_connection = new ServerSocket(k);
		/* Server socket that connects to client */
		ServerSocket tcptunnel = new ServerSocket(i);
		/* Server socket that connects to remote device */
		ServerSocket tcptunnel1 = new ServerSocket(j);

		try {


			while(true)
			{
				/* This connection never closes thus keeping the remote device always connected */
				Socket retain = retain_connection.accept();
				retain.getInputStream();
				
				while (listening)
				{
					/* A new thread is created to handle the two devices */
					new MultiServerThread(tcptunnel.accept(),tcptunnel1.accept()).start();
				}
			}

		} 
		catch (IOException e) 
		{
			System.exit(-1);
		}
		retain_connection.close();
		tcptunnel1.close();
		tcptunnel.close();

	}
}




