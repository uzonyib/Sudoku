package sudoku.core;

public class Field {
	
	protected int rowIndex;
	protected int columnIndex;
	protected int blockIndex;
	protected FieldValue value;
	
	public Field(int rowIndex, int columnIndex, Integer value, int size, int blockSize) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.blockIndex = ((rowIndex / blockSize) * blockSize) + columnIndex / blockSize;
		if (value != null && value > 0) {
			this.setValue(value);
		}
	}
	
	public boolean hasValue() {
		return value != null;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public int getBlockIndex() {
		return blockIndex;
	}
	
	public FieldValue getValue() {
		return value;
	}
	
	private void setValue(int value) {
		setValue(FieldValue.valueOf(value));
	}
	
	public void setValue(FieldValue fieldValue) {
		this.value = fieldValue;
	}

}
