import java.io.*;
import java.net.*;

class Client{
	public static void main(String argv[]) throws Exception{

		// Declare variables.
		String serverAddress = new String(argv[0]);
		String portNumber = new String(argv[1]);
		String sentence = new String(argv[2]);

		String modifiedSentence;
		String input;

		int n_port = Integer.parseInt(portNumber);
		int r_port;
		
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];

		// Create TCP client socket, connect to server.
		Socket clientSocket1 = new Socket(serverAddress, n_port);

		// Create output stream attached to socket
		DataOutputStream outToServer = new DataOutputStream(clientSocket1.getOutputStream());

		// Create input stream attached to socket
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));

		// Send line to server to initiate  negotiation.
		outToServer.writeBytes(sentence + '\n');

		// Read line from server which contains r_port
		input = inFromServer.readLine();
		//System.out.println("r_port:" + input);

		// Convert input into r_port
		r_port = Integer.parseInt(input);

		// Since we get the r_port from the server, we can close the TCP connection now.
		clientSocket1.close();

		// Create UDP client socket
		DatagramSocket clientSocket2 = new DatagramSocket();

		// Translate serverAddress to IP address using DNS
		InetAddress IPAddress = InetAddress.getByName(serverAddress);

		sendData = sentence.getBytes();

		// Create datagram with data-to-send, length, IP addr, port
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, r_port);

		// Send datagram to server
		clientSocket2.send(sendPacket);

		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

		// Read datagram from server
		clientSocket2.receive(receivePacket);

		modifiedSentence = new String(receivePacket.getData());

		System.out.println("FROM SERVER: " + modifiedSentence);
		clientSocket2.close();

	}
}
