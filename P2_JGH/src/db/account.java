package db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dbo.account", catalog = "ACCOUNT")
public class account implements Serializable {  //인터페이스 Serializable를 구현하는 클래스 생성. 직렬화 사용.
												//직렬화:객체를 전송 가능한 형태로 만드는 것

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public account() { //생성자 초기화 . 초기화하는 이유는 인스턴스 변수가 여러 개 있으면 각각 접근해서 값을 넣어줘야 하기 때문. 아무것도 없이 생성하면 디폴트 생성자(클래스는 생성자가 반드시 있어야하므로)

	}

	private int NO;       //캡슐화를 위해 private 선언
	private String ID;
	private String PASSWORD;
	private String USER_NAME;
	private String USER_PHONE;
	private String USER_EMAIL;
	private Date ACCOUNT_CREATE_DATE;
	private Date LAST_LOGIN_DATE;
	
	@Id @GeneratedValue
	@Column(name = "NO")
	public int getNO() {    //메소드를 외부에서 접근하게 하기 위해 getter, setter 사용
		return NO;
	}
	public void setNO(int nO) {
		NO = nO;
	}
	
	@Column(name = "ID")
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	
	@Column(name = "PASSWORD")
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
	@Column(name = "USER_NAME")
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	
	@Column(name = "USER_PHONE")
	public String getUSER_PHONE() {
		return USER_PHONE;
	}
	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}
	
	@Column(name = "USER_EMAIL")
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	
	@Column(name = "ACCOUNT_CREATE_DATE")
	public Date getACCOUNT_CREATE_DATE() {
		return ACCOUNT_CREATE_DATE;
	}
	public void setACCOUNT_CREATE_DATE(Date aCCOUNT_CREATE_DATE) {
		ACCOUNT_CREATE_DATE = aCCOUNT_CREATE_DATE;
	}
	
	@Column(name = "LAST_LOGIN_DATE")
	public Date getLAST_LOGIN_DATE() {
		return LAST_LOGIN_DATE;
	}
	public void setLAST_LOGIN_DATE(Date lAST_LOGIN_DATE) {
		LAST_LOGIN_DATE = lAST_LOGIN_DATE;
	}
}
