package sudoku;

public class SudokuTable {
	
	private int size;
	private int blockSize;
	
	private int[][] table;
	
	public SudokuTable(int[][] table) {
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
				this.table[i][j] = table[i][j] - 1;
			}
		}
		
		try {
			checkIntegrity();
		} catch (IllegalStateException e) {
			throw new IllegalArgumentException("Integrity problem in argument.", e);
		}
	}
	
	public int getSize() {
		return size;
	}

	public int getBlockSize() {
		return blockSize;
	}
	
	public int get(int x, int y) {
		return table[x][y];
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
		System.out.println(builder.toString());
		return builder.toString();
	}

	private void checkIntegrity() {
		checkRowsIntegrity();
		checkColumnsIntegrity();
		checkBlocksIntegrity();
	}

	private void checkRowsIntegrity() {
		for (int rowIndex = 0; rowIndex < size; ++rowIndex) {
			checkRowIntegrity(rowIndex);
		}
	}
	
	private void checkRowIntegrity(int rowIndex) {
		int[] cardinality = new int[size];
		for (int columnIndex = 0; columnIndex < size; ++columnIndex) {
			if (table[rowIndex][columnIndex] < 0) {
				continue;
			}
			if (cardinality[table[rowIndex][columnIndex]] > 0) {
				throw new IllegalStateException("Integrity problem in row #" + (rowIndex + 1) + ".");
			}
			++cardinality[table[rowIndex][columnIndex]];
		}
	}
	
	private void checkColumnsIntegrity() {
		for (int columnIndex = 0; columnIndex < size; ++columnIndex) {
			checkColumnIntegrity(columnIndex);
		}
	}
	
	private void checkColumnIntegrity(int columnIndex) {
		int[] cardinality = new int[size];
		for (int rowIndex = 0; rowIndex < size; ++rowIndex) {
			if (table[rowIndex][columnIndex] < 0) {
				continue;
			}
			if (cardinality[table[rowIndex][columnIndex]] > 0) {
				throw new IllegalStateException("Integrity problem in column #" + (columnIndex + 1) + ".");
			}
			++cardinality[table[rowIndex][columnIndex]];
		}
	}
	
	private void checkBlocksIntegrity() {
		for (int blockRowIndex = 0; blockRowIndex < blockSize; ++blockRowIndex) {
			for (int blockColumnIndex = 0; blockColumnIndex < blockSize; ++blockColumnIndex) {
				checkBlockIntegrity(blockRowIndex, blockColumnIndex);
			}
		}
	}
	
	private void checkBlockIntegrity(int blockRowIndex, int blockColumnIndex) {
		int[] cardinality = new int[size];
		for (int relRowIndex = 0; relRowIndex < blockSize; ++relRowIndex) {
			int absRowIndex = blockRowIndex * blockSize + relRowIndex;
			for (int relColumnIndex = 0; relColumnIndex < blockSize; ++relColumnIndex) {
				int absColumnIndex = blockColumnIndex * blockSize + relColumnIndex;
				if (table[absRowIndex][absColumnIndex] < 0) {
					continue;
				}
				if (cardinality[table[absRowIndex][absColumnIndex]] > 0) {
					throw new IllegalStateException("Integrity problem in block ("
							+ (absRowIndex + 1) + "," + (absColumnIndex + 1) + ").");
				}
				++cardinality[table[absRowIndex][absColumnIndex]];
			}
		}
	}

}
