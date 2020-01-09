package client;

/*
 * 客户端业务代码
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import chatNow.User;
import server.ServerListener;

public class ChatClient  extends Thread{
	private String hostStr;//本机地址
	private int port;//服务端口
	
	Socket socket;//服务端socket
	BufferedReader in;//缓存流
	PrintStream out;//输出
	
	private ServerListener outPutUI;//输出
	
	private User user=null;//用户信息
	
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
	
	//登录
	public void login() {
		String msg = null;
		
		System.out.println("Try to login...");
		out.println("login");//协议，向服务器申请登录
		
		//此处的通讯顺序是约定好的，id->name->password
		out.println(user.getId());
		out.println(user.getName());
		out.println(user.getPassword());
		
		//循环等待登录成功的消息
		while (true) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				//System.out.println("Debug[msg]:"+msg);
				if(msg.equals("login successfully")){//不可以msg.equals("login successfully")，原因：https://jingyan.baidu.com/article/1876c852f85d5ec90b1376ba.html
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
		//不断尝试读取
		while (true) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				System.out.println(msg);
				if(isHaveUI()==true){
					//TODO 子线程如何刷新ui？
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
	 * @param user 要设置的 user
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
	 * @param port 要设置的 port
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
	 * @param hostStr 要设置的 hostStr
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
	 * @param isLogin 要设置的 isLogin
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
	 * @param outPutUI 要设置的 outPutUI
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
	 * @param haveUI 要设置的 haveUI
	 */
	public void setHaveUI(boolean haveUI) {
		this.haveUI = haveUI;
	}
}
