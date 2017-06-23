package tunnel;
import java.io.*;
import java.net.*;
import com.memorynotfound.geoip.*;
public class TCPremoteclient {

	public static void main(String[] args) throws Exception
	{
		try {
			if((args[0]!=(null)) && (args[1]!=(null)))
			{
				final String foldername="/Users/kshama/Documents/workspace/gp/files";
				int portNo;
				portNo=Integer.parseInt(args[1]);
				String ip;
				ip=args[0];
				Socket remote = new Socket(ip,portNo);



				//ps.println("I'm the remote client");
				InputStreamReader isr=new InputStreamReader(remote.getInputStream());
				BufferedReader br= new BufferedReader(isr);
				String request=br.readLine();
				System.out.println(request);

				if(request.contains("list"))
				{
					PrintStream ps= new PrintStream(remote.getOutputStream());

					File filesfolder = new File(foldername);
					File[] fileslist = filesfolder.listFiles();
					String filenames="Files";

					for (int i = 0; i < fileslist.length; i++) {
						if (fileslist[i].isFile()) {
							filenames=filenames+":"+fileslist[i].getName();
							System.out.println("File " + fileslist[i].getName());
						} else if (fileslist[i].isDirectory()) {
							System.out.println("Directory " + fileslist[i].getName());
						}
					}

					ps.println(filenames);
					//System.out.println(filenames);

				}
				else if(request.contains("transfer"))
				{


					String[] tarnsferreq=request.split(":");
					String fileName=tarnsferreq[1];
					System.out.println(foldername+"/"+ fileName);
					File requestedFile=new File(foldername+"/"+ fileName);
					byte[] bytearray=new byte[(int) requestedFile.length()];

					FileInputStream file =  new FileInputStream(requestedFile); 
					BufferedInputStream buffIP=new BufferedInputStream(file);
					BufferedOutputStream buffOP=new BufferedOutputStream(remote.getOutputStream());

					buffIP.read(bytearray, 0,bytearray.length);
					buffOP.write(bytearray, 0, bytearray.length);
					//buffOP.flush(); no need of flush as close calls the flush method
					buffOP.close();
					buffIP.close();
				}
				else if(request.contains("location")){

					URL myIP=new URL("http://checkip.amazonaws.com");
					BufferedReader buff=new BufferedReader(new InputStreamReader(myIP.openStream()));
					String newIP=buff.readLine();		
					String location= LookUpProgram.main(newIP);

					System.out.println("location new:"+location);
					PrintStream ps= new PrintStream(remote.getOutputStream());
					ps.println(location);
				}
				else
				{
					System.out.println("Error reading file from server");
				}

			}
		}
		catch (Exception e) 
		{
			// TODO: handle exception
			System.out.println(e);
			System.out.println("Got an IOException: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
