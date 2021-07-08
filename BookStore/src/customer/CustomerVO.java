package customer;

import java.io.Serializable;

public class CustomerVO implements Serializable{
	
	private String custId;
	private String pwd;
	private String name;
	private String address;
	private String phone;
	
	
	@Override
	public String toString() {
		return "[custId=" + custId + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
