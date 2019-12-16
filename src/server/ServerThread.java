package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * 一个线程服务一个用户
 * */
public class ServerThread extends Thread {
	String hostStr;//本机地址
	int port;//服务端口
	
	Socket socket;//服务端socket
	BufferedReader in;//缓存流
	PrintStream out;//输出
	
	public ServerThread(Socket _socket) throws IOException {
		socket=_socket;
		hostStr=socket.getInetAddress().getHostAddress();//local address
		port=socket.getLocalPort();//local port
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
		out=new PrintStream(socket.getOutputStream());//output
	}
	
	public void run(){
		systemMsg("Hello,");
		systemMsg("You connected to the server successfully.");
		systemMsg("Your IP:"+socket.getRemoteSocketAddress().toString());
		systemMsg("Your port:"+socket.getPort());
		systemMsg("You can talk with others now.");
		
		
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
	
	public void privateMsg(String id,String name){
		//TODO 私聊功能
	}
}
