package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;

public class LoginDialog extends Dialog {  //Dialog에서 상속받음

	protected Object result;
	protected Shell shlLogin;
	private Text txt_id;
	private Text txt_password;
	private Button btn_login;
	private Button btn_registor;
	private Button btn_logout;
	private Button btn_modify;	
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public LoginDialog(Shell parent, int style) {
		super(parent, style);
		setText("Login Dialog");
		// TODO Auto-generated constructor stub
	}
	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shlLogin.open();
		shlLogin.layout();
		Display display=getParent().getDisplay();
		while (!shlLogin.isDisposed()) {  //이벤트 루프 시작
			if (!display.readAndDispatch()) {
				display.sleep(); ////이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}
	private void createContents() {
		// TODO Auto-generated method stub
		shlLogin = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlLogin.setSize(316, 128);
		shlLogin.setText("LogIn");
		shlLogin.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlLogin, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		CLabel lblNewLabel = new CLabel(composite, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(10, 10, 62, 21);
		lblNewLabel.setText("아이디");
		
		txt_id = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_id.setBounds(78, 10, 194, 21);
		
		CLabel label = new CLabel(composite, SWT.NONE);
		label.setText("비밀번호");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(10, 37, 62, 21);
		
		txt_password = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_password.setBounds(78, 37, 194, 21);
		
		Button btn_registor = new Button(composite, SWT.NONE);
		btn_registor.setBounds(216, 64, 76, 25);
		btn_registor.setText("회원가입");
		
		btn_login = new Button(composite, SWT.NONE);
		btn_login.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkLogin(); 
			}
		});
		btn_login.setBounds(134, 64, 76, 25);
		btn_login.setText("로그인");
		
		btn_registor=new Button(composite,SWT.NONE);
		btn_registor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				popUp();
			}
			
			
		});
			
			
		
	}
	
	protected void popUp() {
		// TODO Auto-generated method stub
		
	}
	protected void checkLogin() {
		// TODO Auto-generated method stub
		MessageBox messageBox=null;
		try {
			int returnVal;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
