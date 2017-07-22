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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.DataUtil;
import db.Book;

public class NewRegister extends Dialog {

	protected Object result;
	protected Shell shell;

	private int dialog_kind = 0;
	// private boolean isNoCheck=false;

	protected static Text txt_title;
	protected static Text txt_writer;
	protected static Text txt_genre;
	protected static Text txt_owner;
	protected static Text txt_issueDate;

	private String _title;
	private String _writer;
	private String _genre;
	private String _issueDate;
	private String _owner;
	
	private  ArrayList<Book> books=new ArrayList<Book>();

	public NewRegister(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		dialog_kind = 0; // 정보 입력안된 다이어로그
	}

	public NewRegister(Shell parent, int style, String title,
			String writer, String genre, String issueDate, String owner) {
		super(parent, style);
		setText("SWT Dialog");
		_title = title;
		_writer = writer;
		_genre = genre;
		_issueDate = issueDate;
		_owner = owner;
		dialog_kind = 1; // 정보 입력된 다이어로그

	}

	public Object open() {
		createContents();
		shell.open();
		if (dialog_kind == 1)
			init();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;

	}

	private void init() { // 값들 설정
		txt_title.setText(_title);
		txt_writer.setText(_writer);
		txt_genre.setText(_genre);
		txt_issueDate.setText(_issueDate);
		txt_owner.setText(_owner);
	}

	private void createContents() {
		// TODO Auto-generated method stub
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(370, 280);
		shell.setText("도서등록");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));

		Label lbl_title = new Label(composite, SWT.NONE);
		lbl_title.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_title.setBounds(10, 25, 56, 15);
		lbl_title.setText("도서제목");

		txt_title = new Text(composite, SWT.BORDER);
		txt_title.setBounds(86, 25, 240, 21);

		Label lbl_writer = new Label(composite, SWT.NONE);
		lbl_writer.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_writer.setText("작가");
		lbl_writer.setBounds(10, 63, 56, 15);

		txt_writer = new Text(composite, SWT.BORDER);
		txt_writer.setBounds(86, 63, 240, 21);

		Label lbl_genre = new Label(composite, SWT.NONE);
		lbl_genre.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_genre.setText("장르");
		lbl_genre.setBounds(10, 104, 56, 15);

		txt_genre = new Text(composite, SWT.BORDER);
		txt_genre.setBounds(86, 104, 240, 21);

		Label lbl_owner = new Label(composite, SWT.NONE);
		lbl_owner.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_owner.setText("소유자");
		lbl_owner.setBounds(10, 145, 56, 15);

		txt_owner = new Text(composite, SWT.BORDER);
		txt_owner.setBounds(86, 142, 240, 21);

		txt_issueDate = new Text(composite, SWT.BORDER);
		txt_issueDate.setBounds(86, 179, 240, 21);

		Label lbl_issueDate = new Label(composite, SWT.NONE);
		lbl_issueDate.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_issueDate.setText("발행일");
		lbl_issueDate.setBounds(10, 179, 56, 15);

		Button btn_confirm = new Button(composite, SWT.NONE);
		btn_confirm.setBounds(197, 218, 76, 25);
		btn_confirm.setText("확인");

		btn_confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) { // 확인버튼 눌러서
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				if (dialog_kind == 0) // 정보 입력 안되어있으면
					saveNewRegister();
				else
					modifyRegister();
			}
		});

		Button btn_cancle = new Button(composite, SWT.NONE);
		btn_cancle.setText("취소");
		btn_cancle.setBounds(284, 218, 76, 25);

		/*
		 * Button btn_duplication = new Button(composite, SWT.NONE);
		 * btn_duplication.addSelectionListener(new SelectionAdapter() {
		 * 
		 * @Override public void widgetSelected(SelectionEvent e) {
		 * if(dialog_kind==0) check_duplication_No(0); //중복의 경우 else
		 * check_duplication_No(1); } }); btn_duplication.setText("중복체크");
		 * btn_duplication.setBounds(346, 10, 76, 25);
		 */
	}

	protected void modifyRegister() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {

			switch (DataUtil.command.modifyRegister(txt_title.getText(),
					txt_owner.getText(),
					txt_issueDate.getText(),
					txt_genre.getText(),
					txt_writer.getText())) {
			case 0:
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setText("Failure!");
				messageBox.setMessage("실패");
				messageBox.open();
				break;
			case 1:
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setText("Success!");
				messageBox.setMessage("성공");
				messageBox.open();
				shell.close();
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	protected void saveNewRegister() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			
			/*  if(!isNoCheck) { messageBox = new MessageBox(shell, SWT.OPEN);
			  messageBox.setText("Notify!"); messageBox.setMessage("중복체크 안함");
			  messageBox.open(); return; }*/
						
			if((txt_title.getText().isEmpty() &&
				    txt_genre.getText().isEmpty() && 
				    txt_issueDate.getText().isEmpty() &&
				    txt_owner.getText().isEmpty() &&
				    txt_writer.getText().isEmpty() ))
			{
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setMessage("내용입력");
	
				throw new Exception(""); //값이 비었을 때의 예외처리
			}
			
			else{
				Book book=new Book(txt_title.getText(),
								   txt_genre.getText(),
								   txt_issueDate.getText(),
								   txt_owner.getText(),
								   txt_writer.getText());
				books.add(book);
				SearchDialog.updateTable();
				SearchDialog.clearTxt();
			}
			
			switch (DataUtil.command.saveNewRegister(txt_title.getText(),
					txt_writer.getText(), txt_genre.getText(),
					txt_owner.getText(), txt_issueDate.getText())) {
			case 0:
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setText("Failure!");
				messageBox.setMessage("실패");
				messageBox.open();
				break;
			case 1:
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setText("Success!");
				messageBox.setMessage("등록성공");
				messageBox.open();
				shell.close();
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		

	// protected void check_duplication_No(int kind) {
	// MessageBox messageBox=null;
	//
	// try {
	// switch(DataUtil.command.checkDuplication(Integer.parseInt(txt_no.getText())
	// ,kind)){
	//
	// case -1:
	// messageBox = new MessageBox(shell, SWT.OPEN);
	// messageBox.setText("Error!");
	// messageBox.setMessage("실패");
	// messageBox.open();
	// break;
	// case 0 :
	// messageBox = new MessageBox(shell, SWT.OPEN);
	// messageBox.setText("Error");
	// messageBox.setMessage("중복");
	// messageBox.open();
	// break;
	// case 1 :
	// messageBox = new MessageBox(shell, SWT.OPEN);
	// messageBox.setText("Success!");
	// messageBox.setMessage("사용가능");
	// messageBox.open();
	// isNoCheck = true;
	// break;
	// }
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
		
	
	}
}
