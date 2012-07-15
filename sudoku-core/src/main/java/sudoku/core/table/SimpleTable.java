package sudoku.core.table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sudoku.core.rule.IntegrityException;
import sudoku.core.rule.Rule;
import sudoku.core.table.Table;

public class SimpleTable implements Table {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTable.class);
	
	private int size;
	private int blockSize;
	
	private int[][] table;
	
	public SimpleTable(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.size = size;
		this.blockSize = (int) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.table = new int[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				this.table[i][j] = -1;
			}
		}
	}
	
	public SimpleTable(int[][] table) {
		if (table == null || table.length == 0) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		for (int i = 0; i < table.length; ++i) {
			if (table[i].length != table.length) {
				throw new IllegalArgumentException("Invalid table size.");
			}
		}
		
		this.size = table.length;
		this.blockSize = (int) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.table = new int[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (table[i][j] < -1 || table[i][j] > getSize()) {
					throw new IllegalArgumentException("Invalid value: " + table[i][j] + ".");
				}
				this.table[i][j] = table[i][j];
			}
		}
		
		if (!checkIntegrity()) {
			throw new IllegalArgumentException("Integrity error.");
		}
	}
	
	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int getBlockSize() {
		return blockSize;
	}
	
	@Override
	public int get(int x, int y) {
		return table[x][y];
	}

	@Override
	public boolean checkIntegrity() {
		for (Rule rule : Rule.values()) {
			try {
				rule.checkIntegrity(this);
			} catch (IntegrityException e) {
				LOGGER.info(e.getMessage(), e);
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void set(int rowIndex, int columnIndex, int value) {
		if (rowIndex < 0 || rowIndex >= getSize()) {
			throw new IllegalArgumentException("Row index out of range.");
		}
		if (columnIndex < 0 || columnIndex >= getSize()) {
			throw new IllegalArgumentException("Columnindex out of range.");
		}
		if (value < -1 || value >= getSize()) {
			throw new IllegalArgumentException("Value out of range.");
		}
		
		int originalValue = table[rowIndex][columnIndex];
		table[rowIndex][columnIndex] = value;
		
		if (!checkIntegrity()) {
			table[rowIndex][columnIndex] = originalValue;
			throw new IllegalArgumentException("Integrity error when setting value.");
		}
	}
	
	@Override
	public String getElement(int rowIndex, int columnIndex) {
		return "" + get(rowIndex, columnIndex);
	}
	
	@Override
	public String toString() {
		String separatorLine = "  - - -   - - -   - - -";
		String columnSeparator = "|";
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < size; ++i) {
			if (i % blockSize == 0) {
				builder.append(separatorLine).append("\n");
			}
			builder.append(columnSeparator);
			for (int j = 0; j < size; ++j) {
				if (j != 0 && j % blockSize == 0) {
					builder.append(" ").append(columnSeparator);
				}
				int value = get(i, j);
				builder.append(" ").append(value >= 0 ? value + 1 : ".");
			}
			builder.append(" ").append(columnSeparator).append("\n");
		}
		builder.append(separatorLine);
		return builder.toString();
	}

}
