package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;




public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();  //GUI 작업을 위한 Display 객체를 생성
		Shell shell = new Shell(display);  //할당 초기화
		shell.setSize(399, 233);
		LoginDialog dialog = new LoginDialog(shell, SWT.OPEN);
		dialog.open();
		
	}
}

