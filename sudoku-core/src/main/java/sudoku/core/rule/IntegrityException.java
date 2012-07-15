package sudoku.core.rule;

public class IntegrityException extends Exception {

	private static final long serialVersionUID = 1L;

	private int rowIndex;
	private int columnIndex;
	private int element;
	private Rule rule;

	public IntegrityException(int rowIndex, int columnIndex, int element,
			Rule rule) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.element = element;
		this.rule = rule;
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

	public Rule getRule() {
		return rule;
	}
	
	@Override
	public String getMessage() {
		StringBuilder sb = new StringBuilder();
		switch (rule) {
		case ROW_RULE:
			sb.append("Row");
			break;
		case COLUMN_RULE:
			sb.append("Column");
			break;
		default:
			sb.append("Block");
			break;
		}
		sb.append(" integrity error at (").append(rowIndex + 1).append(",")
				.append(columnIndex + 1).append("), value ").append(element + 1)
				.append(".");
		return sb.toString();
	}

}
