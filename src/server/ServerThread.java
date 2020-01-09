package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;import com.sun.javafx.property.adapter.PropertyDescriptor.Listener;

import chatNow.User;



/*
 * 一个线程服务一个用户
 * */
public class ServerThread extends Thread implements ServerListener {
	String hostStr;//本机地址
	int port;//服务端口
	
	Socket socket;//服务端socket
	BufferedReader in;//缓存流
	PrintStream out;//输出
	
	Vector<ServerThread> listenerList=new Vector<>();//群发列表
	
	User user;//服务的对象的用户信息
	
	//添加群发对象
	public void addListener(ServerThread newThread) {
		listenerList.add(newThread);
	}
	
	//获取已经加入群聊的用户的名单
	public void setListenerList(Vector<ServerThread> _listenerList) {
		listenerList=_listenerList;
	}
	
	public ServerThread(Socket _socket,User _user) throws IOException {
		socket=_socket;
		hostStr=socket.getInetAddress().getHostAddress();//local address
		port=socket.getLocalPort();//local port
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
		out=new PrintStream(socket.getOutputStream());//output
		
		user=_user;//用户信息
	}
	
	public void run(){
		
		out.println("login successfully");//这句话不能更改，属于通讯协议的一部分
		
		//欢迎的话
		systemMsg("Hello,");
		systemMsg("You connected to the server successfully.");
		systemMsg("Your IP:"+socket.getRemoteSocketAddress().toString());
		systemMsg("Your port:"+socket.getPort());
		systemMsg("You can talk with others now.");
		
		
		//循环接收来自用户的消息
		String msg=null;
		boolean isClosed=false;
		while (isClosed==false) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				if (msg.charAt(0)=='@') {
					//私聊
					int aimUserID=Integer.valueOf(msg.substring(1, 4));//TODO 默认账号3位，以后改成正则表达式
					privateMsg(aimUserID, msg.substring(4));
				}else {
					switch (msg) {
					case "CLOSE":
						isClosed=true;
						break;
					default:
						//默认群发消息
						groupMsg(msg);
						break;
					}					
				}
				//TODO 处理来自用户的消息
				
				
				msg=null;
			}
		}
		
		systemMsg("The connect is cancealed.");
		out.close();
		
		//结束通讯
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//系统消息
	public void systemMsg(String msg){
		msg="[System message]"+msg;
		out.println(msg);
	}
	
	//发送群消息
	public void groupMsg(String msg) {
		msg=user.getName()+"("+user.getId()+")"+":"+msg;
		System.out.println(msg);
		Iterator<ServerThread> iterator=listenerList.iterator();
		while (iterator.hasNext()) {
			//System.out.println("debug001-serverThread-into loop");
			iterator.next().getGroupMsg(msg);
		}
	}
	
	public void privateMsg(int _id,String _msg){
		String msg=user.getName()+"("+user.getId()+")"+"给你发了条私密消息："+_msg;
		for (ServerThread th : listenerList) {
			if (Integer.valueOf(th.user.getId())==_id) {
				th.getGroupMsg(msg);
			}
		}
	}

	@Override
	//接收来自其他用户的群发消息
	public void getGroupMsg(String msg) {
		//System.out.println("debug002-serverThread-getGroupMsg()");
		out.println(msg);//发给自己的用户端
	}
}
