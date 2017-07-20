package main;

public class Person_info {    //변수들을  private로 선언. 

	private String address;   
	private String phone;
	private String email;
	
	public String getAddress() {  //메소드를 외부에서 접근하게 하기 위해 getter,setter
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
}
