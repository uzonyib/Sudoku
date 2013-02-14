package sudoku.core.drools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import sudoku.core.Field;
import sudoku.core.FieldValue;

public class DroolsField extends Field {
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public DroolsField(int rowIndex, int columnIndex, Integer value, int size, int blockSize) {
		super(rowIndex, columnIndex, value, size, blockSize);
	}
	
	@Override
	public void setValue(FieldValue fieldValue) {
		FieldValue oldValue = getValue();
		super.setValue(fieldValue);
		support.firePropertyChange("value", oldValue, this.getValue());
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

}
