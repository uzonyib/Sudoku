package sudoku.core.drools;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sudoku.core.Field;
import sudoku.core.FieldValue;

public class DroolsField extends Field {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("DroolsLogger");
	
	private Set<FieldValue> candidates = new HashSet<FieldValue>();
	
	private PropertyChangeSupport support = new PropertyChangeSupport(this);
	
	public DroolsField(int rowIndex, int columnIndex, Integer value, int size, int blockSize) {
		super(rowIndex, columnIndex, value, size, blockSize);
		if (this.value == null) {
			for (int i = 1; i <= size; ++i) {
				this.candidates.add(FieldValue.valueOf(i));
			}
		}
	}
	
	@Override
	public void setValue(FieldValue fieldValue) {
		FieldValue oldValue = getValue();
		super.setValue(fieldValue);
		support.firePropertyChange("value", oldValue, this.getValue());
	}
	
	public FieldValue getSingleCandidate() {
		return this.candidates.size() == 1 ? this.candidates.iterator().next() : null;
	}
	
	public boolean hasCandidate(FieldValue fieldValue) {
		return this.candidates.contains(fieldValue);
	}
	
	public void deleteCandidate(FieldValue fieldValue, Field trigger) {
		this.candidates.remove(fieldValue);
		LOGGER.debug(fieldValue + " may not appear in " + this
				+ ", because it is present in " + trigger);
	}
	
	public int getCandidateCount() {
		return this.candidates.size();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		support.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		support.removePropertyChangeListener(l);
	}

}
