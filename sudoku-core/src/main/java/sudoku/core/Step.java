package sudoku.core;

public class Step {
	
	private final int rowIndex;
	private final int columnIndex;
	private final FieldValue value;

	public Step(int rowIndex, int columnIndex, FieldValue value) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = value;
	}
	
	public Step(int rowIndex, int columnIndex, int index) {
		this(rowIndex, columnIndex, FieldValue.valueOf(index));
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public FieldValue getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return "Set " + value + " in [" + (rowIndex + 1) + "," + (columnIndex + 1) + "]";
	}

}
