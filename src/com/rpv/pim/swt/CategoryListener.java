package com.rpv.pim.swt;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.cocoa.SWTComboBox;
import org.eclipse.swt.widgets.Combo;

public class CategoryListener implements SelectionListener {

	@Override
	public void widgetDefaultSelected(SelectionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void widgetSelected(SelectionEvent arg0) {
		Combo comboBox = (Combo)arg0.widget;
		String selectedItem = comboBox.getItem(comboBox.getSelectionIndex());
		// TODO Auto-generated method stub
		System.out.println(selectedItem);
	}

}
