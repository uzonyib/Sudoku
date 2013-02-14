package sudoku.core.drools;

import sudoku.core.FieldValue;

public class Candidate {
	
	private DroolsField field;
	private FieldValue value;
	
	public Candidate(DroolsField field, FieldValue value) {
		if (field == null || value == null) {
			throw new IllegalArgumentException();
		}
		this.field = field;
		this.value = value;
	}
	
	public DroolsField getField() {
		return field;
	}
	
	public FieldValue getValue() {
		return value;
	}

}
