package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import control.command;
import control.dataUtil;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell shell = new Shell(display);
		dataUtil.command = new command();
		LogIn_Dialog dialog = new LogIn_Dialog(shell, SWT.OPEN);
		dialog.open();
	}

}
