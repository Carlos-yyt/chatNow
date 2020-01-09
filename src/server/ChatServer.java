package server;

/*
 * ���������߳�
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.GOTO;

import chatNow.User;

/*
 * TODO ��Ϣ�����������/�ж����ͣ�
 * ����������̣߳���ô���߳̾ͱض��е��˳���һ�����������Server Threadֻ��������Ϣ����������������2019��12��23�� ������
 * �������ServerThread���߳�֮�����ͨѶ����������2019��12��23�� ͨ���Զ���������
 * */

public class ChatServer extends Thread implements ServerListener {
	Vector<ServerThread> serverList=new Vector<>();//��������б�vector��Ȼ�������̰߳�ȫ https://blog.csdn.net/weixin_41596018/article/details/82892900
	private int userNumCeiling;//�û���������
	
	private String hostStr;//������ַ
	private int port;//����˿�
	
	ServerSocket serverSocket;//����socket
	
	BufferedReader in;//������
	PrintStream out;//���
	
	public ChatServer(String _hostStr,int _port) throws IOException {
		setHostStr(_hostStr);
		setPort(_port);
		serverSocket=new ServerSocket(getPort());
		//TODO �˴�ȱ�����Լ�����serverList�Ĵ��룬���·����������ܼ���
	}
	public ChatServer() throws IOException {
		
	}
	
	public void run() {
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ѭ�������µ�����
		while (true) {
			System.out.println("Waiting for new apply...");
			
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}//�÷�����һֱ�ȴ���ֱ���ͻ������ӵ��������ϸ����Ķ˿ڡ�
			
			System.out.print("Find new apply:");
			System.out.print("IP:"+socket.getRemoteSocketAddress().toString());
			System.out.println("\tport:"+socket.getPort());
			
			//����һ���û���Ϣ��ʣ�µľͽ���ServerThread������
			try {
				in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}//input
			try {
				out=new PrintStream(socket.getOutputStream());
			} catch (IOException e1) {
				e1.printStackTrace();
			}//output
			   
			//�˴���ͨѶ˳����Լ���õģ�id->name->password
			String userStatus = null;
			try {
				userStatus = in.readLine();
				System.out.println("User wants to "+userStatus);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//ѭ���ȴ��û�����״̬��Ϣ
			while (true) {
				if (userStatus.equals("login")) {
					break;
					//TODO ��¼
				}else if (userStatus.equals("signup")) {
					//TODO ע��
				}				
			}
			
			String _id = null;
			try {
				_id = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String _name = null;
			try {
				_name = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			String _password = null;
			try {
				_password = in.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//TODO �����û�������Ϣ
			User user=new User(socket.getPort(), socket.getRemoteSocketAddress().toString(), _id, _name, _password);
			
			System.out.println("New user's info��");
			System.out.println("\tNew user's id:\t"+user.getId());
			System.out.println("\tNew user's name:\t"+user.getName());
			System.out.println("\tNew user's password:\t"+user.getPassword());
			
			ServerThread serverThread = null;
			try {
				serverThread = new ServerThread(socket,user);
			} catch (IOException e) {
				e.printStackTrace();
			}
			serverThread.start();
			
			//�¾ɽ��̻������
			for (ServerThread th : serverList) {
				th.addListener(serverThread);
				serverThread.addListener(th);
			}
			serverList.add(serverThread);//�� ���߳� �����������б�
			
			
			
			//TODO �˴�ȱ����thread�Ĺ���
		}
	}
	
	/**
	 * @return userNumCeiling
	 */
	public int getUserNumCeiling() {
		return userNumCeiling;
	}
	/**
	 * @param userNumCeiling Ҫ���õ� userNumCeiling
	 */
	public void setUserNumCeiling(int userNumCeiling) {
		this.userNumCeiling = userNumCeiling;
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
	
	@Override
	//���������������˵ĶԻ�
	public void getGroupMsg(String msg) {
		System.out.println(msg);
	}
}
