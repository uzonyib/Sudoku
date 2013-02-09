package sudoku.core.drools;

import sudoku.core.AbstractTable;
import sudoku.core.Table;

public class DroolsTable extends AbstractTable {
	
	private int size;
	private int blockSize;
	
	private DroolsField[][] fields;
	
	public DroolsTable(int size) {
		if (size <= 0) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.size = size;
		this.blockSize = (int) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.fields = new DroolsField[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				this.fields[i][j] = new DroolsField(i, j, null, size, blockSize);
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
		
		this.fields = new DroolsField[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (table[i][j] < 0 || table[i][j] > getSize()) {
					throw new IllegalArgumentException("Invalid value: " + table[i][j] + ".");
				}
				this.fields[i][j] = new DroolsField(i, j, table[i][j], size, blockSize);
			}
		}	
	}
	
	public DroolsTable(Table table) {
		if (table == null) {
			throw new IllegalArgumentException("Invalid source table.");
		}
		
		this.size = table.getSize();
		this.blockSize = table.getBlockSize();
		
		this.fields = new DroolsField[size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				Integer value = table.get(i, j).hasValue() ? table.get(i, j).getValue().getIndex() : null;
				this.fields[i][j] = new DroolsField(i, j, value, size, blockSize);
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
	public DroolsField get(int x, int y) {
		return fields[x][y];
	}

}
