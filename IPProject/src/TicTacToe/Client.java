package TicTacToe;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class Client extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<JPanel> menuScreens = new ArrayList<>();
	private JButton[] gameBtns;
	private JTextField textField;
	private ServerEventHandeler SEH;
	private Server server;
	private int turn = 0;
	private int player;

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
					frame.SEH = new ServerEventHandeler(frame);
					Thread worker = new Thread(frame.SEH, "ThingThread");
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
		setLocationRelativeTo(null);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (server != null) {
					server.theEnd();
					server = null;

				}
				SEH.DisconnectFromServer();
				System.out.println("bye!");
			}
		});

		// GAME SCREEN

		JPanel GameScreen = new JPanel();
		GameScreen.setVisible(false);

		// SERVER SCREEN

		JPanel ServerScreen = new JPanel();
		ServerScreen.setVisible(false);

		// CLIENT SCREEN

		JPanel ClientScreen = new JPanel();
		ClientScreen.setVisible(false);
		ClientScreen.setName("ClientScreen");
		ClientScreen.setBounds(0, 0, 444, 421);
		contentPane.add(ClientScreen);
		ClientScreen.setLayout(null);
		ClientScreen.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblTypeInThe = new JLabel("Type in the code from the other player");
		lblTypeInThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeInThe.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTypeInThe.setAlignmentX(0.5f);
		lblTypeInThe.setBounds(10, 23, 424, 86);
		ClientScreen.add(lblTypeInThe);

		JButton backToStartClient = new JButton("Or go back to player select");
		backToStartClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				show("StartScreen");
			}
		});

		JLabel pressEnter = new JLabel("and press enter!");
		pressEnter.setFont(new Font("Tahoma", Font.PLAIN, 23));
		pressEnter.setHorizontalAlignment(SwingConstants.CENTER);
		pressEnter.setBounds(124, 86, 187, 38);
		ClientScreen.add(pressEnter);
		backToStartClient.setBounds(108, 269, 230, 118);
		
		ClientScreen.add(backToStartClient);
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SEH.connectToServer(textField.getText());
					show("GameScreen");
				} catch (IOException e1) {
					textField.setText("Wrong code");
				}
				
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 23));
		textField.setBounds(108, 173, 230, 47);
		ClientScreen.add(textField);
		textField.setColumns(10);
		
		
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
				server.theEnd();
				server = null;
				show("StartScreen");
			}
		});
		backToStartServer.setBounds(108, 269, 230, 118);
		ServerScreen.add(backToStartServer);
		GameScreen.setName("GameScreen");
		GameScreen.setBounds(0, 0, 444, 421);
		contentPane.add(GameScreen);
		GameScreen.setLayout(new GridLayout(0, 3, 0, 0));

		JButton btn0 = new JButton("");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(0);
			}
		});
		btn0.setVerticalAlignment(SwingConstants.TOP);
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn0);

		JButton btn1 = new JButton("");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(1);
			}
		});
		btn1.setVerticalAlignment(SwingConstants.TOP);
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn1);

		JButton btn2 = new JButton("");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(2);
			}
		});
		btn2.setVerticalAlignment(SwingConstants.TOP);
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn2);

		JButton btn3 = new JButton("");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(3);
			}
		});
		btn3.setVerticalAlignment(SwingConstants.TOP);
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn3);

		JButton btn4 = new JButton("");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(4);
			}
		});
		btn4.setVerticalAlignment(SwingConstants.TOP);
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		GameScreen.add(btn4);

		JButton btn5 = new JButton("");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(5);
			}
		});
		btn5.setVerticalAlignment(SwingConstants.TOP);
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn5);

		JButton btn6 = new JButton("");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(6);

			}
		});
		btn6.setVerticalAlignment(SwingConstants.TOP);
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn6);

		JButton btn7 = new JButton("");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(7);
			}
		});
		btn7.setVerticalAlignment(SwingConstants.TOP);
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn7);

		JButton btn8 = new JButton("");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(8);
			}
		});
		btn8.setVerticalAlignment(SwingConstants.TOP);
		btn8.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn8);

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
					SEH.connectToServer(InetAddress.getLocalHost().getHostAddress());
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
			SEH.clientEvents(btnum);
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
		default:
			turn++;
		}

	}
}