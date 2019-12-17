package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ChatClient  extends Thread{
	String hostStr;//本机地址
	int port;//服务端口
	
	Socket socket;//服务端socket
	BufferedReader in;//缓存流
	PrintStream out;//输出
	public ChatClient(Socket _socket) throws IOException {
		socket=_socket;
		hostStr=socket.getInetAddress().getHostAddress();
		port=socket.getLocalPort();
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
		out=new PrintStream(socket.getOutputStream());//output
	}
	
	public void run() {
		String msg = null;
		while (true) {
			try {
				msg=in.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (msg!=null) {
				//TODO 子线程如何刷新ui？
				System.out.println(msg);
				msg=null;
			}
		}
	}
}
