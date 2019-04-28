package TicTacToe;

public class Update implements Runnable {
	Client frame;

	public Update(Client frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void run() {
		while (true) {
			int action = frame.getDisInt();
			System.out.println(action);
			switch (action) {
			case 9:
				frame.show("YouWonScreen");
				break;
			case 10:
				frame.show("YouLostScreen");
				break;
			case 11:
				frame.show("TieScreen");
				break;
			case 12:
				frame.show("GameScreen");
				break;
			case 0:
				break;
			}
		}
	}
}
