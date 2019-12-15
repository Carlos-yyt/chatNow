package server;

import java.util.Map;

public class ChatServer {
	Map<String, User> userMap;//储存所有注册成功的用户的个人信息
	int userNumCeiling;//用户人数上限
	
	String hostStr;//本机地址
	int port;//服务端口
	
}
