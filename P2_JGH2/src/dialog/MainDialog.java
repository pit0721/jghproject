package dialog;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.DataUtil;
import db.Book;

public class MainDialog extends Dialog {  //Dialog에서 상속받음

	protected Object result;
	protected Shell shlSearch;
	protected Shell shell;
	private Text txt_search;
	private  Table tbl_list;
	protected Book book;
	private Button btn_search;

	private ArrayList<Book> books=new ArrayList<Book>();
	private Button radio_bookWriter;
	private Button radio_bookTitle;

	public ArrayList<Book> getBook() {
		return books;
	}
	public void setBook(ArrayList<Book> book) {
		books = book;
	}

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public MainDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		// TODO Auto-generated constructor stub
	}
	public Object open() {
		createContents();
		shlSearch.open();
		shlSearch.layout();
		Display display=getParent().getDisplay();
		while (!shlSearch.isDisposed()) {  //이벤트 루프 시작
			if (!display.readAndDispatch()) {
				display.sleep(); ////이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}
	private void createContents() {
		// TODO Auto-generated method stub
		shlSearch = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlSearch.setSize(746, 366);
		shlSearch.setText("도서검색");
		shlSearch.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shlSearch, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		radio_bookWriter = new Button(composite, SWT.RADIO);
		radio_bookWriter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		radio_bookWriter.setBounds(10, 26, 91, 16);
		radio_bookWriter.setText("작가");
		
		radio_bookTitle = new Button(composite, SWT.RADIO);
		
		radio_bookTitle.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		radio_bookTitle.setBounds(70, 26, 91, 16);
		radio_bookTitle.setText("도서제목");

		txt_search = new Text(composite, SWT.BORDER);
		txt_search.setBounds(204, 26, 296, 21);

		btn_search = new Button(composite, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				search();
			}
		});
		btn_search.setBounds(620, 10, 106, 25);
		btn_search.setText("검색");

		Button btn_register = new Button(composite, SWT.NONE);
		btn_register.setBounds(620, 41, 106, 25);
		btn_register.setText("등록");

		btn_register.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				popUp();
			}
		});

		Button btn_modify = new Button(composite, SWT.NONE);
		btn_modify.setBounds(620, 75, 106, 25);
		btn_modify.setText("수정");

		btn_modify.addSelectionListener(new SelectionAdapter() {
			
			public void widgetSelected(SelectionEvent e){
				///MessageBox messageBox=null;
				if(tbl_list.getSelectionIndex()!=-1) //리스트에서 선택한 데이터의 index값이 0 미만이 아니면 수정팝업창 띄우기
					popUpModify();	
				else 
				{
					/*messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("도서선택");
					messageBox.setMessage("도서를 선택하세요");
					messageBox.open();*/
				}
			}
		});

		tbl_list = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tbl_list.setBounds(0, 106, 736, 228);
		tbl_list.setHeaderVisible(true);
		tbl_list.setVisible(true);

		TableColumn tblClmn_PK = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_PK.setWidth(0);

		TableColumn tblClmn_NO = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_NO.setWidth(60);
		tblClmn_NO.setText("번호");

		TableColumn tblClmn_title = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_title.setWidth(112);
		tblClmn_title.setText("제목");

		TableColumn tblClmn_writer = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_writer.setWidth(112);
		tblClmn_writer.setText("작가");

		TableColumn tblClmn_genre = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_genre.setWidth(112);
		tblClmn_genre.setText("장르");

		TableColumn tblClmn_owner = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_owner.setWidth(112);
		tblClmn_owner.setText("소유자");

		TableColumn tblClmn_issueDate = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_issueDate.setWidth(112);
		tblClmn_issueDate.setText("발행일");

		TableColumn tblClmn_regDate = new TableColumn(tbl_list, SWT.CENTER);
		tblClmn_regDate.setWidth(112);
		tblClmn_regDate.setText("등록일");

		DataUtil.command.init_table(tbl_list);
	}

	protected void search() {
		// TODO Auto-generated method stub
		try {
			if(radio_bookTitle.getSelection())
			{
				search(1, txt_search.getText());
			}
			else if(radio_bookWriter.getSelection())
			{
				search(2, txt_search.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void search(int kind, String text) {
		// TODO Auto-generated method stub
		MessageBox messageBox=null;
		try {
			switch(DataUtil.command.searchResult(kind, text)){//searchResult()에 kind,text 값 넣음
				case 0:
					messageBox = new MessageBox(shlSearch, SWT.OPEN);
					messageBox.setText("Error");
					messageBox.setMessage("실패");
					messageBox.open();	
					break;
				case 1:
					messageBox = new MessageBox(shlSearch, SWT.OPEN);
					messageBox.setText("Success");
					messageBox.setMessage("성공");
					searchResultTable();
					messageBox.open();	
					break;
				case 2:
					saveNewTable();
					break;
					
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void popUp(){  //등록할 때 새창 띄우기
		try {
			UtilDialog utilDialog=new UtilDialog(shlSearch, SWT.OPEN);
			utilDialog.open();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	protected void popUp(Book book){  //등록할 때 새창 띄우기
		try {
			UtilDialog utilDialog=new UtilDialog(shlSearch, SWT.OPEN, book);
			utilDialog.open();
		} catch (Exception e) {
			// TODO: handle exception#
			e.printStackTrace();
		}
	}

	protected void popUpModify() {
		// TODO Auto-generated method stub
		Book book = null;
		try {
			book = new Book();
			TableItem item = tbl_list.getItem(tbl_list.getSelectionIndex()); //getSelectionIndex():index 전체의 값을 가져옴
			book.setNO(Integer.parseInt(item.getText(0)));
			book.setTITLE(item.getText(2));
			book.setWRITER(item.getText(3));
			book.setGENRE(item.getText(4));
			book.setOWNER(item.getText(5));
			book.setISSUE_DATE(item.getText(6));
			if(!item.getText(7).equals("null"))
				book.setREG_DATE(DataUtil.command.transFormat.parse(item.getText(7)));
			
			popUp(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void saveNewTable() {
		tbl_list.removeAll(); 
		int count = 1;  //기본키가 아닌 그냥 번호를 테이블에 보이게하기 위해  
		for (Book boo : DataUtil.command.books) {  //books 배열에 들어있는 값들을 하나씩 boo에 넣음
			TableItem item = new TableItem(tbl_list, SWT.INSERT); 
			item.setText(new String[]{String.valueOf(boo.getNO()),String.valueOf(count), boo.getTITLE(), boo.getWRITER(),
					boo.getGENRE(), boo.getOWNER(), boo.getISSUE_DATE(), String.valueOf(boo.getREG_DATE()) });
			count++;
		}
		tbl_list.update();
	}
		
	private void searchResultTable() { //검색 테이블
		// TODO Auto-generated method stub
		tbl_list.removeAll();
		int count=1;
		for(Book b : DataUtil.command.search_books){
			TableItem item=new TableItem(tbl_list, SWT.INSERT);
			item.setText(new String[]{String.valueOf(b.getNO()),String.valueOf(count), b.getTITLE(), b.getWRITER(),
					b.getGENRE(), b.getOWNER(), b.getISSUE_DATE(), String.valueOf(b.getREG_DATE()) });
			count++;
		}
	}
}
