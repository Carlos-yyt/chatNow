package client;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;

public class ClientMainWindow {

	ChatClient chatClient;
	
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
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientMainWindow() throws UnknownHostException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		//TODO 这里以后应该由登录界面传输参数过来
		Socket socket=new Socket("192.168.0.105",5000);
		chatClient=new ChatClient(socket);
		chatClient.start();
		
		frmChatnow = new JFrame();
		frmChatnow.setTitle("chatNow");
		frmChatnow.setBounds(100, 100, 524, 628);
		frmChatnow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatnow.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u901A\u8BAF\u4FE1\u606F");
		panel.setBounds(366, 10, 120, 569);
		frmChatnow.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u4F60\u7684\u540D\u79F0");
		lblNewLabel_2.setBounds(5, 5, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("-\u540D\u79F0-");
		lblNewLabel_1.setBounds(5, 25, 105, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("\u4F60\u7684\u8D26\u53F7");
		lblNewLabel_4.setBounds(5, 45, 54, 15);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("-\u8D26\u53F7-");
		lblNewLabel_3.setBounds(5, 65, 105, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblid_1 = new JLabel("\u4F60\u7684IP");
		lblid_1.setBounds(8, 85, 48, 15);
		panel.add(lblid_1);
		
		JLabel lblid = new JLabel("-ip-");
		lblid.setBounds(8, 105, 102, 15);
		panel.add(lblid);
		
		JLabel lblNewLabel = new JLabel("\u4F60\u7684\u7AEF\u53E3");
		lblNewLabel.setBounds(5, 125, 105, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNull = new JLabel("-\u7AEF\u53E3-");
		lblNull.setBounds(5, 150, 105, 15);
		panel.add(lblNull);
		
		JLabel lblip = new JLabel("\u670D\u52A1\u5668\u7684IP");
		lblip.setBounds(5, 175, 105, 15);
		panel.add(lblip);
		
		JLabel lblip_1 = new JLabel("-IP-");
		lblip_1.setBounds(5, 200, 105, 15);
		panel.add(lblip_1);
		
		JLabel lblNewLabel_5 = new JLabel("\u670D\u52A1\u5668\u7684\u7AEF\u53E3");
		lblNewLabel_5.setBounds(5, 225, 105, 15);
		panel.add(lblNewLabel_5);
		
		JLabel label = new JLabel("-\u670D\u52A1\u5668\u7684\u7AEF\u53E3-");
		label.setBounds(5, 250, 105, 15);
		panel.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 346, 569);
		frmChatnow.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextPane txtpnchatnow = new JTextPane();
		txtpnchatnow.setEditable(false);
		txtpnchatnow.setText("\u3010CHATNOW\u3011");
		txtpnchatnow.setToolTipText("\u804A\u5929\u7A97\u53E3");
		txtpnchatnow.setBounds(10, 10, 326, 457);
		panel_1.add(txtpnchatnow);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setText("\u5927\u5BB6\u597D~");
		textField.setToolTipText("\u53D1\u9001\u6846");
		textField.setBounds(10, 477, 229, 82);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.setBounds(249, 477, 87, 82);
		panel_1.add(btnNewButton);
	}
}
