INTRODUCTION:

Remote Device Management(RDM) has been implemented using java language,Eclipse.
RDM consists of three major components: TCPServer, RemoteDevice and TCPClient.
TCPServer is always on the listening mode to accept connections from both RemoteDevice and TCPClient. RemoteDevice is always connected to the server and awaits for the requests from the client, which is relayed to it by the server and responds to them accordingly. TCPClient connects to the server to request the whereabouts of the RemoteDevice, there are three possible requests:
list
transfer
location

list : The list command displays the list of all files available at the provided path of the RemoteDevice(as an argument) 

transfer: The transfer command saves a file physically from RemoteDevice to the TCPClient. The path at which the file is save can be modified accordingly in the code, defaulted to ~/files/ folder. The file name and path has to be given as additional arguments.

Location: The location command provides the exact location of the RemoteDevice to the TCPClient.

CODE EXCECUTION:

RDM has been implemented using three terminals with Eclipse installed in them, the flow goes as respectively

TCPServer:
Application used:Eclipse IDE for Java Developers
Version: Kepler Service Release 2
Project: Java Project

The TCPServer java program run configurations has to be set as it needs three input arguments, port number for TCPClient and RemoteDevice respectively.
For instance, the arguments tab under run configuration will have
		2400 2000 2001
where 2400 is the port number to connect to the TCPClient,
	2000 is the port number to connect to the RemoteDevice
	2001 is the port number to connect to the RemoteDevice
There are two port being used as two ServerSockets will be listening to accept connections from two RemoteDevice's sockets: one to process the requested command, one to keep the RemoteDevice always connected to the TCPServer.

RemoteDevice:
Application used:
Version: 
Project:
Thrid party database:GeoLite 

The RemoteDevice java program run configurations has to be set as it needs three input arguments, server IP address and port numbers to connect to the server.
For instance, the arguments tab under run configuration will have
		10.189.95.33 2000 2001
where 10.189.95.33 is the server IP address,
	2000 is the port number to connect to the TCPServer
	2001 is the port number to connect to the TCPServer
There are two ports being used as two sockets will be created thus making two connections with the TCPServer: one to process the requested command, one to keep the RemoteDevice always connected to the TCPServer.

TCPClient:
Application used:Eclipse IDE for Java Developers
Version: Kepler Service Release 2
Project: Java Project

List:
The TCPClient java program run configurations has to be set as it needs four input arguments, server IP address, server port number to connect to the server, list command and the path of the folder
For instance, the arguments tab under run configuration will have
		10.189.95.33 2400 list /users/kritikagopalakrishnan/Documents/workspace/tunnel/filesfolder/

where 10.189.95.33 is the server IP address,
	2400 is the port number to connect to the TCPServer
	list is the command to get all the list of files
	/users/kritikagopalakrishnan/Documents/workspace/tunnel/filesfolder/ is the path of the folder of the RemoteDevice

Transfer:
The TCPClient java program run configurations has to be set as it needs five input arguments, server IP address, server port number to connect to the server, transfer command, file name and the path of the folder
For instance, the arguments tab under run configuration will have
		10.189.95.33 2400 transfer DVD.java /users/kritikagopalakrishnan/Documents/workspace/tunnel/filesfolder/

where 10.189.95.33 is the server IP address,
	2400 is the port number to connect to the TCPServer
	transfer is the command to save a file from RemoteDevice
	 DVD.java is the file name of the file which has to be saved
	/users/kritikagopalakrishnan/Documents/workspace/tunnel/filesfolder/ is the path of the folder of the RemoteDevice

Location:
The TCPClient java program run confihurations has to be set as it needs three input arguments, server IP address, server port number to connect to the server, location command
For instance, the arguments tab under run configuration will have
		10.189.95.33 2400 location
where 10.189.95.33 is the server IP address,
	2400 is the port number to connect to the TCPServer
	location is the command to get the RemoteDevice location