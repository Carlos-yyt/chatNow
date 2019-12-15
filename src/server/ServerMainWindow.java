package server;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class ServerMainWindow {

	private JFrame frame;
	private JTextPane textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerMainWindow window = new ServerMainWindow();
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
	public ServerMainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u670D\u52A1\u7AEF");
		frame.setBounds(100, 100, 912, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setToolTipText("\u8FDE\u63A5\u8BBE\u7F6E");
		panel.setBounds(10, 10, 294, 113);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u672C\u673A\u5730\u5740");
		label.setBounds(10, 10, 77, 15);
		panel.add(label);
		
		textField = new JTextPane();
		textField.setBounds(97, 10, 187, 21);
		panel.add(textField);
		
		JLabel label_1 = new JLabel("\u670D\u52A1\u7AEF\u53E3");
		label_1.setBounds(10, 35, 77, 15);
		panel.add(label_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(97, 35, 187, 21);
		panel.add(textPane);
		
		JButton btnNewButton = new JButton("\u83B7\u53D6");
		btnNewButton.setBounds(10, 60, 274, 43);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setToolTipText("\u7528\u6237\u8BBE\u7F6E");
		panel_1.setBounds(10, 133, 294, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("\u6700\u5927\u7528\u6237\u6570\u91CF");
		label_2.setBounds(10, 10, 89, 15);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 7, 193, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u5217\u8868");
		label_3.setBounds(10, 35, 68, 15);
		panel_1.add(label_3);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(91, 35, 193, 378);
		panel_1.add(textPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setToolTipText("\u5BF9\u8BDD\u76D1\u542C");
		panel_2.setBounds(314, 10, 572, 546);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(10, 31, 552, 505);
		panel_2.add(textPane_2);
		
		JLabel label_4 = new JLabel("\u5BF9\u8BDD\u76D1\u542C");
		label_4.setBounds(255, 10, 54, 15);
		panel_2.add(label_4);
	}
}
