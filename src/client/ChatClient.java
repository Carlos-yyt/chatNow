package client;

/*
 * �ͻ���ҵ�����
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import chatNow.User;
import server.ServerListener;

public class ChatClient  extends Thread{
	private String hostStr;//������ַ
	private int port;//����˿�
	
	Socket socket;//�����socket
	BufferedReader in;//������
	PrintStream out;//���
	
	private ServerListener outPutUI;//���
	
	private User user=null;//�û���Ϣ
	
	private boolean isLogin=false;
	private boolean haveUI=false;
	
	public ChatClient(Socket _socket) throws IOException {
		socket=_socket;
		setHostStr(socket.getInetAddress().getHostAddress());
		setPort(socket.getLocalPort());
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
		out=new PrintStream(socket.getOutputStream());//output
	}
	
	public void sentMsg(String msg) {
		out.println(msg);
	}
	
	//��¼
	public void login() {
		String msg = null;
		
		System.out.println("Try to login...");
		out.println("login");//Э�飬������������¼
		
		//�˴���ͨѶ˳����Լ���õģ�id->name->password
		out.println(user.getId());
		out.println(user.getName());
		out.println(user.getPassword());
		
		//ѭ���ȴ���¼�ɹ�����Ϣ
		while (true) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				//System.out.println("Debug[msg]:"+msg);
				if(msg.equals("login successfully")){//������msg.equals("login successfully")��ԭ��https://jingyan.baidu.com/article/1876c852f85d5ec90b1376ba.html
					break;
				}
			}
		}
		setLogin(true);
		System.out.println("[Local msg]:Login successfully.");
		
	}
	
	public void run() {
		String msg = null;
		while (isLogin()==false){			
			System.out.println("debug004-chatClient-isLogin no");
		};
		System.out.println("debug003-chatClient-isLogin yes");
		//���ϳ��Զ�ȡ
		while (true) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				System.out.println(msg);
				if(isHaveUI()==true){
					//TODO ���߳����ˢ��ui��
					outPutUI.getGroupMsg(msg);					
				}
				
				msg=null;
			}
		}
	}

	/**
	 * @return user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user Ҫ���õ� user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port Ҫ���õ� port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return hostStr
	 */
	public String getHostStr() {
		return hostStr;
	}

	/**
	 * @param hostStr Ҫ���õ� hostStr
	 */
	public void setHostStr(String hostStr) {
		this.hostStr = hostStr;
	}

	/**
	 * @return isLogin
	 */
	public boolean isLogin() {
		return isLogin;
	}

	/**
	 * @param isLogin Ҫ���õ� isLogin
	 */
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}

	/**
	 * @return outPutUI
	 */
	public ServerListener getOutPutUI() {
		return outPutUI;
	}

	/**
	 * @param outPutUI Ҫ���õ� outPutUI
	 */
	public void setOutPutUI(ServerListener outPutUI) {
		setHaveUI(true);
		this.outPutUI = outPutUI;
	}

	/**
	 * @return haveUI
	 */
	public boolean isHaveUI() {
		return haveUI;
	}

	/**
	 * @param haveUI Ҫ���õ� haveUI
	 */
	public void setHaveUI(boolean haveUI) {
		this.haveUI = haveUI;
	}
}
