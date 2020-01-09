package chatNow;

public class User {
	private int port;//�˿ں�
	private String hostStr;//IP��ַ
	
	private String id;//�˺ţ�User��Ψһ��ʶ��־
	private String name;//�ǳƣ������ظ�
	private String password;//�˺�
	
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
	 * @param port Ҫ���õ� port
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
	 * @param hostStr Ҫ���õ� hostStr
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
	 * @param name Ҫ���õ� name
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
	 * @param password Ҫ���õ� password
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
	 * @param id Ҫ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
}
