package control;

import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import db.Book;
import db.HibernateSessionFactory;

public class Command {
	
	public Book currentBook;
	
	public Book getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	}

	
	@SuppressWarnings("finally")
	public int bookList(String title) {
		// TODO Auto-generated method stub
		Session session=null;
		Transaction tx=null;
		
		int returnVal=0;
		try {
			session=HibernateSessionFactory.getSession();
			Book book=(Book) session.createCriteria(Book.class).add(Restrictions.eq("TITLE",title)).uniqueResult();
			session.close();
			
			if(book ==null)
				returnVal=0;
			else{
				if(book.getTITLE().equals(title))
				{
					session=HibernateSessionFactory.getSession();
					tx=session.beginTransaction();
					
					tx.commit();
					session.close();
					
					setCurrentBook(book);
					returnVal=1;
				}
				else
					returnVal=2;
			}
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isConnected() || session.isConnected()){
				session.close();
				returnVal=-1;
			}
		}finally
		{
			return returnVal;
		}
		
	}

//	@SuppressWarnings({"finally","unchecked"})
//	public int checkDuplication(int no, int kind) {
//		// TODO Auto-generated method stub
//		Session session = null;
//		int returnVal = 1;
//		try {
//			session = HibernateSessionFactory.getSession();
//			List<Book> book = session.createCriteria(Book.class).list();
//			session.close();
//				
//			if(kind == 0)
//			{
//				for(Book boo : book)
//				{
//					if(boo.getNO() == no)
//					if(String.valueOf(boo.getNO()).equals(no)) //입력한 값이 같으면
//					{
//						returnVal = 0;
//						break;
//					}
//				}
//			}
//			else
//			{
//				int duplicationCount = 0;
//				for(Book boo : book)
//				{
//					if(String.valueOf(boo.getNO()).equals(no))
//						duplicationCount++;  //중복횟수 증가
//				}
//				if(duplicationCount > kind)  
//					returnVal = 0;
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			if(session.isOpen() || session.isConnected())
//				session.close();
//			returnVal = -1;
//		}
//		finally
//		{
//			return returnVal;
//		}
//	
//	}

	@SuppressWarnings("finally")
	public int saveNewRegister(String title, String writer,
			String issue_date, String owner, String genre) {

		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Book book = new Book();
			book.setTITLE(title);
			book.setWRITER(writer);
			book.setGENRE(genre);
			book.setOWNER(owner);
			book.setISSUE_DATE(issue_date);
			book.setREG_DATE(Calendar.getInstance().getTime());
			
			session.saveOrUpdate(book);
			tx.commit();
			session.close();
		} catch (Exception e) {
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
	public int modifyRegister(String title, String writer, String genre,
			String issueDATE, String owner) {
		
		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			Book book = (Book)session.createCriteria(Book.class).add(Restrictions.eq("TITLE", title)).uniqueResult();			
			book.setWRITER(writer);
			book.setGENRE(genre);
			book.setOWNER(owner);
			book.setISSUE_DATE(issueDATE);
			
			session.saveOrUpdate(book);
			tx.commit();
			session.close();
			
			setCurrentBook(book);
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
