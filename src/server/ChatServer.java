package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
/*
 * ���������߳�
 * */

/*
 * TODO ��Ϣ�����������/�ж����ͣ�
 * ����������̣߳���ô���߳̾ͱض��е��˳���һ�����������Server Threadֻ��������Ϣ��������
 * �������ServerThread���߳�֮�����ͨѶ��
 * */

public class ChatServer extends Thread {
	Map<String, User> userMap;//��������ע��ɹ����û��ĸ�����Ϣ
	private int userNumCeiling;//�û���������
	
	private String hostStr;//������ַ
	private int port;//����˿�
	
	ServerSocket serverSocket;//����socket
	public ChatServer(String _hostStr,int _port) throws IOException {
		setHostStr(_hostStr);
		setPort(_port);
		serverSocket=new ServerSocket(getPort());
	}
	public ChatServer() throws IOException {
		
	}
	
	public void run() {
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		while (true) {
			System.out.println("Waiting for new apply...");
			
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}//�÷�����һֱ�ȴ���ֱ���ͻ������ӵ��������ϸ����Ķ˿ڡ�
			
			System.out.print("Find new apply:");
			System.out.print("IP:"+socket.getRemoteSocketAddress().toString());
			System.out.println("\tport:"+socket.getPort());
			
			ServerThread serverThread = null;
			try {
				serverThread = new ServerThread(socket);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			serverThread.start();
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
}
