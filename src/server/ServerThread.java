package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * һ���̷߳���һ���û�
 * */
public class ServerThread extends Thread {
	String hostStr;//������ַ
	int port;//����˿�
	
	Socket socket;//�����socket
	BufferedReader in;//������
	PrintStream out;//���
	
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
		
		//����ͨѶ
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//ϵͳ��Ϣ
	public void systemMsg(String msg){
		msg="[System message]"+msg;
		out.println(msg);
	}
	
	public void privateMsg(String id,String name){
		//TODO ˽�Ĺ���
	}
}
