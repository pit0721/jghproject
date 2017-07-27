package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;

import control.Command;
import control.DataUtil;
import dialog.MainDialog;

public class Main extends Dialog  {

	private Shell shell;
	private Object result;
	
	public Main(Shell parent) {
		// TODO Auto-generated constructor stub
		this(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	}
	
	public Main(Shell parent, int style){
		super(parent, style);
	}
	
	public Object open(){
		
		createContents();
		shell.open();
		shell.layout();
		Display display=getParent().getDisplay();
		while (!shell.isDisposed()) {  //이벤트 루프 시작
			if (!display.readAndDispatch()) {
				display.sleep(); ////이벤트가 없으면 잠시 멈춘다
			}
		}
		return result;
	}

	private void createContents() {
		// TODO Auto-generated method stub
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(249, 149);
		shell.setText("도서정보");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Button btn_bookInfo = new Button(composite, SWT.NONE);
		btn_bookInfo.setBounds(26, 31, 76, 57);
		btn_bookInfo.setText("도서자료");
		
		btn_bookInfo.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				
				DataUtil.command.dialog = new MainDialog(shell, SWT.OPEN);
				DataUtil.command.dialog.open();
			}
		});
		
		Button btn_exit = new Button(composite, SWT.NONE);
		btn_exit.setBounds(139, 31, 76, 57);
		btn_exit.setText("종료");
		
		btn_exit.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				super.widgetSelected(e);
				shell.close();
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Display display = new Display();  //GUI 작업을 위한 Display 객체를 생성
		Shell shell = new Shell(display);  //할당 초기화
		shell.setSize(229, 183);
		
		DataUtil.command=new Command();
		DataUtil.command.dialog = new MainDialog(shell, SWT.OPEN);
		DataUtil.command.dialog.open();*/
		
		
		
		Display display = new Display();  //GUI 작업을 위한 Display 객체를 생성
		Shell shell = new Shell(display); 
		
		DataUtil.command=new Command();
		@SuppressWarnings("unused")
		Main main=new Main(shell);
		main.open();
	}
}


