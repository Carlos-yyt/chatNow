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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerMainWindow implements ServerListener{

	ChatServer chatServer;//主服务线程
	
	
	private JFrame frame;
	private JTextPane IPTextField;
	private JTextField textField_1;
	private JTextField portTextField;

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
		//创建ChatServer，但是没有参数
		try {
			chatServer=new ChatServer();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		frame = new JFrame();
		frame.setTitle("\u670D\u52A1\u7AEF");
		frame.setBounds(100, 100, 912, 605);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setToolTipText("\u8FDE\u63A5\u8BBE\u7F6E");
		panel.setBounds(10, 10, 367, 113);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u672C\u673A\u5730\u5740");
		label.setBounds(10, 10, 77, 15);
		panel.add(label);
		
		IPTextField = new JTextPane();
		IPTextField.setEditable(false);
		IPTextField.setBounds(97, 4, 260, 21);
		panel.add(IPTextField);
		
		JLabel label_1 = new JLabel("\u670D\u52A1\u7AEF\u53E3");
		label_1.setBounds(10, 35, 77, 15);
		panel.add(label_1);
		
		JButton getInterInfoBtn = new JButton("\u83B7\u53D6\u5730\u5740");
		getInterInfoBtn.setBounds(10, 60, 107, 43);
		getInterInfoBtn.addMouseListener(new MouseAdapter() {
			@Override
			//获得本机IP和程序port
			public void mouseClicked(MouseEvent e) {			
				//IP获得与显示
				try {
					chatServer.setHostStr(InetAddress.getLocalHost().getHostAddress());
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				IPTextField.setText(chatServer.getHostStr());
				System.out.println("Server IP:"+chatServer.getHostStr());
			}
		});
		panel.add(getInterInfoBtn);
		
		JButton setPortBtn = new JButton("\u8BBE\u7F6E\u7AEF\u53E3");
		setPortBtn.setBounds(127, 60, 110, 43);
		setPortBtn.addMouseListener(new MouseAdapter() {
			@Override
			//设置端口
			public void mouseClicked(MouseEvent e) {
				chatServer.setPort(Integer.valueOf(portTextField.getText()));
				System.out.println("Server port:"+chatServer.getPort());
			}
		});
		panel.add(setPortBtn);
		
		portTextField = new JTextField();
		portTextField.setText("5000");
		portTextField.setBounds(97, 32, 260, 21);
		panel.add(portTextField);
		portTextField.setColumns(10);
		
		JButton startServerBtn = new JButton("\u5F00\u59CB\u670D\u52A1");
		startServerBtn.addMouseListener(new MouseAdapter() {
			@Override
			//启动服务器
			public void mouseClicked(MouseEvent e) {
				chatServer.start();
				//System.out.println("Debug info:chatServer.run();的下一行");
			}
		});
		startServerBtn.setBounds(247, 60, 110, 43);
		panel.add(startServerBtn);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setToolTipText("\u7528\u6237\u8BBE\u7F6E");
		panel_1.setBounds(10, 133, 367, 423);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_2 = new JLabel("\u6700\u5927\u7528\u6237\u6570\u91CF");
		label_2.setBounds(10, 10, 89, 15);
		panel_1.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(91, 7, 94, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u5217\u8868");
		label_3.setBounds(10, 35, 68, 15);
		panel_1.add(label_3);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		textPane_1.setBounds(91, 35, 266, 378);
		panel_1.add(textPane_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setToolTipText("\u5BF9\u8BDD\u76D1\u542C");
		panel_2.setBounds(387, 10, 499, 546);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setEditable(false);
		textPane_2.setBounds(10, 31, 479, 505);
		panel_2.add(textPane_2);
		
		JLabel label_4 = new JLabel("\u5BF9\u8BDD\u76D1\u542C");
		label_4.setBounds(255, 10, 54, 15);
		panel_2.add(label_4);
	}

	@Override
	public void getGroupMsg(String msg) {
		// TODO 服务器也要监听，更新ui
	}
}
