package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import control.command;
import control.dataUtil;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();  //display 객체생성 
		Shell shell = new Shell(display); //shell(실행 창)
		dataUtil.command = new command();
		LogIn_Dialog dialog = new LogIn_Dialog(shell, SWT.OPEN);  //LogIn_Dialog클래스의 dialog 변수 선언. 
		dialog.open();
	}

}
