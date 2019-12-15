package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class ClientLoginWindow {

	private JFrame frame;
	private JTextField IPTextField;
	private JTextField portTextField;
	private JTextField textField_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLoginWindow window = new ClientLoginWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientLoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u7528\u6237\u767B\u5F55");
		frame.setBounds(100, 100, 480, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u8FDE\u63A5\u8BBE\u7F6E");
		panel.setBounds(10, 10, 421, 157);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u670D\u52A1\u5668\u7684IP\u5730\u5740");
		lblNewLabel.setBounds(10, 10, 227, 15);
		panel.add(lblNewLabel);
		
		IPTextField = new JTextField();
		IPTextField.setBounds(10, 35, 227, 21);
		panel.add(IPTextField);
		IPTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u670D\u52A1\u5668\u7684\u7AEF\u53E3\u53F7");
		lblNewLabel_1.setBounds(10, 66, 227, 15);
		panel.add(lblNewLabel_1);
		
		portTextField = new JTextField();
		portTextField.setBounds(10, 91, 227, 21);
		panel.add(portTextField);
		portTextField.setColumns(10);
		
		JButton button = new JButton("\u4FDD\u5B58");
		button.setBounds(10, 122, 227, 23);
		panel.add(button);
		
		JTextPane textPane = new JTextPane();
		textPane.setText("= = =\u8FDE\u63A5\u4FE1\u606F= = =");
		textPane.setBounds(247, 10, 164, 135);
		panel.add(textPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(20, 177, 411, 222);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u7528\u6237\u540D");
		lblNewLabel_2.setBounds(10, 10, 190, 15);
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setText("\u5F20\u4E09");
		textField_2.setBounds(10, 35, 190, 21);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label.setBounds(10, 66, 190, 15);
		panel_1.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 91, 190, 21);
		panel_1.add(passwordField);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("= = =\u8D26\u53F7\u4FE1\u606F= = =");
		textPane_1.setBounds(210, 10, 191, 202);
		panel_1.add(textPane_1);
		
		JButton btnNewButton = new JButton("\u6CE8\u518C");
		btnNewButton.setBounds(10, 122, 87, 90);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u767B\u5F55");
		btnNewButton_1.setBounds(107, 122, 93, 90);
		panel_1.add(btnNewButton_1);
	}
}
