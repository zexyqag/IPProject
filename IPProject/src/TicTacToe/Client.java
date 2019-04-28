package TicTacToe;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import java.awt.GridLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class Client extends JFrame {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public ArrayList<JPanel> menuScreens = new ArrayList<>();
	public JButton[] gameBtns;
	public JTextField textField;
	private ServerEventHandeler thing;
	private Server server;
	public int turn = 0;
	private int player;
	private JLabel Playing;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) throws UnknownHostException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
					frame.thing = new ServerEventHandeler(frame);
					Thread worker = new Thread(frame.thing, "ThingThread");
					worker.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws UnknownHostException
	 */
	public Client() throws UnknownHostException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 450, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// CLIENT SCREEN

		JPanel ClientScreen = new JPanel();
		ClientScreen.setVisible(false);

		// SERVER SCREEN

		JPanel ServerScreen = new JPanel();
		ServerScreen.setVisible(false);

		// GAME SCREEN

		JPanel GameScreen = new JPanel();
		GameScreen.setVisible(false);
		GameScreen.setName("GameScreen");
		GameScreen.setBounds(0, 0, 444, 421);
		contentPane.add(GameScreen);

		JButton btn0 = new JButton("");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(0);
			}
		});
		GameScreen.setLayout(new FormLayout(
				new ColumnSpec[] { ColumnSpec.decode("148px"), ColumnSpec.decode("148px"),
						ColumnSpec.decode("148px"), },
				new RowSpec[] { RowSpec.decode("30px"), RowSpec.decode("130px"), RowSpec.decode("130px"),
						RowSpec.decode("131px"), }));

		JLabel Playing = new JLabel("");
		Playing.setHorizontalAlignment(SwingConstants.CENTER);
		GameScreen.add(Playing, "1, 1, 3, 1");
		btn0.setVerticalAlignment(SwingConstants.TOP);
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn0, "1, 3, fill, fill");

		JButton btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(1);
			}
		});
		btn1.setVerticalAlignment(SwingConstants.TOP);
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn1, "2, 3, fill, fill");

		JButton btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(2);
			}
		});
		btn2.setVerticalAlignment(SwingConstants.TOP);
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn2, "3, 3, fill, fill");

		JButton btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(3);
			}
		});
		btn3.setVerticalAlignment(SwingConstants.TOP);
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn3, "1, 2, fill, fill");

		JButton btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(4);
			}
		});
		btn4.setVerticalAlignment(SwingConstants.TOP);
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		GameScreen.add(btn4, "2, 2, fill, fill");

		JButton btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(5);
			}
		});
		btn5.setVerticalAlignment(SwingConstants.TOP);
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn5, "3, 2, fill, fill");

		JButton btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(6);

			}
		});
		btn6.setVerticalAlignment(SwingConstants.TOP);
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn6, "1, 4, fill, fill");

		JButton btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(7);
			}
		});
		btn7.setVerticalAlignment(SwingConstants.TOP);
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn7, "2, 4, fill, fill");

		JButton btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(8);
			}
		});
		btn8.setVerticalAlignment(SwingConstants.TOP);
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn8, "3, 4, fill, fill");
		ServerScreen.setName("ServerScreen");
		ServerScreen.setLayout(null);
		ServerScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		ServerScreen.setBounds(0, 0, 444, 421);
		contentPane.add(ServerScreen);

		JLabel ServerScreenGuideText = new JLabel("Give this to the other player!");
		ServerScreenGuideText.setHorizontalAlignment(SwingConstants.CENTER);
		ServerScreenGuideText.setName("");
		ServerScreenGuideText.setAlignmentX(Component.CENTER_ALIGNMENT);
		ServerScreenGuideText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		ServerScreenGuideText.setBounds(10, 11, 424, 118);
		ServerScreen.add(ServerScreenGuideText);

		JLabel IPV4Address = new JLabel(InetAddress.getLocalHost().getHostAddress());
		IPV4Address.setName("");
		IPV4Address.setHorizontalAlignment(SwingConstants.CENTER);
		IPV4Address.setFont(new Font("Tahoma", Font.PLAIN, 30));
		IPV4Address.setAlignmentX(0.5f);
		IPV4Address.setBounds(10, 140, 424, 118);
		ServerScreen.add(IPV4Address);

		JButton backToStartServer = new JButton("Or go back to player select");
		backToStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show("StartScreen");
			}
		});
		backToStartServer.setBounds(108, 269, 230, 118);
		ServerScreen.add(backToStartServer);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show("GameScreen");
			}
		});
		btnNewButton.setBounds(165, 122, 89, 23);
		ServerScreen.add(btnNewButton);
		ClientScreen.setName("ClientScreen");
		ClientScreen.setBounds(0, 0, 444, 421);
		contentPane.add(ClientScreen);
		ClientScreen.setLayout(null);
		ClientScreen.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel label = new JLabel("Type in the code from the other player!");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 23));
		label.setAlignmentX(0.5f);
		label.setBounds(10, 11, 424, 118);
		ClientScreen.add(label);

		JButton backToStartClient = new JButton("Or go back to player select");
		backToStartClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				show("StartScreen");
			}
		});
		backToStartClient.setBounds(108, 269, 230, 118);
		ClientScreen.add(backToStartClient);

		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					thing.connectToServer("192.168.1." + textField.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				show("GameScreen");
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField.setBounds(108, 140, 230, 47);
		ClientScreen.add(textField);
		textField.setColumns(10);

		// START SCREEN

		JPanel StartScreen = new JPanel();
		StartScreen.setName("StartScreen");
		StartScreen.setBounds(0, 0, 444, 421);
		contentPane.add(StartScreen);
		StartScreen.setBorder(new EmptyBorder(5, 5, 5, 5));
		StartScreen.setLayout(null);

		JButton XButton = new JButton("X");
		XButton.setBounds(10, 11, 214, 399);
		XButton.setFont(new Font("Tahoma", Font.PLAIN, 99));
		XButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		XButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Server();
				Thread serverThread = new Thread(server, "ServerThread");
				serverThread.start();
				show("ServerScreen");
				player = 0;
				try {
					thing.connectToServer(InetAddress.getLocalHost().getHostAddress());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		StartScreen.add(XButton);

		JButton OButton = new JButton("O");
		OButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				show("ClientScreen");
				player = 1;
			}
		});
		OButton.setBounds(220, 11, 214, 399);
		OButton.setFont(new Font("Tahoma", Font.PLAIN, 99));
		OButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartScreen.add(OButton);

		// LOST SCREEN

		JPanel YouLostScreen = new JPanel();
		YouLostScreen.setVisible(false);
		YouLostScreen.setName("YouLostScreen");
		YouLostScreen.setBounds(0, 0, 444, 421);
		contentPane.add(YouLostScreen);
		YouLostScreen.setLayout(null);

		JLabel LostLabel = new JLabel("You lost!");
		LostLabel.setForeground(Color.RED);
		LostLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		LostLabel.setBounds(144, 85, 156, 83);
		YouLostScreen.add(LostLabel);

		JButton lostTryAgain = new JButton("Try again");
		lostTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show("GameScreen");
				for (JButton element : gameBtns) {
					element.setText("");
					turn = 0;
				}
			}
		});
		lostTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lostTryAgain.setBounds(140, 244, 164, 83);
		YouLostScreen.add(lostTryAgain);

		// WON SCREEN

		JPanel YouWonScreen = new JPanel();
		YouWonScreen.setVisible(false);
		YouWonScreen.setName("YouWonScreen");
		YouWonScreen.setBounds(0, 0, 444, 421);
		contentPane.add(YouWonScreen);
		YouWonScreen.setLayout(null);

		JLabel WonLabel = new JLabel("You won!");
		WonLabel.setForeground(Color.GREEN);
		WonLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		WonLabel.setBounds(138, 85, 168, 83);
		YouWonScreen.add(WonLabel);

		JButton wonTryAgain = new JButton("Try again");
		wonTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show("GameScreen");
				for (JButton element : gameBtns) {
					element.setText("");
					turn = 0;
				}
			}
		});
		wonTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 25));
		wonTryAgain.setBounds(140, 244, 164, 83);
		YouWonScreen.add(wonTryAgain);

		// TIE SCREEN

		JPanel TieScreen = new JPanel();
		TieScreen.setName("TieScreen");
		TieScreen.setBounds(0, 0, 444, 421);
		contentPane.add(TieScreen);
		TieScreen.setLayout(null);

		JLabel TieLabel = new JLabel("It's a tie!");
		TieLabel.setForeground(Color.BLUE);
		TieLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		TieLabel.setBounds(146, 85, 158, 59);
		TieScreen.add(TieLabel);

		JButton tieTryAgain = new JButton("Try again");
		tieTryAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				show("GameScreen");
				for (JButton element : gameBtns) {
					element.setText("");
					turn = 0;
				}
			}
		});
		tieTryAgain.setBounds(140, 244, 164, 83);
		tieTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 25));
		TieScreen.add(tieTryAgain);

		gameBtns = new JButton[] { btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8 };

		for (Component component : contentPane.getComponents()) {
			if (component instanceof JPanel) {
				menuScreens.add((JPanel) component);
			}
		}
	}

	public void show(String screen) {
		for (JPanel scr : menuScreens) {
			if (screen == scr.getName()) {
				scr.setVisible(true);
			} else {
				scr.setVisible(false);
			}
		}
	}

	public void buttonPressed(int btnum) {
		if (gameBtns[btnum].getText() == "" && turn % 2 == player) {
			thing.clientEvents(btnum);
		}
	}

	public void serverEvents(int action) {
		if (action >= 0 && action <= 8) {
			gameBtns[action].setText(turn % 2 == 0 ? "X" : "O");
		}
		switch (action) {
		case 9:
			show("YouWonScreen");
			break;
		case 10:
			show("YouLostScreen");
			break;
		case 11:
			show("TieScreen");
			break;
		case 12:
			show("GameScreen");
			break;
		case 13:
			break;
		case 14:
			player = 0;
			break;
		case 15:
			player = 1;
			break;
		default:
			turn++;
		}
		Playing.setText(Integer(player).toString());
	}

	private Object Integer(int player2) {
		// TODO Auto-generated method stub
		return null;
	}
}