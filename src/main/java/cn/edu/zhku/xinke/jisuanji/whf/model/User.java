package cn.edu.zhku.xinke.jisuanji.whf.model;

import java.util.Date;

/**
 * @author Paul
 * 
 * */
public class User {
	
	public final static String CURRENT_USER = "_current_user"; 
	
	public final static int TYPE_USER = 1;
	
	public final static int TYPE_STORE_HOLDER = 2;
	
	public final static int TYPE_ADMIN = 3;
	
	private int id;
	
	private String name;
	
	private String password;
	
	private String email;
	
	private int type;
	
	private String status;
	
	private Date registerDate;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password
				+ ", email=" + email + ", type=" + type + ", status=" + status
				+ ", registerDate=" + registerDate + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
}
