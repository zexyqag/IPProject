package TicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	int[] board = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	int turn;

	ServerSocket ss;

	Socket player1;
	DataInputStream player1dis;
	DataOutputStream player1dout;

	Socket player2;
	DataInputStream player2dis;
	DataOutputStream player2dout;

	@Override
	public void run() {

		try {
			ss = new ServerSocket(6666);
			System.out.println("Waiting for connection from player 1");

			player1 = ss.accept();
			player1dis = new DataInputStream(player1.getInputStream());

			player1dout = new DataOutputStream(player1.getOutputStream());
			System.out.println("Player 1 connected!");

			player2 = ss.accept();
			player2dis = new DataInputStream(player2.getInputStream());
			player2dout = new DataOutputStream(player2.getOutputStream());
			System.out.println("Player 2 connected!");

			player1dout.writeInt(12);
			System.out.println("Game can now begin");

			while (true) {
				turn++;
				if (turn % 2 == 1) {
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
					if (turn % 2 == 1) {
						player1dout.writeInt(9);
						player2dout.writeInt(10);
					} else if (turn % 2 == 0) {
						player1dout.writeInt(10);
						player2dout.writeInt(9);
					}
					newGame();

				} else if (noWinner()) {
					player1dout.writeInt(11);
					player2dout.writeInt(11);
					newGame();

				}

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			if (i == 0) {
				return false;
			}
		}
		return true;
	}

	public void newGame() {
		board = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		turn = 0;
	}

	public void theEnd() {
		try {
			if (player1dis != null) 
			player1dis.close();
			
			if (player1dout != null) 
			player1dout.close();
			
			if (player1 != null) 
			player1.close();
			
			if (player2dis != null)
			player2dis.close();
			
			if (player2dout != null)
			player2dout.close();
			
			if (player2 != null)
			player2.close();
			
			if (ss != null)
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block

		}

	}

}
