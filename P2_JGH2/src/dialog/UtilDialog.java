package dialog;

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

public class UtilDialog extends Dialog {

	protected Object result;
	protected Shell shell;

	protected  Text txt_title;
	protected  Text txt_writer;
	protected  Text txt_genre;
	protected  Text txt_owner;
	protected  Text txt_issueDate;
	
	private Book book;

	public UtilDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	public UtilDialog(Shell parent, int style, Book book) {
		super(parent, style);
		setText("SWT Dialog");
		this.book = book;
	}
	
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
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
				if(book==null)
					saveNewRegister();
				else
					modifyRegister();
			}
		});

		Button btn_cancle = new Button(composite, SWT.NONE);
		btn_cancle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btn_cancle.setText("취소");
		btn_cancle.setBounds(284, 218, 76, 25);
		init_text();
	}

	protected void modifyRegister() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			modify_book();
			switch (DataUtil.command.modifyRegister(book)) {
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
						DataUtil.command.dialog.saveNewTable();
						shell.close();
						break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void saveNewRegister() {
		// TODO Auto-generated method stub
		try {
			if((txt_title.getText().isEmpty() &&
					txt_genre.getText().isEmpty() && 
					txt_issueDate.getText().isEmpty() &&
					txt_owner.getText().isEmpty() &&
					txt_writer.getText().isEmpty() ))
			{
				throw new Exception(""); //값이 비었을 때의 예외처리
			}
			else{
				if(setRegister())  //값이 있으면
				{
					DataUtil.command.dialog.saveNewTable();
					shell.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public boolean setRegister(){	
		MessageBox messageBox=null;
		boolean success=false;
		
		try {
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
						success=true;
						break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return success;
		
	}
	
	private void init_text() // 텍스트 초기화
	{
		if(book!=null) //book이 null이 아니라면
		{
			txt_title.setText(book.getTITLE());
			txt_owner.setText(book.getOWNER());
			txt_issueDate.setText(book.getISSUE_DATE());
			txt_genre.setText(book.getGENRE());
			txt_writer.setText(book.getWRITER());
		}
	}
	
	private void modify_book()  //수정버튼을 눌렀을 때 팝업창에 데이터 넣기
	{
		book.setTITLE(txt_title.getText());
		book.setWRITER(txt_writer.getText());
		book.setGENRE(txt_genre.getText());
		book.setOWNER(txt_owner.getText());
		book.setISSUE_DATE(txt_issueDate.getText());
	}
}
