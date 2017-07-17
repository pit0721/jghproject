package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import control.dataUtil;
import db.account;
import dialog.New_Register_Dialog;

public class LogIn_Dialog extends Dialog {  //Dialog에서 상속받음

	protected Object result;
	protected Shell shlLogin;
	private Text txt_id;
	private Text txt_password;
	private Button btn_login;
	private Button btn_register;	
	private Button btn_logout;
	private Button btn_modify;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LogIn_Dialog(Shell parent, int style) {
		super(parent, style); //부모클래스의 Dialog에서 불러옴
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLogin.open();
		shlLogin.layout();
		Display display = getParent().getDisplay();
		while (!shlLogin.isDisposed()) {  //이벤트 루프 시작
			if (!display.readAndDispatch()) {
				display.sleep(); ////이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shlLogin = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlLogin.setSize(362, 128);
		shlLogin.setText("LogIn");
		shlLogin.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlLogin, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		CLabel lblNewLabel = new CLabel(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 10, 62, 21);
		lblNewLabel.setText("\uC544\uC774\uB514");
		
		txt_id = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_id.setBounds(78, 10, 194, 21);
		
		CLabel label = new CLabel(composite, SWT.NONE);
		label.setText("\uBE44\uBC00\uBC88\uD638");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 37, 62, 21);
		
		txt_password = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_password.setBounds(78, 37, 194, 21);
		
		btn_login = new Button(composite, SWT.NONE);
		btn_login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkLogin();
			}
		});
		btn_login.setBounds(276, 10, 66, 48);
		btn_login.setText("\uB85C\uADF8\uC778");
		
		btn_register = new Button(composite, SWT.NONE);
		btn_register.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				popUp();
			}
		});
		btn_register.setBounds(276, 64, 66, 25);
		btn_register.setText("\uD68C\uC6D0\uAC00\uC785");
		
		btn_logout = new Button(composite, SWT.NONE);
		btn_logout.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txt_id.setEnabled(true);
				txt_id.setText("");
				txt_password.setEnabled(true);
				txt_password.setText("");
				btn_login.setEnabled(true);
				btn_register.setEnabled(true);
				btn_logout.setVisible(false);
				btn_modify.setVisible(false);
			}
		});
		btn_logout.setBounds(10, 64, 76, 25);
		btn_logout.setText("\uB85C\uADF8\uC544\uC6C3");
		btn_logout.setVisible(false);
		
		btn_modify = new Button(composite, SWT.NONE);
		btn_modify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				popUpForModify();
			}
		});
		btn_modify.setVisible(false);
		btn_modify.setText("\uC815\uBCF4\uC218\uC815");
		btn_modify.setBounds(88, 64, 76, 25);

	}
	
	protected void popUpForModify() {
		// TODO Auto-generated method stub
		try {
			account account = dataUtil.command.getCurrentAccout();
			New_Register_Dialog new_Register_Dialog = new New_Register_Dialog(shlLogin, SWT.OPEN, account.getID(), account.getPASSWORD(), account.getUSER_NAME(), account.getUSER_PHONE(), account.getUSER_EMAIL());
			new_Register_Dialog.open();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

	protected void popUp() {
		// TODO Auto-generated method stub
		try {
			New_Register_Dialog new_Register_Dialog = new New_Register_Dialog(shlLogin, SWT.OPEN);
			new_Register_Dialog.open();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	protected void checkLogin() {
		// TODO Auto-generated method stub
		MessageBox messageBox = null;
		try {
			int returnVal = dataUtil.command.checkLogin(txt_id.getText(), txt_password.getText());
			switch(returnVal)
			{
				case -1:
					messageBox = new MessageBox(shlLogin, SWT.OPEN);
					messageBox.setText("Error!");
					messageBox.setMessage("�ý��� ����");
					messageBox.open();
					break;
				case 0:
					messageBox = new MessageBox(shlLogin, SWT.OPEN);
					messageBox.setText("Error");
					messageBox.setMessage("���̵� Ȯ���� �ּ���");
					messageBox.open();
					break;
				case 1:
					messageBox = new MessageBox(shlLogin, SWT.OPEN);
					messageBox.setText("Success");
					messageBox.setMessage("�α��� ����");
					messageBox.open();					
					
					// �α��� ��Ȳ ǥ��
					txt_id.setEnabled(false);
					txt_password.setEnabled(false);
					btn_login.setEnabled(false);
					btn_register.setEnabled(false);
					btn_logout.setVisible(true);
					btn_modify.setVisible(true);
					break;
				case 2:
					messageBox = new MessageBox(shlLogin, SWT.OPEN);
					messageBox.setText("Error");
					messageBox.setMessage("��й�ȣ�� Ȯ���� �ּ���");
					messageBox.open();
					break;
			}			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
