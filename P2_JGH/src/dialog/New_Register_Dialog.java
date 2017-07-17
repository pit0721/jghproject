package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.dataUtil;

public class New_Register_Dialog extends Dialog {	

	protected Object result;
	protected Shell shell;
	private Text txt_id;
	private Text txt_password;
	private Text txt_name;
	private Text txt_phone;
	private Text txt_email;
	private Button btn_duplication;
	private Button btn_ok;
	private Button btn_cancel;
	
	private boolean isIdCheck = false;
	private int dialog_kind = 0;
	
	private String _id;
	private String _password;
	private String _name;
	private String _phone;
	private String _email;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public New_Register_Dialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		dialog_kind = 0;
	}
	
	public New_Register_Dialog(Shell parent, int style, String id, String password, String name, String phone, String email) {
		super(parent, style);
		setText("SWT Dialog");
		_id = id;
		_password = password;
		_name = name;
		_phone = phone;
		_email = email;
		dialog_kind = 1;
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		if(dialog_kind == 1)
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

	private void init() {
		// TODO Auto-generated method stub
		txt_id.setText(_id);
		txt_id.setEnabled(false);
		btn_duplication.setEnabled(false);
		txt_password.setText(_password);
		txt_name.setText(_name);
		txt_phone.setText(_phone);
		txt_email.setText(_email);
		
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(346, 230);
		shell.setText("\uD68C\uC6D0\uAC00\uC785");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		CLabel lblNewLabel = new CLabel(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 12, 62, 21);
		lblNewLabel.setText("\uC544\uC774\uB514");
		
		txt_id = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_id.setBounds(78, 12, 164, 21);
		
		btn_duplication = new Button(composite, SWT.NONE);
		btn_duplication.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(dialog_kind == 0)
					check_duplication_Id(0);
				else
					check_duplication_Id(1);
				
			}
		});
		btn_duplication.setBounds(248, 10, 76, 25);
		btn_duplication.setText("\uC911\uBCF5\uCCB4\uD06C");
		
		CLabel label = new CLabel(composite, SWT.NONE);
		label.setText("\uBE44\uBC00\uBC88\uD638");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 39, 62, 21);
		
		txt_password = new Text(composite, SWT.BORDER | SWT.PASSWORD | SWT.CENTER);
		txt_password.setBounds(78, 39, 164, 21);
		
		CLabel label_1 = new CLabel(composite, SWT.NONE);
		label_1.setText("\uC774\uB984");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(10, 66, 62, 21);
		
		txt_name = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_name.setBounds(78, 66, 164, 21);
		
		CLabel label_2 = new CLabel(composite, SWT.NONE);
		label_2.setText("\uC5F0\uB77D\uCC98");
		label_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_2.setBounds(10, 93, 62, 21);
		
		txt_phone = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_phone.setBounds(78, 93, 164, 21);
		
		CLabel label_3 = new CLabel(composite, SWT.NONE);
		label_3.setText("\uC774\uBA54\uC77C");
		label_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_3.setBounds(10, 120, 62, 21);
		
		txt_email = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_email.setBounds(78, 120, 164, 21);
		
		btn_cancel = new Button(composite, SWT.NONE);
		btn_cancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btn_cancel.setBounds(254, 165, 76, 25);
		btn_cancel.setText("\uCDE8\uC18C");
		
		btn_ok = new Button(composite, SWT.NONE);
		btn_ok.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(dialog_kind == 0)
					saveNewRegister();
				else
					modiftRegister();
			}
		});
		btn_ok.setText("\uD655\uC778");
		btn_ok.setBounds(172, 165, 76, 25);
		
		Label label_4 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_4.setBounds(0, 155, 400, 2);
		composite.setTabList(new Control[]{txt_id, btn_duplication, txt_password, txt_name, txt_phone, txt_email, btn_ok, lblNewLabel, label_3, label, label_2, btn_cancel, label_1});
	}

	protected void modiftRegister() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			
			switch(dataUtil.command.modifyRegister(txt_id.getText(), txt_password.getText(), txt_name.getText(), txt_phone.getText(), txt_email.getText()))
			{						
				case 0 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Failure!");
					messageBox.setMessage("정보 수정 실패");
					messageBox.open();
					break;
				case 1 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Success!");
					messageBox.setMessage("정보 수정 성공");
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
			
			if(!isIdCheck)
			{
				messageBox = new MessageBox(shell, SWT.OPEN);
				messageBox.setText("Notify!");
				messageBox.setMessage("아이디 중복체크를 해주세요");
				messageBox.open();
				return;
			}
			
			switch(dataUtil.command.saveNewRegister(txt_id.getText(), txt_password.getText(), txt_name.getText(), txt_phone.getText(), txt_email.getText()))
			{
				case 0 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Failure!");
					messageBox.setMessage("새로운 계정 등록 실패");
					messageBox.open();
					break;
				case 1 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Success!");
					messageBox.setMessage("새로운 계정 등록 성공");
					messageBox.open();
					shell.close();
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	protected void check_duplication_Id(int kind) {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			switch(dataUtil.command.checkDuplicationId(txt_id.getText(), kind))
			{
				case -1 :	
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Error!");
					messageBox.setMessage("시스템 오류");
					messageBox.open();
					break;
				case 0 : 
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Error!");
					messageBox.setMessage("사용 불가능한 아이디 입니다.");
					messageBox.open();
					break;
				case 1 :
					messageBox = new MessageBox(shell, SWT.OPEN);
					messageBox.setText("Error!");
					messageBox.setMessage("사용 가능한 아이디 입니다.");
					messageBox.open();
					isIdCheck = true;
					break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}	
}
