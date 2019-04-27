package TicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server implements Runnable {

	Scanner scanner = new Scanner(System.in);
	int[] board = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int turn;
	ServerSocket ss;
	Socket s;

	@Override
	public void run() {

		try {
			ServerSocket ss = new ServerSocket(6666);
			System.out.println("Waiting for connection from player 1");
			Socket player1 = ss.accept();
			System.out.println("Player 1 onnected!");
			Socket player2 = ss.accept();
			System.out.println("Player 2 onnected!");

			DataInputStream player1dis = new DataInputStream(player1.getInputStream());
			DataOutputStream player1dout = new DataOutputStream(player1.getOutputStream());
			DataInputStream player2dis = new DataInputStream(player2.getInputStream());
			DataOutputStream player2dout = new DataOutputStream(player2.getOutputStream());
			player1dout.writeInt(12);
			System.out.println("Game can now begin");

			while (true) {
				if (turn % 2 == 0) {
					System.out.println("Player 1s turn");
					int play = player1dis.readInt();
					board[play] = 1;
					player1dout.writeInt(play);
					player2dout.writeInt(play);
					System.out.println("Player 1 played their turn");
				} else {
					System.out.println("Player 2s turn");
					int play = player2dis.readInt();
					board[play] = 2;
					player1dout.writeInt(play);
					player2dout.writeInt(play);
					System.out.println("Player 2 played their turn");
				}
				if (hasWinner()) {
					player1dout.writeInt(9+turn % 2);
					player2dout.writeInt(9+turn % 2);
					break;
				} else if (noWinner()) {
					player1dout.writeInt(11);
					player2dout.writeInt(11);
					break;
				}
				turn++;
			}
			
			player1dis.close();
			player1dout.close();
			player2dis.close();
			player2dout.close();
			ss.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// preliminary i swear :)
	public boolean hasWinner() {
		return (board[0] != 0 && board[0] == board[1] && board[0] == board[2])
				|| (board[3] != 0 && board[3] == board[4] && board[3] == board[5])
				|| (board[6] != 0 && board[6] == board[7] && board[6] == board[8])
				|| (board[0] != 0 && board[0] == board[3] && board[0] == board[6])
				|| (board[1] != 0 && board[1] == board[4] && board[1] == board[7])
				|| (board[2] != 0 && board[2] == board[5] && board[2] == board[8])
				|| (board[0] != 0 && board[0] == board[4] && board[0] == board[8])
				|| (board[2] != 0 && board[2] == board[4] && board[2] == board[6]);
	}
	public boolean noWinner() {
		for (int i : board) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}
}
