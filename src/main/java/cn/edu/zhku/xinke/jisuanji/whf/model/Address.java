package cn.edu.zhku.xinke.jisuanji.whf.model;

public class Address {

	private int id;

	private int region;

	private String verboseAddress;

	private int user;
	
	private String receiverName;
	
	private String receiverPhone;
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRegion() {
		return region;
	}

	public void setRegion(int region) {
		this.region = region;
	}

	public String getVerboseAddress() {
		return verboseAddress;
	}

	public void setVerboseAddress(String verboseAddress) {
		this.verboseAddress = verboseAddress;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", region=" + region + ", verboseAddress="
				+ verboseAddress + ", user=" + user + ", receiverName="
				+ receiverName + ", receiverPhone=" + receiverPhone + "]";
	}

}
