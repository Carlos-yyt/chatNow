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
 * һ���̷߳���һ���û�
 * */
public class ServerThread extends Thread implements ServerListener {
	String hostStr;//������ַ
	int port;//����˿�
	
	Socket socket;//�����socket
	BufferedReader in;//������
	PrintStream out;//���
	
	Vector<ServerThread> listenerList=new Vector<>();//Ⱥ���б�
	
	User user;//����Ķ�����û���Ϣ
	
	//���Ⱥ������
	public void addListener(ServerThread newThread) {
		listenerList.add(newThread);
	}
	
	//��ȡ�Ѿ�����Ⱥ�ĵ��û�������
	public void setListenerList(Vector<ServerThread> _listenerList) {
		listenerList=_listenerList;
	}
	
	public ServerThread(Socket _socket,User _user) throws IOException {
		socket=_socket;
		hostStr=socket.getInetAddress().getHostAddress();//local address
		port=socket.getLocalPort();//local port
		in=new BufferedReader(new InputStreamReader(socket.getInputStream()));//input
		out=new PrintStream(socket.getOutputStream());//output
		
		user=_user;//�û���Ϣ
	}
	
	public void run(){
		
		out.println("login successfully");//��仰���ܸ��ģ�����ͨѶЭ���һ����
		
		//��ӭ�Ļ�
		systemMsg("Hello,");
		systemMsg("You connected to the server successfully.");
		systemMsg("Your IP:"+socket.getRemoteSocketAddress().toString());
		systemMsg("Your port:"+socket.getPort());
		systemMsg("You can talk with others now.");
		
		
		//ѭ�����������û�����Ϣ
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
					//˽��
					int aimUserID=Integer.valueOf(msg.substring(1, 4));//TODO Ĭ���˺�3λ���Ժ�ĳ�������ʽ
					privateMsg(aimUserID, msg.substring(4));
				}else {
					switch (msg) {
					case "CLOSE":
						isClosed=true;
						break;
					default:
						//Ĭ��Ⱥ����Ϣ
						groupMsg(msg);
						break;
					}					
				}
				//TODO ���������û�����Ϣ
				
				
				msg=null;
			}
		}
		
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
	
	//����Ⱥ��Ϣ
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
		String msg=user.getName()+"("+user.getId()+")"+"���㷢����˽����Ϣ��"+_msg;
		for (ServerThread th : listenerList) {
			if (Integer.valueOf(th.user.getId())==_id) {
				th.getGroupMsg(msg);
			}
		}
	}

	@Override
	//�������������û���Ⱥ����Ϣ
	public void getGroupMsg(String msg) {
		//System.out.println("debug002-serverThread-getGroupMsg()");
		out.println(msg);//�����Լ����û���
	}
}
