package TicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerEventHandeler implements Runnable {

	private Socket s;
	private DataOutputStream dout;
	private DataInputStream dis;

	private Client client;

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			if (dis != null) {
				try {
					client.serverEvents(dis.readInt());
	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	}

	public void clientEvents(int i){
		try {
			dout.writeInt(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ServerEventHandeler(Client client) {
		this.client = client;

	}

	public void connectToServer(String string) throws UnknownHostException, IOException {
		s = new Socket(string, 6666);
		dout = new DataOutputStream(s.getOutputStream());
		dis = new DataInputStream(s.getInputStream());
		System.out.println("yaa");
	}

	public void DisconnectFromServer() {

	}

}
