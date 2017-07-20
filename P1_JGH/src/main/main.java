package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class main {

	public static void main(String[] args) {		
		// TODO Auto-generated method stub

		Display display = new Display();
		Shell shell = new Shell(display); 
		
		Person_input person_input = new Person_input(shell, SWT.OPEN);  //Person_input 클래스의 person_input 변수선언 
		person_input.open();
	}
}
