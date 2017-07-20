package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import dialog.NewRegister;

public class SearchDialog extends Dialog {  //Dialog에서 상속받음

	protected Object result;
	protected Shell shlSearch;
	protected Shell shell;
	private Text txt_name;
	private Text text_birth;
	private Text txt_phone;
	private Text txt_home;
	private Text btn_sex;
	private Text txt_search;
	private Table tbl_list;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SearchDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		// TODO Auto-generated constructor stub
	}
	/**
	 * Open the dialog.
	 * @return the result
	 */
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
		shlSearch.setSize(708, 366);
		shlSearch.setText("도서검색");
		shlSearch.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlSearch, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button radio_bookNo = new Button(composite, SWT.RADIO);
		radio_bookNo.setBounds(10, 26, 91, 16);
		radio_bookNo.setText("도서번호");
		
		Button radio_bookTitle = new Button(composite, SWT.RADIO);
		radio_bookTitle.setBounds(107, 26, 91, 16);
		radio_bookTitle.setText("도서제목");
		
		Button btn_search = new Button(composite, SWT.NONE);
		btn_search.setBounds(527, 10, 106, 25);
		btn_search.setText("검색");
		
		btn_search.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				bookList();
			}
			
		});
		
		txt_search = new Text(composite, SWT.BORDER);
		txt_search.setBounds(204, 26, 296, 21);
		
		Button btn_register = new Button(composite, SWT.NONE);
		btn_register.setBounds(527, 50, 106, 25);
		btn_register.setText("등록");
		
		btn_register.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				popUp();
			}
			
			
		});
		
		tbl_list = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tbl_list.setBounds(0, 106, 698, 228);
		tbl_list.setHeaderVisible(true);
		tbl_list.setLinesVisible(true);
		
		TableColumn tblClmn_NO = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_NO.setWidth(100);
		tblClmn_NO.setText("    번호");
		
		TableColumn tblClmn_title = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_title.setWidth(100);
		tblClmn_title.setText("제목");
		
		TableColumn tblClmn_writer = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_writer.setWidth(100);
		tblClmn_writer.setText("작가");
		
		TableColumn tblClmn_genre = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_genre.setWidth(100);
		tblClmn_genre.setText("장르");
		
		TableColumn tblClmn_issueDate = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_issueDate.setWidth(100);
		tblClmn_issueDate.setText("발행일");
		
		TableColumn tblClmn_regDate = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_regDate.setWidth(100);
		tblClmn_regDate.setText("등록일");
		
		TableColumn tblClmn_owner = new TableColumn(tbl_list, SWT.NONE);
		tblClmn_owner.setWidth(100);
		tblClmn_owner.setText("소유자");
		
	}
	
	protected void bookList() {
		
	}
	
	protected void popUp(){  //등록할 때 새창 띄우기
		try {
			NewRegister newRegister=new NewRegister(shlSearch, SWT.OPEN);
			newRegister.open();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
}
