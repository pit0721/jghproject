package control;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import db.HibernateSessionFactory;
import db.account;

public class command {
	
	public account currentAccout; //account 클래스의 currentAccout 변수 선언
	
	public account getCurrentAccout() {  
		return this.currentAccout;
	}

	public void setCurrentAccout(account currentAccout) {
		this.currentAccout = currentAccout;
	}

	@SuppressWarnings("finally")
	public int checkLogin(String id, String password) 
	{
		Session session = null; //Session:어플리케이션과 JDBC 사이의 단일쓰레드
		Transaction tx = null;  //Transaction: 어플리케이션에 의해 사용되는 단일 쓰레드
		int returnVal = 0;
		try {
			session = HibernateSessionFactory.getSession();			
			account account = (account)session.createCriteria(account.class).add(Restrictions.eq("ID", id)).uniqueResult(); //account 클래스로 형변환. 
								//session.createCriteria()메소드를 사용해 기본 검색 조건을 갖는 Criteria 인스턴스 생성. 메소드 인자로 매핑 설정파일에서 명시한 자바 클래스가 옴.
								//org.hibernate.Criteria인터페이스는 특정 영속 클래스에 대한 질의를 표현.Restrictions 클래스는 어떤 미리 만들어진 Criterion 타입들을 얻는 팩토리 메소드들을 정의
			session.close();
			
			if(account == null) 
				returnVal = 0;
			else
			{
				if(account.getPASSWORD().equals(password)) //계정의 입력된 패스워드가 같다면
				{
					session = HibernateSessionFactory.getSession();
					tx = session.beginTransaction();
					
					account.setLAST_LOGIN_DATE(Calendar.getInstance().getTime());					
					session.saveOrUpdate(account);
					
					tx.commit();
					session.close();
					
					setCurrentAccout(account);
					
					returnVal = 1;
				}
				else
					returnVal = 2;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isOpen() || session.isConnected())
				session.close();
			//e.printStackTrace();
			returnVal = -1;
		}
		finally
		{
			return returnVal;
		}
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public int checkDuplicationId(String id, int kind)
	{
		Session session = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			List<account> account = session.createCriteria(account.class).add(Restrictions.eq("ID", id)).list();
			session.close();
				
			if(kind == 0)
			{
				for(account acc : account)
				{
					if(acc.getID().equals(id))
					{
						returnVal = 0;
						break;
					}
				}
			}
			else
			{
				int duplicationCount = 0;
				for(account acc : account)
				{
					if(acc.getID().equals(id))
						duplicationCount++;
				}
				if(duplicationCount > kind)
					returnVal = 0;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isOpen() || session.isConnected())
				session.close();
			returnVal = -1;
		}
		finally
		{
			return returnVal;
		}
	}
	
	@SuppressWarnings("finally")
	public int saveNewRegister(String id, String password, String name, String phone, String email)
	{
		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			account account = new account();
			account.setID(id);
			account.setPASSWORD(password);
			account.setUSER_NAME(name);
			account.setUSER_PHONE(phone);
			account.setUSER_EMAIL(email);
			account.setACCOUNT_CREATE_DATE(Calendar.getInstance().getTime());
			
			session.saveOrUpdate(account);
			tx.commit();
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isOpen() || session.isConnected())
				session.close();
			returnVal = 0;
		}
		finally
		{
			return returnVal;
		}
	}
	
	@SuppressWarnings("finally")
	public int modifyRegister(String id, String password, String name, String phone, String email)
	{
		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			account account = (account)session.createCriteria(account.class).add(Restrictions.eq("ID", id)).uniqueResult();			
			account.setPASSWORD(password);
			account.setUSER_NAME(name);
			account.setUSER_PHONE(phone);
			account.setUSER_EMAIL(email);
			
			session.saveOrUpdate(account);
			tx.commit();
			session.close();
			
			setCurrentAccout(account);
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isOpen() || session.isConnected())
				session.close();
			returnVal = 0;
		}
		finally
		{
			return returnVal;
		}
	}
}
