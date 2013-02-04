package sudoku.core.drools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sudoku.core.Step;
import sudoku.core.Table;

public class DroolsTable implements Table {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DroolsTable.class);
	
	private int size;
	private int blockSize;
	
	private Field[][] fields;
	
	public DroolsTable(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.size = size;
		this.blockSize = (int) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.fields = new Field[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				this.fields[i][j] = new Field(i, j, null, size, blockSize);
			}
		}
	}
	
	public DroolsTable(int[][] table) {
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
		
		this.fields = new Field[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (table[i][j] < -1 || table[i][j] > getSize()) {
					throw new IllegalArgumentException("Invalid value: " + table[i][j] + ".");
				}
				this.fields[i][j] = new Field(i, j, table[i][j], size, blockSize);
			}
		}	
	}
	
	public DroolsTable(Table table) {
		if (table == null) {
			throw new IllegalArgumentException("Invalid source table.");
		}
		
		this.size = table.getSize();
		this.blockSize = table.getBlockSize();
		
		this.fields = new Field[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				this.fields[i][j] = new Field(i, j, table.get(i, j), size, blockSize);
			}
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
	public Integer get(int x, int y) {
		return fields[x][y].getValue();
	}
	
	@Override
	public String getElement(int rowIndex, int columnIndex) {
		return "" + get(rowIndex, columnIndex);
	}
	
	@Override
	public void set(int rowIndex, int columnIndex, int value) {
		if (rowIndex < 0 || rowIndex >= getSize()) {
			throw new IllegalArgumentException("Row index out of range.");
		}
		if (columnIndex < 0 || columnIndex >= getSize()) {
			throw new IllegalArgumentException("Column index out of range.");
		}
		if (value < 0 || value >= getSize()) {
			throw new IllegalArgumentException("Value out of range.");
		}
		
		fields[rowIndex][columnIndex].setValue(value);
	}
	
	@Override
	public void takeStep(Step step) {
		if (step == null) {
			throw new IllegalArgumentException("Step is null.");
		}
		set(step.getRowIndex(), step.getColumnIndex(), step.getValue());
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
				Integer value = get(i, j);
				builder.append(" ").append(value != null && value >= 0 ? value + 1 : ".");
			}
			builder.append(" ").append(columnSeparator).append("\n");
		}
		builder.append(separatorLine);
		return builder.toString();
	}
	
	public Field getField(int rowIndex, int columnIndex) {
		return this.fields[rowIndex][columnIndex];
	}

}
