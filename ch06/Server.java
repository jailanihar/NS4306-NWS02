package ch06;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		try {
			// Create server socket
			ServerSocket serverSocket = new ServerSocket(9101);
			System.out.println("Server started at " + new Date() + "\n");
			
			// Listen for connection request
			Socket socket = serverSocket.accept();
			
			// Create data output and input streams
			DataOutputStream toClient = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			DataInputStream fromClient = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			
			while(true) {
				// Receive radius from client
				double radius = fromClient.readDouble();
				// Calculate area
				double area = radius * radius * Math.PI;
				// Send area to client
				toClient.writeDouble(area);
				toClient.flush();
				
				System.out.println("Radius received: " + radius);
				System.out.println("Area sent: " + area);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
