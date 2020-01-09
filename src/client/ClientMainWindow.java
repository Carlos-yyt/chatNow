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

import server.ServerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientMainWindow implements ServerListener{

	ChatClient chatClient;
	
	JFrame frmChatnow;
	private JTextField inputTF;
	
	JTextPane showTP;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMainWindow window = new ClientMainWindow(null);
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
	public ClientMainWindow(ChatClient _chatClient) throws UnknownHostException, IOException {
		if (_chatClient==null) {
			//TODO 单独调试ClientMainWindow时使用，参数要手动改代码
		}else {
			this.chatClient=_chatClient;
			this.chatClient.setOutPutUI(this);//UI类监听chatClient
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	private void initialize() throws UnknownHostException, IOException {
		
		frmChatnow = new JFrame();
		frmChatnow.setTitle("chatNow");
		frmChatnow.setBounds(100, 100, 543, 628);
		frmChatnow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChatnow.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("\u901A\u8BAF\u4FE1\u606F");
		panel.setBounds(366, 10, 151, 569);
		frmChatnow.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("\u4F60\u7684\u540D\u79F0");
		lblNewLabel_2.setBounds(5, 5, 54, 15);
		panel.add(lblNewLabel_2);
		
		JLabel nameLab = new JLabel("-\u540D\u79F0-");
		nameLab.setBounds(5, 25, 136, 15);
		panel.add(nameLab);
		nameLab.setText(chatClient.getUser().getName());
		
		JLabel lblNewLabel_4 = new JLabel("\u4F60\u7684\u8D26\u53F7");
		lblNewLabel_4.setBounds(5, 45, 54, 15);
		panel.add(lblNewLabel_4);
		
		JLabel countLab = new JLabel("-\u8D26\u53F7-");
		countLab.setBounds(5, 65, 136, 15);
		panel.add(countLab);
		countLab.setText(chatClient.getUser().getId());
		
		JLabel lblid_1 = new JLabel("\u4F60\u7684IP");
		lblid_1.setBounds(8, 85, 48, 15);
		panel.add(lblid_1);
		
		JLabel ipLab = new JLabel("-ip-");
		ipLab.setBounds(8, 105, 133, 15);
		panel.add(ipLab);
		ipLab.setText(chatClient.getHostStr());
		
		JLabel lblNewLabel = new JLabel("\u4F60\u7684\u7AEF\u53E3");
		lblNewLabel.setBounds(5, 125, 105, 15);
		panel.add(lblNewLabel);
		
		JLabel portLab = new JLabel("-\u7AEF\u53E3-");
		portLab.setBounds(5, 150, 136, 15);
		panel.add(portLab);
		portLab.setText(Integer.toString(chatClient.getPort()));
		
		JLabel lblip = new JLabel("\u670D\u52A1\u5668\u7684IP");
		lblip.setBounds(5, 175, 105, 15);
		panel.add(lblip);
		
		JLabel serverIPLab = new JLabel("-IP-");
		serverIPLab.setBounds(5, 200, 136, 15);
		panel.add(serverIPLab);
		serverIPLab.setText(chatClient.socket.getRemoteSocketAddress().toString());
		
		JLabel lblNewLabel_5 = new JLabel("\u670D\u52A1\u5668\u7684\u7AEF\u53E3");
		lblNewLabel_5.setBounds(5, 225, 105, 15);
		panel.add(lblNewLabel_5);
		
		JLabel serverPortLab = new JLabel("-\u670D\u52A1\u5668\u7684\u7AEF\u53E3-");
		serverPortLab.setBounds(5, 250, 136, 15);
		panel.add(serverPortLab);
		serverPortLab.setText(Integer.toString(chatClient.socket.getPort()));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 346, 569);
		frmChatnow.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		showTP = new JTextPane();
		showTP.setEditable(false);
		showTP.setText("\u3010CHATNOW\u3011");
		showTP.setToolTipText("\u804A\u5929\u7A97\u53E3");
		showTP.setBounds(10, 10, 326, 457);
		panel_1.add(showTP);
		
		inputTF = new JTextField();
		inputTF.setHorizontalAlignment(SwingConstants.LEFT);
		inputTF.setText("\u5927\u5BB6\u597D~");
		inputTF.setToolTipText("\u53D1\u9001\u6846");
		inputTF.setBounds(10, 477, 229, 82);
		panel_1.add(inputTF);
		inputTF.setColumns(10);
		
		JButton btnNewButton = new JButton("\u53D1\u9001");
		btnNewButton.addMouseListener(new MouseAdapter() {
			//发送消息
			@Override
			public void mouseClicked(MouseEvent e) {
				chatClient.sentMsg(inputTF.getText());
				appendMsg(inputTF.getText());
				inputTF.setText("");
			}
		});
		btnNewButton.setBounds(249, 477, 87, 82);
		panel_1.add(btnNewButton);
	}

	public void appendMsg(String msg) {
		showTP.setText(showTP.getText()+"\r\n"+msg);//向聊天公屏追加msg
	}
	
	@Override
	public void getGroupMsg(String msg) {
		appendMsg(msg);
	}
}
