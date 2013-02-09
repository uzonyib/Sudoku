package sudoku.core;

public class Step {
	
	private final Field field;
	private final FieldValue value;

	public Step(Field field, FieldValue value) {
		if (field == null || value == null) {
			throw new IllegalStateException();
		}
		this.field = field;
		this.value = value;
	}
	
	public Step(Field field, int index) {
		this(field, FieldValue.valueOf(index));
	}
	
	public int getRowIndex() {
		return this.field.getRowIndex();
	}
	
	public int getColumnIndex() {
		return this.field.getColumnIndex();
	}
	
	public FieldValue getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "Set " + value + " in [" + (getRowIndex() + 1) + "," + (getColumnIndex() + 1) + "]";
	}

}
