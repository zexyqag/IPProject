package TicTacToe;

import java.io.*;
import java.net.*;

public class Server {
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("Waiting for connection");
			Socket s = ss.accept();
			System.out.println("Connected!");
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			String str = (String) dis.readUTF();
			System.out.println("Client says ? " + str);
			dout.writeUTF("Hello Client!");
			dout.flush();
			dout.close();
			dis.close();
			ss.close();
		}
		
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
