package tunnel;
import java.io.*;
/* Inherits Thread class */
public class Relay extends Thread 
{

	static final int BUFSIZ = 1000;
	InputStream in;
	OutputStream out;
	byte buf[];
	public Relay(InputStream inputstream, OutputStream outputstream)
	{

		buf = new byte[1000];
		in = inputstream;
		out = outputstream;
		return;
	}

	public void run()
	{
		int i;
		try
		{
			while((i = in.read(buf)) > 0) 
			{
				out.write(buf, 0, i);
				out.flush();
			}
		}
		catch(IOException _ex) { }
		finally
		{
			try
			{
				in.close();
				out.close();
			}
			catch(Exception _ex) { }
		}
		return;
	}
}

