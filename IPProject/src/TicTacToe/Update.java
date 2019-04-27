package TicTacToe;

import java.io.IOException;

public class Update implements Runnable {
	Client frame;

	public Update(Client frame) {
		// TODO Auto-generated constructor stub
		this.frame = frame;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("awdawd");;
			frame.show("StartScreen");
			try {
				if (frame.getDis() != null) {
					switch (frame.getDis().readInt()) {
					case 9:
						break;
					case 10:
						break;
					case 11:
						break;
					case 12:
						frame.show("GameScreen");
						break;
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
