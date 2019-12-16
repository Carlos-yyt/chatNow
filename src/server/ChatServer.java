package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
/*
 * 服务器主线程
 * */

/*
 * TODO 消息放在哪里接收/判断类型？
 * 如果放在主线程，那么主线程就必定承担了超过一半的任务量，Server Thread只负责发送消息。不合理。
 * 如果放在ServerThread，线程之间如何通讯？
 * */

public class ChatServer extends Thread {
	Map<String, User> userMap;//储存所有注册成功的用户的个人信息
	private int userNumCeiling;//用户人数上限
	
	private String hostStr;//本机地址
	private int port;//服务端口
	
	ServerSocket serverSocket;//服务socket
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		while (true) {
			System.out.println("Waiting for new apply...");
			
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}//该方法将一直等待，直到客户端连接到服务器上给定的端口。
			
			System.out.print("Find new apply:");
			System.out.print("IP:"+socket.getRemoteSocketAddress().toString());
			System.out.println("\tport:"+socket.getPort());
			
			ServerThread serverThread = null;
			try {
				serverThread = new ServerThread(socket);
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			serverThread.start();
			//TODO 此处缺乏对thread的管理
		}
	}
	
	/**
	 * @return userNumCeiling
	 */
	public int getUserNumCeiling() {
		return userNumCeiling;
	}
	/**
	 * @param userNumCeiling 要设置的 userNumCeiling
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
	 * @param hostStr 要设置的 hostStr
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
	 * @param port 要设置的 port
	 */
	public void setPort(int port) {
		this.port = port;
	}
}
