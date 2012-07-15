package sudoku.core.hint;

public class Step {
	
	private final int rowIndex;
	private final int columnIndex;
	private final int value;

	public Step(int rowIndex, int columnIndex, int value) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = value;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public int getValue() {
		return value;
	}

}
