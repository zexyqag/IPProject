package TicTacToe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
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

public class GameController extends JFrame {

	private JPanel contentPane;
	public ArrayList<JPanel> menuScreens = new ArrayList<>();
	private JTextField textField;

	/**
	 * Launch the application.
	 * 
	 */
	public static void main(String[] args) throws UnknownHostException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameController frame = new GameController();
					frame.setVisible(true);
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
	public GameController() throws UnknownHostException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 450, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// GAME SCREEN

		JPanel GameScreen = new JPanel();
		GameScreen.setName("GameScreen");
		GameScreen.setVisible(false);
		GameScreen.setBounds(0, 0, 444, 421);
		contentPane.add(GameScreen);
		GameScreen.setLayout(new GridLayout(0, 3, 0, 0));

		JButton btn0 = new JButton("");
		btn0.setVerticalAlignment(SwingConstants.TOP);
		btn0.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn0);

		JButton btn1 = new JButton("");
		btn1.setVerticalAlignment(SwingConstants.TOP);
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn1);

		JButton btn2 = new JButton("");
		btn2.setVerticalAlignment(SwingConstants.TOP);
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn2);

		JButton btn3 = new JButton("");
		btn3.setVerticalAlignment(SwingConstants.TOP);
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn3);

		JButton btn4 = new JButton("");
		btn4.setVerticalAlignment(SwingConstants.TOP);
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 99));
		btn4.setHorizontalTextPosition(SwingConstants.CENTER);
		GameScreen.add(btn4);

		JButton btn5 = new JButton("");
		btn5.setVerticalAlignment(SwingConstants.TOP);
		btn5.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn5);

		JButton btn6 = new JButton("");
		btn6.setVerticalAlignment(SwingConstants.TOP);
		btn6.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn6);

		JButton btn7 = new JButton("");
		btn7.setVerticalAlignment(SwingConstants.TOP);
		btn7.setFont(new Font("Tahoma", Font.PLAIN, 99));
		GameScreen.add(btn7);

		JButton btn8 = new JButton("");
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
				show("ServerScreen");
			}
		});
		StartScreen.add(XButton);

		JButton OButton = new JButton("O");
		OButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		OButton.setBounds(220, 11, 214, 399);
		OButton.setFont(new Font("Tahoma", Font.PLAIN, 99));
		OButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		StartScreen.add(OButton);

		// SERVER SCREEN

		JPanel ServerScreen = new JPanel();
		ServerScreen.setName("ServerScreen");
		ServerScreen.setVisible(false);
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

		// CLIENT SCREEN

		JPanel ClientScreen = new JPanel();
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
				ClientScreen.setVisible(false);
				StartScreen.setVisible(true);
			}
		});
		backToStartClient.setBounds(108, 269, 230, 118);
		ClientScreen.add(backToStartClient);

		textField = new JTextField();
		textField.setBounds(108, 140, 230, 47);
		ClientScreen.add(textField);
		textField.setColumns(10);

		// LOST SCREEN

		JPanel YouLostScreen = new JPanel();
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
		lostTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lostTryAgain.setBounds(140, 244, 164, 83);
		YouLostScreen.add(lostTryAgain);

		// WON SCREEN

		JPanel YouWonScreen = new JPanel();
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
			}
		});
		wonTryAgain.setFont(new Font("Tahoma", Font.PLAIN, 25));
		wonTryAgain.setBounds(140, 244, 164, 83);
		YouWonScreen.add(wonTryAgain);

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
}
