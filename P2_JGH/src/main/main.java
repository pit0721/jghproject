package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import control.command;
import control.dataUtil;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();  //GUI 작업을 위한 Display 객체를 생성
		Shell shell = new Shell(display);  //할당 초기화
		dataUtil.command = new command();
		LogIn_Dialog dialog = new LogIn_Dialog(shell, SWT.OPEN);  //LogIn_Dialog클래스의 dialog 변수 선언. 
		dialog.open();
	}

}
