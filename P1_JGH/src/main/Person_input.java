package main;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;

public class Person_input extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text txt_name;
	private Text txt_address;
	private Text txt_age;
	private Text txt_sex;
	private Text txt_phone;
	private Text txt_email;
	private Table tbl_list;
	private Button btn_register;
	
	private ArrayList<Person> persons = new ArrayList<Person>();  //ArrayList로 persons배열 선언

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public Person_input(Shell parent, int style) {
		super(parent, style); //부모클래스 호출. main의 dialog
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {  //이벤트 루프를 시작
			if (!display.readAndDispatch()) {
				display.sleep(); //이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM);
		shell.setSize(601, 399);
		shell.setText("\uC0AC\uC6A9\uC790 \uC785\uB825");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.BORDER);
		
		txt_name = new Text(composite, SWT.BORDER);
		txt_name.setBounds(106, 6, 73, 21);
		
		CLabel lblNewLabel = new CLabel(composite, SWT.NONE);
		lblNewLabel.setBounds(20, 6, 62, 21);
		lblNewLabel.setText("\uC774\uB984");
		
		CLabel lblWnth = new CLabel(composite, SWT.NONE);
		lblWnth.setText("\uC8FC\uC18C");
		lblWnth.setBounds(20, 33, 62, 21);
		
		txt_address = new Text(composite, SWT.BORDER);
		txt_address.setBounds(106, 33, 459, 21);
		
		CLabel label_1 = new CLabel(composite, SWT.NONE);
		label_1.setText("\uB098\uC774");
		label_1.setBounds(270, 6, 30, 21);
		
		txt_age = new Text(composite, SWT.BORDER);
		txt_age.setBounds(304, 6, 73, 21);
		
		CLabel label_2 = new CLabel(composite, SWT.NONE);
		label_2.setText("\uC131\uBCC4");
		label_2.setBounds(458, 6, 26, 21);
		
		txt_sex = new Text(composite, SWT.BORDER);
		txt_sex.setBounds(492, 6, 73, 21);
		
		CLabel label_3 = new CLabel(composite, SWT.NONE);
		label_3.setText("\uC5F0\uB77D\uCC98");
		label_3.setBounds(20, 60, 62, 21);
		
		txt_phone = new Text(composite, SWT.BORDER);
		txt_phone.setBounds(106, 60, 176, 21);
		
		txt_email = new Text(composite, SWT.BORDER);
		txt_email.setBounds(389, 60, 176, 21);
		
		CLabel label_4 = new CLabel(composite, SWT.NONE);
		label_4.setText("\uC774\uBA54\uC77C");
		label_4.setBounds(341, 60, 42, 21);
		
		tbl_list = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tbl_list.setBounds(0, 121, 591, 248);
		tbl_list.setHeaderVisible(true);
		tbl_list.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn.setWidth(80);
		tblclmnNewColumn.setText("\uC774\uB984");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn_1.setWidth(40);
		tblclmnNewColumn_1.setText("\uB098\uC774");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn_2.setWidth(40);
		tblclmnNewColumn_2.setText("\uC131\uBCC4");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn_3.setWidth(210);
		tblclmnNewColumn_3.setText("\uC8FC\uC18C");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn_4.setWidth(100);
		tblclmnNewColumn_4.setText("\uC5F0\uB77D\uCC98");
		
		TableColumn tblclmnNewColumn_5 = new TableColumn(tbl_list, SWT.NONE);
		tblclmnNewColumn_5.setWidth(100);
		tblclmnNewColumn_5.setText("\uC774\uBCA0\uC77C");
		
		btn_register = new Button(composite, SWT.NONE);
		btn_register.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setRegister();
			}
		});
		btn_register.setBounds(489, 90, 76, 25);
		btn_register.setText("\uB4F1\uB85D");
		composite.setTabList(new Control[]{txt_name, txt_age, txt_sex, txt_address, txt_phone, txt_email, btn_register, lblNewLabel, lblWnth, label_1, label_2, label_3, label_4, tbl_list});

	}

	protected void setRegister() {
		// TODO Auto-generated method stub		
		try {
			if((txt_name.getText().isEmpty() &&
			    txt_age.getText().isEmpty() && 
			    txt_sex.getText().isEmpty() &&
			    txt_address.getText().isEmpty() &&
			    txt_phone.getText().isEmpty() &&
			    txt_email.getText().isEmpty()))
			{
				throw new Exception(""); //값이 비었을 때의 예외처리
			}
			else
			{	
				Person person = new Person(txt_name.getText(),
										   Integer.parseInt(txt_age.getText()),
										   txt_sex.getText(),
										   txt_address.getText(),
										   txt_phone.getText(),
										   txt_email.getText());
				persons.add(person);
				
				
				
				updateTable();
				clearTxt();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println(e.getMessage());
		}
	}

	private void clearTxt() {
		// TODO Auto-generated method stub
		txt_name.setText("");
		txt_age.setText("");
		txt_sex.setText("");
		txt_address.setText("");
		txt_phone.setText("");
		txt_email.setText("");
	}

	private void updateTable() {
		// TODO Auto-generated method stub
		tbl_list.removeAll(); 
		for (Person p : persons) { //persons 배열에 들어있는 값들을 하나씩 p 변수에 대입
			TableItem item = new TableItem(tbl_list, SWT.INSERT);
			item.setText(new String[]{p.getName(), String.valueOf(p.getAge()), p.getSex(), p.getAddress(), p.getPhone(), p.getEmail()});
		}
		
	}
	
	
	
}
