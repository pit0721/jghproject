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

public class NewRegister extends Dialog {

	protected Object result;
	protected Shell shell;
	
	private int dialog_kind=0;
	private boolean isNoCheck=false;
	
	private Text txt_title;
	private Text txt_no;
	private Text txt_writer;
	private Text txt_genre;
	private Text txt_owner;
	private Text txt_regDate;
	private Text txt_issueDate;
	
	private String _no;
	private String _title;
	private String _writer;
	private String _genre;
	private String _issueDate;
	private String _regDate;
	private String _owner;
	private Text text;
	private Button btn_duplication;
	private Button btn_confirm;
	private Button btn_cancle;
	
	public NewRegister(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		dialog_kind = 0;  //정보 입력안된 다이어로그
	}
	
	public NewRegister(Shell parent, int style, String no, String title, String writer, String genre, String issueDate, String regDate, String owner){
		super(parent, style);
		setText("SWT Dialog");
		_no=no;
		_title=title;
		_writer=writer;
		_genre=genre;
		_issueDate=issueDate;
		_regDate=regDate;
		_owner=owner;
		dialog_kind=1; //정보 입력된 다이어로그
		
	}
	
	public Object open(){
		createContents();		
		shell.open();
		if(dialog_kind==1)
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

	private void init() {  //값들 설정
		txt_no.setText(_no);
		txt_title.setText(_title);
		btn_duplication.setEnabled(false);
		txt_genre.setText(_genre);
		txt_issueDate.setText(_issueDate);
		txt_regDate.setText(_regDate);
		txt_owner.setText(_owner);
	}

	private void createContents() {
		// TODO Auto-generated method stub
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(442, 349);
		shell.setText("도서등록");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite=new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label lbl_title = new Label(composite, SWT.NONE);
		lbl_title.setBounds(10, 52, 56, 15);
		lbl_title.setText("제목");
		
		txt_title = new Text(composite, SWT.BORDER);
		txt_title.setBounds(86, 52, 240, 21);
		
		Label lbl_no = new Label(composite, SWT.NONE);
		lbl_no.setText("번호");
		lbl_no.setBounds(10, 10, 56, 15);
		
		txt_no = new Text(composite, SWT.BORDER);
		txt_no.setBounds(86, 10, 240, 21);
		
		Label lbl_writer = new Label(composite, SWT.NONE);
		lbl_writer.setText("작가");
		lbl_writer.setBounds(10, 90, 56, 15);
		
		txt_writer = new Text(composite, SWT.BORDER);
		txt_writer.setBounds(86, 90, 240, 21);
		
		Label lbl_genre = new Label(composite, SWT.NONE);
		lbl_genre.setText("장르");
		lbl_genre.setBounds(10, 131, 56, 15);
		
		txt_genre = new Text(composite, SWT.BORDER);
		txt_genre.setBounds(86, 131, 240, 21);
		
		Label lbl_owner = new Label(composite, SWT.NONE);
		lbl_owner.setText("소유자");
		lbl_owner.setBounds(10, 248, 56, 15);
		
		txt_owner = new Text(composite, SWT.BORDER);
		txt_owner.setBounds(86, 245, 240, 21);
		
		Label lbl_regDate = new Label(composite, SWT.NONE);
		lbl_regDate.setText("등록일");
		lbl_regDate.setBounds(10, 207, 56, 15);
		
		txt_regDate = new Text(composite, SWT.BORDER);
		txt_regDate.setBounds(86, 207, 240, 21);
		
		txt_issueDate = new Text(composite, SWT.BORDER);
		txt_issueDate.setBounds(86, 168, 240, 21);
		
		Label lbl_issueDate = new Label(composite, SWT.NONE);
		lbl_issueDate.setText("발행일");
		lbl_issueDate.setBounds(10, 168, 56, 15);
		
		Button btn_confirm = new Button(composite, SWT.NONE);
		btn_confirm.setBounds(258, 283, 76, 25);
		btn_confirm.setText("확인");
		
		btn_confirm.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				if(dialog_kind==0)  //정보 입력 안되어있으면
					saveNewRegister();
				else
					modifyRegister();
			}
				
		});
		
		Button btn_cancle = new Button(composite, SWT.NONE);
		btn_cancle.setText("취소");
		btn_cancle.setBounds(346, 283, 76, 25);
		
		Button btn_duplication = new Button(composite, SWT.NONE);
		btn_duplication.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(dialog_kind==0)
					check_duplication_No(0); //중복의 경우
				else
					check_duplication_No(1);
			}
		});
		btn_duplication.setText("중복체크");
		btn_duplication.setBounds(346, 10, 76, 25);
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(87, 10, 239, 21);
		
		
	}

	protected void modifyRegister() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			
			switch(DataUtil.command.modifyRegister(txt_no.getText(), txt_title.getText(), txt_genre.getText(),txt_writer.getText()))
			{						
				case 0 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Failure!");
					messageBox.setMessage("실패");
					messageBox.open();
					break;
				case 1 :
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
				
				if(!isNoCheck)
				{
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Notify!");
					messageBox.setMessage("이미 있는 번호다");
					messageBox.open();
					return;
				}
				
				switch(DataUtil.command.saveNewRegister(txt_no.getText(), txt_title.getText(), txt_genre.getText(),txt_writer.getText()))
				{
					case 0 :
						messageBox = new MessageBox(shell, SWT.OPEN);
						messageBox.setText("Failure!");
						messageBox.setMessage("실패");
						messageBox.open();
						break;
					case 1 :
						messageBox = new MessageBox(shell, SWT.OPEN);
						messageBox.setText("Success!");
						messageBox.setMessage("성공");
						messageBox.open();
						shell.close();
						break;
				}
			
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
	}

	protected void check_duplication_No(int kind) {
		MessageBox messageBox=null;
		
		try {
			switch(DataUtil.command.checkDuplication(txt_no.getText(),kind)){
			
				case -1:
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Error!");
					messageBox.setMessage("실패");
					messageBox.open();
					break;
				case 0 : 
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Error");
					messageBox.setMessage("중복.");
					messageBox.open();
					break;
				case 1 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Success!");
					messageBox.setMessage("성공");
					messageBox.open();
					isNoCheck = true;
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
