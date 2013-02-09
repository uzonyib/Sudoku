package sudoku.core;

public class Field {
	
	protected final int rowIndex;
	protected final int columnIndex;
	protected final int blockIndex;
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
		this.value = FieldValue.valueOf(value);
	}
	
	public void setValue(FieldValue fieldValue) {
		this.value = fieldValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Field)) {
			return false;
		}
		Field f = (Field) obj;
		return this.rowIndex == f.rowIndex && this.columnIndex == f.columnIndex;
	}
	
	@Override
	public int hashCode() {
		return this.rowIndex + 31 * this.columnIndex;
	}
	
	@Override
	public String toString() {
		return "[" + this.rowIndex + "," + this.columnIndex + "]";
	}

}
