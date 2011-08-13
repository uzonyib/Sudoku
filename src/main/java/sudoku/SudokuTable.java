package sudoku;

public class SudokuTable {
	
	private short size;
	private short blockSize;
	
	private short[][] table;
	private short[][][] possibilities;
	
	private short nextX;
	private short nextY;
	private short nextValue;
	
	public SudokuTable(short[][] table) {
		if (table == null || table.length == 0 || table.length != table[0].length) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.size = (short) table.length;
		this.blockSize = (short) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		initPossibilities();
		this.table = new short[size][size];
		for (short i = 0; i < size; ++i) {
			for (short j = 0; j < size; ++j) {
				this.table[i][j] = table[i][j];
			}
		}
		
		try {
			checkIntegrity();
		} catch (IllegalStateException e) {
			throw new IllegalArgumentException("Integrity problem in argument.", e);
		}
		
		for (short i = 0; i < size; ++i) {
			for (short j = 0; j < size; ++j) {
				if (this.table[i][j] > 0) {
					eliminatePossibilitiesForCell(i, j);
				}
			}
		}
	}

	private void checkIntegrity() {
		checkRowsIntegrity();
		checkColumnsIntegrity();
		checkBlocksIntegrity();
	}

	private void checkRowsIntegrity() {
		for (short i = 0; i < size; ++i) {
			short[] cardinality = new short[size];
			for (short j = 0; j < size; ++j) {
				if (cardinality[table[i][j] - 1] > 0) {
					throw new IllegalStateException("Integrity problem in row #" + (i + 1) + ".");
				}
				++cardinality[table[i][j] - 1];
			}
		}
	}
	
	private void checkColumnsIntegrity() {
		for (short i = 0; i < size; ++i) {
			short[] cardinality = new short[size];
			for (short j = 0; j < size; ++j) {
				if (cardinality[table[j][i] - 1] > 0) {
					throw new IllegalStateException("Integrity problem in column #" + (i + 1) + ".");
				}
				++cardinality[table[j][i] - 1];
			}
		}
	}
	
	private void checkBlocksIntegrity() {
		for (short blockX = 0; blockX < blockSize; ++blockX) {
			for (short blockY = 0; blockY < blockSize; ++blockY) {
				short[] cardinality = new short[size];
				for (short i = 0; i < blockSize; ++i) {
					short x = (short) (blockX * blockSize + i);
					for (short j = 0; j < blockSize; ++j) {
						short y = (short) (blockY * blockSize + j);
						if (cardinality[table[x][y] - 1] > 0) {
							throw new IllegalStateException("Integrity problem in block ("
									+ (x + 1) + "," + (y + 1) + ").");
						}
						++cardinality[table[x][y] - 1];
					}
				}
			}
		}
	}

	private void initPossibilities() {
		this.possibilities = new short[size][size][size];
		for (short i = 0; i < size; ++i) {
			for (short j = 0; j < size; ++j) {
				for (short k = 0; k < size; ++ k) {
					possibilities[i][j][k] = (short) (k + 1);
				}
			}
		}
	}
	
	private void eliminatePossibilitiesForCell(short i, short j) {
		eliminatePossibilitiesInRow(i, j);
		eliminatePossibilitiesInColumn(j, i);
		eliminatePossibilitiesInBlock(i, j);
	}
	
	private void eliminatePossibilitiesInRow(short i, short j) {
		short value = (short) (table[i][j] - 1);
		for (short k = 0; k < size; ++k) {
			if (k == j) {
				continue;
			}
			this.possibilities[i][k][value] = 0;
		}
	}
	
	private void eliminatePossibilitiesInColumn(short i, short j) {
		short value = (short) (table[i][j] - 1);
		for (short k = 0; k < size; ++k) {
			if (k == i) {
				continue;
			}
			possibilities[k][j][value] = 0;
		}
	}
	
	private void eliminatePossibilitiesInBlock(short i, short j) {
		short value = (short) (table[i][j] - 1);
		short blockX = (short) (i / blockSize);
		short blockY = (short) (j / blockSize);
		
		for (short k = 0; k < blockSize; ++k) {
			short x = (short) (blockX * blockSize + k);
			for (short l = 0; l < blockSize; ++l) {
				short y = (short) (blockY * blockSize + l);
				if (x == i && y == j) {
					continue;
				}
				possibilities[x][y][value] = 0;
			}
		}
	}
	
	private void findNextForLastCell(short i, short j) {
		
	}
	
	private boolean hasSinglePossibility(short i, short j) {
		short nonZeroCount = 0;
		for (short k = 0; k < size; ++k) {
			if (possibilities[i][j][k] != 0) {
				++nonZeroCount;
				nextValue = possibilities[i][j][k];
				if (nonZeroCount > 1) {
					nextValue = 0;
					return false;
				}
			}
		}
		return nonZeroCount == 1;
	}
	
	private void findNextInRow(short i) {
		for (short k = 0; k < size; ++k) {
			if (hasSinglePossibility(i, k)) {
				nextX = i;
				nextY = k;
				return;
			}
		}
	}
	
	private void findNextInColumn(short i) {
		for (short k = 0; k < size; ++k) {
			if (hasSinglePossibility(k, i)) {
				nextX = k;
				nextY = i;
				return;
			}
		}
	}

	public int getSize() {
		return size;
	}

}
