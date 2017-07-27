package control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import db.Book;
import db.HibernateSessionFactory;
import dialog.MainDialog;

public class Command {
	
	public static Book currentBook;
	public ArrayList<Book> books = new ArrayList<Book>();
	public ArrayList<Book> search_books = new ArrayList<Book>();
	public MainDialog dialog;
	
	public SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	
	public Book getCurrentBook() {
		return this.currentBook;
	}

	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	}
	
	@SuppressWarnings({ "unchecked", "finally" })
	public int searchResult(int _kind, String key) {
		// TODO Auto-generated method stub
		Session session=null;
		int kind=1;
		try {
			search_books.clear();
			session=HibernateSessionFactory.getSession();
			
			if(_kind == 1)
			{
				search_books = (ArrayList<Book>) session.createCriteria(Book.class).add(Restrictions.like("TITLE", "%"+key+"%")).list(); //like():중복되는거 있으면 같이 출력. TITLE의 key 앞뒤로 %사용
			}
			else
			{
				search_books = (ArrayList<Book>) session.createCriteria(Book.class).add(Restrictions.like("WRITER", "%"+key+"%")).list();
			}
			
			if(search_books.size() != 0){
				kind = 1;
			}
			else{
				kind = 2;
			}			
			
			session.close();
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isOpen() || session.isConnected())
				session.close();
			kind = 0;
		}
		finally{
			return kind;
		}
	}

	@SuppressWarnings("finally") //경고를 제외해주는 어노테이션
		public int saveNewRegister(String title, String writer,
			String issue_date, String owner, String genre) {

		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			//Book 클래스의 book변수 선언하고 book의 값들 등록 
			Book book = new Book();
			book.setTITLE(title);
			book.setWRITER(writer);
			book.setGENRE(genre);
			book.setOWNER(owner);
			book.setISSUE_DATE(issue_date);
			book.setREG_DATE(Calendar.getInstance().getTime());
			
			session.saveOrUpdate(book);
			tx.commit();
			books.add(book);
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
	public int modifyRegister(Book book) {
		
		Session session = null;
		Transaction tx = null;
		int returnVal = 1;
		try {
			session = HibernateSessionFactory.getSession();
			tx = session.beginTransaction();
			
			session.saveOrUpdate(book);
			tx.commit();
			session.close();
			
			for(int i=0; i<DataUtil.command.books.size(); i++)  //i=0부터 books의 size()의 크기까지 증가
			{
				if(DataUtil.command.books.get(i).getNO() == book.getNO()) //book의 가져온 i값의 getNO()가 book의 getNO()랑 같다면 
				{
					DataUtil.command.books.get(i).setGENRE(book.getGENRE());
					DataUtil.command.books.get(i).setISSUE_DATE(book.getISSUE_DATE());
					DataUtil.command.books.get(i).setOWNER(book.getOWNER());
					DataUtil.command.books.get(i).setREG_DATE(book.getREG_DATE());
					DataUtil.command.books.get(i).setTITLE(book.getTITLE());				
					DataUtil.command.books.get(i).setWRITER(book.getWRITER());
				}
			}
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

	@SuppressWarnings({ "unchecked" })
	public void init_table(Table tbl_list)
	{
		Session session=null;
		Transaction tx=null;
		
		try {
			session=HibernateSessionFactory.getSession();
			books = (ArrayList<Book>) session.createCriteria(Book.class).list(); //Book클래스의 리스트에서 리스트를 books에 넣는다.
			session.close();
			for(int i=0; i<books.size(); i++) //i=0부터 books의 size()까지 증가
			{
				TableItem item = new TableItem(tbl_list, SWT.INSERT); 
				//item에 String 배열값들을 저장
				item.setText(new String[]{String.valueOf(books.get(i).getNO()), String.valueOf(i+1), books.get(i).getTITLE(), books.get(i).getWRITER(),
						books.get(i).getGENRE(), books.get(i).getOWNER(), String.valueOf(books.get(i).getISSUE_DATE()), String.valueOf(books.get(i).getREG_DATE()) });
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			if(session.isConnected() || session.isConnected()){
				session.close();
			}
		}finally
		{
		}
	}
}
