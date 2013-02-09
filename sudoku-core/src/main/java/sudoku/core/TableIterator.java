package sudoku.core;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TableIterator implements Iterator<Field> {
	
	private Table table;
	
	private int rowIndex;
	private int columnIndex;

	public TableIterator(Table table) {
		this.table = table;
		this.rowIndex = 0;
		this.columnIndex = 0;
	}

	@Override
	public boolean hasNext() {
		return rowIndex < table.getSize() && columnIndex < table.getSize();
	}

	@Override
	public Field next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Field next = table.get(rowIndex, columnIndex);
		if (columnIndex < table.getSize() - 1) {
			++columnIndex;
		} else {
			++rowIndex;
			columnIndex = 0;
		}
		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
