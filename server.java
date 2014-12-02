import java.io.*;
import java.net.*;
import java.lang.Integer;
import java.util.Random;

class Server{
	public static void main(String argv[]) throws Exception{
		String clientSentence;
		StringBuffer reversedSentence;
		String output;

		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		// create welcomeSocket by using a port number that is automatically allocated.
		ServerSocket welcomeSocket = new ServerSocket(0);

		// print out the <n_port> value that it is using.
		int n_port = welcomeSocket.getLocalPort();
		System.out.println(new Integer(n_port).toString());

		while(true){

			// Wait, on welcoming socket for contact by client
			Socket connectionSocket = welcomeSocket.accept();

			// Create input stream, attached to socket
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			// Create output stream, attached to socket
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

			// Read in line from socket
			clientSentence = inFromClient.readLine();

			// Create datagram socket and binds it to any available port.
			DatagramSocket serverSocket = new DatagramSocket();

			// get the <r_port> value that serverSocket is using.
			int r_port = serverSocket.getLocalPort();

			output = new Integer(r_port).toString();
			
			// debug
			//System.out.println(new Integer(r_port).toString());

			// Write out line to socket
			outToClient.writeBytes(output + '\n');

			// Create space for received datagram
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

			// Receive datagram
			serverSocket.receive(receivePacket);

			String sentence = new String(receivePacket.getData());

			// Get IP addr port #, of sender
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();


			reversedSentence = new StringBuffer(sentence);

			// output is the reversed sentence.
			output = reversedSentence.reverse().toString() + '\n';
			sendData = output.getBytes();

			// Create datagram to send to client
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

			// Write out datagram to socket
			serverSocket.send(sendPacket);

			// close the UDP connection.
			serverSocket.close();

			// End of while loop, loop back and wait for another client connection
		}
	}
}
