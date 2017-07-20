package main;

public class Person extends Person_info {  //Person_info 에 상속받은 Person클래스
	private String name;	
	private int age;
	private String sex;
	
	public Person() //생성자. 클래스 초기화
	{
		
	}
	public Person(String name, int age, String sex, String address, String phone, String email)  //생성자 오버라이딩
	{
		setName(name);
		setAge(age);
		setSex(sex);
		setAddress(address);
		setPhone(phone);
		setEmail(email);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
