package chatNow;

public class User {
	private int port;//端口号
	private String hostStr;//IP地址
	
	private String id;//账号，User的唯一辨识标志
	private String name;//昵称，允许重复
	private String password;//账号
	
	public User(int _port,String _hostStr,String _id,String _name,String _password){
		this.setPort(_port);
		this.setHostStr(_hostStr);
		this.setId(_id);
		this.setName(_name);
		this.setPassword(_password);
	}
	
	String showInfo(){
		String infoStr = null;
		
		infoStr+="id:"+getId();
		infoStr+="name:"+getName();
		infoStr+="password:"+getPassword();
		infoStr+="IP:"+getHostStr();
		infoStr+="port:"+getPort();
		
		return infoStr;
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
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password 要设置的 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 要设置的 id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
