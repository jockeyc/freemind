package com.mubu2;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JTextField;

public class HighLight implements Action {

	JTextField jTextField;
	public void setjTextField(JTextField jTextField) {
		this.jTextField = jTextField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {}

	@Override
	public Object getValue(String key) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void putValue(String key, Object value) {}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {}

	@Override
	public void setEnabled(boolean b) {}
}
