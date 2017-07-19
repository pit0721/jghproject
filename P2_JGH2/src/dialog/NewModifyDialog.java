package dialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

public class NewModifyDialog extends Dialog {

	protected Object result;
	protected Shell shell;
	
	private int dialog_kind=0;
	
	public NewModifyDialog(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}
	
	public Object open(){
		createContents();		
		
		return result;
		
	}

	private void createContents() {
		// TODO Auto-generated method stub
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(346, 230);
		shell.setText("회원가입");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
	}

	
}
