package sudoku.core;

import java.util.Iterator;

public abstract class AbstractTable implements Table {
	
	@Override
	public Iterator<Field> iterator() {
		return new TableIterator(this);
	}
	
	@Override
	public void set(int rowIndex, int columnIndex, FieldValue value) {
		if (rowIndex < 0 || rowIndex >= getSize()) {
			throw new IllegalArgumentException("Row index out of range.");
		}
		if (columnIndex < 0 || columnIndex >= getSize()) {
			throw new IllegalArgumentException("Column index out of range.");
		}
		if (value.getIndex() > getSize()) {
			throw new IllegalArgumentException("Value out of range.");
		}
		
		this.get(rowIndex, columnIndex).setValue(value);
	}
	
	private String getSeparatorLine() {
		StringBuilder sb = new StringBuilder(" ");
		for (int i = 0; i < this.getBlockSize(); ++i) {
			for (int j = 0; j < this.getBlockSize(); ++j) {
				sb.append(" -");
			}
			sb.append("  ");
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		String separatorLine = this.getSeparatorLine();
		String columnSeparator = "|";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.getSize(); ++i) {
			if (i % this.getBlockSize() == 0) {
				builder.append(separatorLine).append("\n");
			}
			builder.append(columnSeparator);
			for (int j = 0; j < this.getSize(); ++j) {
				if (j != 0 && j % this.getBlockSize() == 0) {
					builder.append(" ").append(columnSeparator);
				}
				FieldValue value = get(i, j).getValue();
				builder.append(" ").append(value != null ? value.getDisplayValue() : ".");
			}
			builder.append(" ").append(columnSeparator).append("\n");
		}
		builder.append(separatorLine);
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Table)) {
			return false;
		}
		Table table = (Table) obj;
		if (table.getSize() != this.getSize()) {
			return false;
		}
		for (int i = 0; i < this.getSize(); ++i) {
			for (int j = 0; j < this.getSize(); ++j) {
				if (this.get(i, j).getValue() != table.get(i, j).getValue()) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		int hashCode = 17;
		for (Field field : this) {
			hashCode += 30 * field.hashCode();
		}
		return hashCode;
	}

}
