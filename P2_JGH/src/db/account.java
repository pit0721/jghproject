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
public class account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public account() {

	}

	private int NO;
	private String ID;
	private String PASSWORD;
	private String USER_NAME;
	private String USER_PHONE;
	private String USER_EMAIL;
	private Date ACCOUNT_CREATE_DATE;
	private Date LAST_LOGIN_DATE;
	
	@Id @GeneratedValue
	@Column(name = "NO")
	public int getNO() {
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
