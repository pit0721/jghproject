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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class RegistorDialog extends Dialog {  //Dialog에서 상속받음

	protected Object result;
	protected Shell shlRegistor;
	private Text txt_name;
	private Text text_birth;
	private Text txt_phone;
	private Text txt_home;
	private Text btn_sex;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public RegistorDialog(Shell parent, int style) {
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
		shlRegistor.open();
		shlRegistor.layout();
		Display display=getParent().getDisplay();
		while (!shlRegistor.isDisposed()) {  //이벤트 루프 시작
			if (!display.readAndDispatch()) {
				display.sleep(); ////이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}
	private void createContents() {
		// TODO Auto-generated method stub
		shlRegistor = new Shell(getParent(), SWT.DIALOG_TRIM);
		shlRegistor.setSize(499, 248);
		shlRegistor.setText("등록");
		shlRegistor.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shlRegistor, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		CLabel lbl_name = new CLabel(composite, SWT.NONE);
		lbl_name.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_name.setBounds(10, 10, 62, 21);
		lbl_name.setText("이름");
		
		txt_name = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_name.setBounds(78, 10, 194, 21);
		
		CLabel lbl_home = new CLabel(composite, SWT.NONE);
		lbl_home.setText("주소");
		lbl_home.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_home.setBounds(10, 67, 62, 21);
		
		Label lbl_sex = new Label(composite, SWT.NONE);
		lbl_sex.setBounds(301, 10, 56, 15);
		lbl_sex.setText("성별");
		btn_sex.setBounds(367, 10, 73, 21);
		
		CLabel lbl_bitth = new CLabel(composite, SWT.NONE);
		lbl_bitth.setText("생년월일");
		lbl_bitth.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_bitth.setBounds(10, 40, 62, 21);
		
		text_birth = new Text(composite, SWT.BORDER | SWT.CENTER);
		text_birth.setBounds(78, 40, 194, 21);
		
		CLabel lbl_phone = new CLabel(composite, SWT.NONE);
		lbl_phone.setText("연락처");
		lbl_phone.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lbl_phone.setBounds(10, 94, 62, 21);
		
		txt_phone = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_phone.setBounds(78, 94, 194, 21);
		
		Button btn_registor = new Button(composite, SWT.NONE);
		btn_registor.setBounds(384, 149, 76, 25);
		btn_registor.setText("등록");
		
		btn_registor.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				checkRegistor();
			}
		});
		
		txt_home = new Text(composite, SWT.BORDER | SWT.CENTER);
		txt_home.setBounds(78, 67, 386, 21);
		
		btn_sex = new Text(composite, SWT.BORDER);
		btn_sex.setBounds(384, 10, 80, 21);
		
		Button btn_modify = new Button(composite, SWT.NONE);
		btn_modify.setText("수정");
		btn_modify.setVisible(false);
		btn_modify.setBounds(74, 149, 47, 15);
		
		btn_modify.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				modoify();
			}
			
		});		
		
		
		
	}
	
	protected void checkRegistor(){
		MessageBox messageBox=null;
		try {
			//int returnVal=DataUtil.command.checkRegistor;
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	protected void modoify(){
		
	}

}
