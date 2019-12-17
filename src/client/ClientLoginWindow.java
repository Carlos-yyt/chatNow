package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.net.ssl.internal.www.protocol.https.Handler;

import jdk.internal.org.objectweb.asm.Handle;
import server.User;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

public class ClientLoginWindow {

	ChatClient chatClient;
	
	private JFrame frame;
	private JTextField IPTextField;
	private JTextField portTextField;
	private JTextField idTextField;
	private JPasswordField passwordField;
	
	//接收子线程的 更新ui 的请求
	final Handler handler=new Handler(){
		public void handleMessage(Message msg) {
			
		}
	};
	
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
		frame.setBounds(100, 100, 793, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u8FDE\u63A5\u8BBE\u7F6E");
		panel.setBounds(10, 10, 757, 157);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8BF7\u8F93\u5165\u670D\u52A1\u5668\u7684IP\u5730\u5740");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setBounds(10, 10, 227, 15);
		panel.add(lblNewLabel);
		
		IPTextField = new JTextField();
		IPTextField.setBounds(10, 35, 227, 21);
		panel.add(IPTextField);
		IPTextField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u670D\u52A1\u5668\u7684\u7AEF\u53E3\u53F7");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setBounds(10, 66, 227, 15);
		panel.add(lblNewLabel_1);
		
		portTextField = new JTextField();
		portTextField.setBounds(10, 91, 227, 21);
		panel.add(portTextField);
		portTextField.setColumns(10);
		
		JButton ssaveInterInfo = new JButton("\u8FDE\u63A5\u670D\u52A1\u5668");
		ssaveInterInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Socket socket=null;
				try {
					socket=new Socket(IPTextField.getText(),Integer.valueOf(portTextField.getText()));
				} catch (NumberFormatException | IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				try {
					chatClient=new ChatClient(socket);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				chatClient.start();
				
			}
		});
		ssaveInterInfo.setBounds(10, 122, 227, 23);
		panel.add(ssaveInterInfo);
		
		JTextPane connectInfoTextPane = new JTextPane();
		connectInfoTextPane.setEditable(false);
		connectInfoTextPane.setText("= = =\u8FDE\u63A5\u4FE1\u606F= = =");
		connectInfoTextPane.setBounds(247, 10, 500, 135);
		panel.add(connectInfoTextPane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 177, 757, 222);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BF7\u8F93\u5165\u8D26\u53F7");
		lblNewLabel_2.setEnabled(false);
		lblNewLabel_2.setBounds(10, 10, 190, 15);
		panel_1.add(lblNewLabel_2);
		
		idTextField = new JTextField();
		idTextField.setBounds(10, 35, 215, 21);
		panel_1.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel label = new JLabel("\u8BF7\u8F93\u5165\u5BC6\u7801");
		label.setEnabled(false);
		label.setBounds(10, 66, 190, 15);
		panel_1.add(label);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 91, 215, 21);
		panel_1.add(passwordField);
		
		JTextPane accountInfoTextPane = new JTextPane();
		accountInfoTextPane.setEditable(false);
		accountInfoTextPane.setText("= = =\u8D26\u53F7\u4FE1\u606F= = =");
		accountInfoTextPane.setBounds(235, 10, 502, 202);
		panel_1.add(accountInfoTextPane);
		
		JButton signUpBtn = new JButton("\u6CE8\u518C");
		signUpBtn.setBounds(10, 122, 99, 90);
		panel_1.add(signUpBtn);
		
		JButton logInBtn = new JButton("\u767B\u5F55");
		logInBtn.setBounds(119, 122, 106, 90);
		panel_1.add(logInBtn);
	}
}
