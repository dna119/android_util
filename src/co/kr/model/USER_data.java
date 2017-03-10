package co.kr.model;
public class USER_data{
	String user_id;
	String user_pass;
/*	public String getUser_id() {
		return user_id;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	*/
	public USER_data(String user_id, String user_pass) {
		super();
		this.user_id = user_id;
		this.user_pass = user_pass;
	}
	
	
}