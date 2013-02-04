package sudoku.core;

public class IntegrityError {

	private int rowIndex;
	private int columnIndex;
	private int element;
	private Integrity integrity;

	public IntegrityError(int rowIndex, int columnIndex, int element,
			Integrity integrity) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.element = element;
		this.integrity = integrity;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public int getColumnIndex() {
		return columnIndex;
	}

	public int getElement() {
		return element;
	}

	public Integrity getIntegrity() {
		return integrity;
	}
	
	public String getMessage() {
		StringBuilder sb = new StringBuilder(integrity.getClass().getSimpleName());
		sb.append(" error at (").append(rowIndex + 1).append(",")
				.append(columnIndex + 1).append("), value ").append(element + 1)
				.append(".");
		return sb.toString();
	}

}
