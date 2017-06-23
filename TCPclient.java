package tunnel;
import java.io.*;
import java.net.*;
public class TCPclient {

		public static void main(String[] args) throws Exception
		{
			try {
				if((args[0]!=(null)) && (args[1]!=(null)) && (args[2]!=(null)))
				{
					int portNo;
					portNo=Integer.parseInt(args[1]);
					String ip;
					ip=args[0];
					Socket client = new Socket(ip,portNo);
						PrintStream ps= new PrintStream(client.getOutputStream());

						if(args[2].contains("list"))
						{
							ps.println(args[2]);//send list command to server

							//get info from server
							InputStreamReader isr=new InputStreamReader(client.getInputStream());
							BufferedReader br= new BufferedReader(isr);
							String request=br.readLine();
							String[] filenames=request.split(":");
							for (String string : filenames) {
								System.out.println(string);
							}
						}
						else if (args[2].contains("location"))
						{
							ps.println(args[2]);
						//System.out.println(args[2]);
							
							//get ip from server
							InputStreamReader isr=new InputStreamReader(client.getInputStream());
							BufferedReader br= new BufferedReader(isr);
							String request=br.readLine();
							System.out.println(request);
							
						}
						else if(args[2].contains("transfer"))
						{
							if(args[3]!=null)
							{
								String requestedfile=args[2]+":"+ args[3];
								ps.println(requestedfile);

								//get info from server
								InputStream is=client.getInputStream();
								if(is!=null)
								{
									String filenamewithextenstion=args[3];
									String fileNamewithoutext;
									String extenstion;
									int extenPos=filenamewithextenstion.lastIndexOf(".");
									if(extenPos>0)
									{
										fileNamewithoutext=filenamewithextenstion.substring(0,extenPos);
										extenstion=filenamewithextenstion.substring(extenPos+1, filenamewithextenstion.length());
										FileOutputStream fos=new FileOutputStream("/Users/kshama/Desktop/"+fileNamewithoutext+"_Transferred."+extenstion);
										BufferedOutputStream buffOP=new BufferedOutputStream(fos);
										byte[] bytearray=new byte[1];
										int procByte= is.read(bytearray, 0, bytearray.length);
										ByteArrayOutputStream byteOP=new ByteArrayOutputStream();

										do {
											byteOP.write(bytearray);
											procByte=is.read(bytearray);
										} while (procByte != -1);

										buffOP.write(byteOP.toByteArray());
										buffOP.close();
										System.out.println("File recieved successfully");
									}

								}
								else
								{
									System.out.println("Error reading file from server");
								}

							}
						}

						
						
						}
				}
			
			catch (Exception e) 
			{
				System.out.println(e);
				System.out.println("Got an IOException: " + e.getMessage());
				e.printStackTrace();
			}
			
		}

	}

