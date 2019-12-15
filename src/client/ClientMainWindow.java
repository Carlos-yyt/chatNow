package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ClientMainWindow {

	private JFrame frmChatnow;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMainWindow window = new ClientMainWindow();
					window.frmChatnow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatnow = new JFrame();
		frmChatnow.setTitle("chatNow");
		frmChatnow.setBounds(100, 100, 524, 628);
		frmChatnow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatnow.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 35, 338, 380);
		frmChatnow.getContentPane().add(textPane);
		
		JLabel label = new JLabel("\u4F60\u7684\u540D\u79F0");
		label.setBounds(358, 10, 54, 15);
		frmChatnow.getContentPane().add(label);
		
		JLabel lblNull = new JLabel("null");
		lblNull.setBounds(358, 35, 54, 15);
		frmChatnow.getContentPane().add(lblNull);
		
		textField = new JTextField();
		textField.setBounds(10, 425, 338, 154);
		frmChatnow.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.setBounds(358, 425, 140, 154);
		frmChatnow.getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("\u804A\u5929\u754C\u9762");
		label_1.setBounds(10, 10, 338, 15);
		frmChatnow.getContentPane().add(label_1);
	}
}
