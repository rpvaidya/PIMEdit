package com.rpv.pim.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

import com.rpv.pim.JSONHelper;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class PIMEditor {

	protected Shell shell;
	private Text Name;
	private Text Number;
	private Text Address;
	private Text ValidFrom;
	private Text ValidTo;
	private Text Description;
	private Text WebAddress;
	private Text UserID;
	private Text Password;
	private String[] categories;
	private String[] tagsForCat;
	private Accounts accts;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PIMEditor window = new PIMEditor();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		categories = new JSONHelper().getCategories();
		shell = new Shell();
		shell.setSize(450, 418);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));

		Label lblCategory = new Label(shell, SWT.NONE);
		lblCategory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategory.setText("Category");

		Combo Category = new Combo(shell, SWT.NONE);
		Category.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Category.setItems(categories);
		Category.addSelectionListener(new CategoryListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Combo comboBox = (Combo) arg0.widget;
				String selectedItem = comboBox.getItem(comboBox.getSelectionIndex());
				// TODO Auto-generated method stub
				System.out.println(selectedItem);
				tagsForCat = new JSONHelper().getTagsForCategory(selectedItem);

			}

		}

		);

		Label lblBelongsTo = new Label(shell, SWT.NONE);
		lblBelongsTo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBelongsTo.setText("Tags");

		Combo Tags = new Combo(shell, SWT.NONE);
		Tags.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		Tags.addSelectionListener(new TagsListener() {
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}

			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}

		}

		);

		Tags.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				if (tagsForCat != null) {
					Tags.setItems(tagsForCat);
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				accts = new JSONHelper().getDetailsForTag(Tags.getText());
				if (accts.getName() != null) Name.setText(accts.getName()); 
				if (accts.getAddress() != null) Address.setText(accts.getAddress());
				if (accts.getDescription() != null) Description.setText(accts.getDescription());
				if (accts.getNumber() != null) Number.setText(accts.getNumber());
				if (accts.getPwd() != null) Password.setText(accts.getPwd());
				if (accts.getUid() != null) UserID.setText(accts.getUid());
				if(accts.getWeb_address() != null) WebAddress.setText(accts.getWeb_address());
 			}
		});

		Label lblName = new Label(shell, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblName.setText("Name");

		Name = new Text(shell, SWT.BORDER);
		Name.setText("  ");
		Name.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNumber = new Label(shell, SWT.NONE);
		lblNumber.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNumber.setText("Number");

		Number = new Text(shell, SWT.BORDER);
		Number.setText("  ");
		Number.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAddress.setText("Address");

		Address = new Text(shell, SWT.BORDER);
		Address.setText("  ");
		Address.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblValidFrom = new Label(shell, SWT.NONE);
		lblValidFrom.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValidFrom.setText("Valid From");

		ValidFrom = new Text(shell, SWT.BORDER);
		ValidFrom.setText("  ");
		ValidFrom.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblValidTo = new Label(shell, SWT.NONE);
		lblValidTo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValidTo.setText("Valid To");

		ValidTo = new Text(shell, SWT.BORDER);
		ValidTo.setText("  ");
		ValidTo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Description");

		Description = new Text(shell, SWT.BORDER);
		Description.setText("  ");
		Description.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblWebAddress = new Label(shell, SWT.NONE);
		lblWebAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblWebAddress.setText("Web Address");

		WebAddress = new Text(shell, SWT.BORDER);
		WebAddress.setText("  ");
		WebAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblUserId = new Label(shell, SWT.NONE);
		lblUserId.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUserId.setText("User ID");

		UserID = new Text(shell, SWT.BORDER);
		UserID.setText("  ");
		UserID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblPassword = new Label(shell, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password");

		Password = new Text(shell, SWT.BORDER);
		Password.setText("  ");
		Password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(shell, SWT.NONE);

		Button Save = new Button(shell, SWT.NONE);
		Save.setText("Save");
		Save.addListener(SWT.Selection, new SaveButtonListener() {
		      public void handleEvent(Event e) {
		        switch (e.type) {
		        case SWT.Selection:
		          System.out.println("Button pressed");
		          Accounts acct = new Accounts();
		          
		          acct.setAddress(Address.getText());
		          acct.setCategory(Category.getText());
		          acct.setDescription(Description.getText());
		          acct.setHash_key(Tags.getItem(Tags.getSelectionIndex()));
		          acct.setName(Name.getText());
		          acct.setNumber(Number.getText());
		          acct.setPwd(Password.getText());
		          acct.setUid(UserID.getText());
		          acct.setValid_from(ValidFrom.getText());
		          acct.setValid_to(ValidTo.getText());
		          acct.setWeb_address(WebAddress.getText());
		          acct.setBelongs(" ");
		          new JSONHelper().updateTagDetails(acct);
		          break;
		        }
		      }
		    });

	}
}
