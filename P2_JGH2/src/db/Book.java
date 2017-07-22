package db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dbo.book", catalog="BOOK")
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int NO;
	private String TITLE;
	private String WRITER;
    private String ISSUE_DATE;
    private String OWNER;
    private String GENRE;
    private Date REG_DATE;
    
    public Book(String title, String writer, String issueDate, String owner, String genre) {
    	setTITLE(title);
    	setWRITER(writer);
    	setOWNER(owner);
    	setGENRE(genre);
    	setISSUE_DATE(issueDate);
	}
    
	public Book() {
		
	}

	@Id @GeneratedValue
	@Column(name="NO")
	public int getNO() {
		return NO;
	}
	public void setNO(int nO) {
		NO = nO;
	}
	
	@Column(name="TITLE")
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	
	@Column(name="WRITER")
	public String getWRITER() {
		return WRITER;
	}
	
	public void setWRITER(String wRITER) {
		WRITER = wRITER;
	}
	
	@Column(name="OWNER")
	public String getOWNER() {
		return OWNER;
	}
	public void setOWNER(String oWNER) {
		OWNER = oWNER;
	}
	
	@Column(name="GENRE")
	public String getGENRE() {
		return GENRE;
	}
	public void setGENRE(String gENRE) {
		GENRE = gENRE;
	}
    
	@Column(name="ISSUE_DATE")
	public String getISSUE_DATE() {
		return ISSUE_DATE;
	}
	public void setISSUE_DATE(String iSSUE_DATE) {
		ISSUE_DATE = iSSUE_DATE;
	}
    
	@Column(name="REG_DATE")
	public Date getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(Date rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	
}