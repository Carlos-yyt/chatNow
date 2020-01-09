package server;

/*
 * 服务器主线程
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
 * TODO 消息放在哪里接收/判断类型？
 * 如果放在主线程，那么主线程就必定承担了超过一半的任务量，Server Thread只负责发送消息。不合理。————2019年12月23日 不采用
 * 如果放在ServerThread，线程之间如何通讯？————2019年12月23日 通过自定义监听解决
 * */

public class ChatServer extends Thread implements ServerListener {
	Vector<ServerThread> serverList=new Vector<>();//服务进程列表，vector虽然慢但是线程安全 https://blog.csdn.net/weixin_41596018/article/details/82892900
	private int userNumCeiling;//用户人数上限
	
	private String hostStr;//本机地址
	private int port;//服务端口
	
	ServerSocket serverSocket;//服务socket
	
	BufferedReader in;//缓存流
	PrintStream out;//输出
	
	public ChatServer(String _hostStr,int _port) throws IOException {
		setHostStr(_hostStr);
		setPort(_port);
		serverSocket=new ServerSocket(getPort());
		//TODO 此处缺乏把自己加入serverList的代码，导致服务器本身不能监听
	}
	public ChatServer() throws IOException {
		
	}
	
	public void run() {
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//循环接收新的请求
		while (true) {
			System.out.println("Waiting for new apply...");
			
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}//该方法将一直等待，直到客户端连接到服务器上给定的端口。
			
			System.out.print("Find new apply:");
			System.out.print("IP:"+socket.getRemoteSocketAddress().toString());
			System.out.println("\tport:"+socket.getPort());
			
			//接收一下用户信息，剩下的就交给ServerThread处理了
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
			   
			//此处的通讯顺序是约定好的，id->name->password
			String userStatus = null;
			try {
				userStatus = in.readLine();
				System.out.println("User wants to "+userStatus);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//循环等待用户发送状态消息
			while (true) {
				if (userStatus.equals("login")) {
					break;
					//TODO 登录
				}else if (userStatus.equals("signup")) {
					//TODO 注册
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
			
			//TODO 接收用户个人信息
			User user=new User(socket.getPort(), socket.getRemoteSocketAddress().toString(), _id, _name, _password);
			
			System.out.println("New user's info：");
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
			
			//新旧进程互相监听
			for (ServerThread th : serverList) {
				th.addListener(serverThread);
				serverThread.addListener(th);
			}
			serverList.add(serverThread);//把 新线程 加入服务进程列表
			
			
			
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
	
	@Override
	//服务器监听所有人的对话
	public void getGroupMsg(String msg) {
		System.out.println(msg);
	}
}
