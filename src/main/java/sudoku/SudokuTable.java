package sudoku;

public class SudokuTable {
	
	private int size;
	private int blockSize;
	
	private int[][] table;
	private boolean[][][] possibilities;
	
	private int nextX;
	private int nextY;
	private int nextValue;
	
	public SudokuTable(int[][] table) {
		if (table == null || table.length == 0 || table.length != table[0].length) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		this.size = table.length;
		this.blockSize = (int) Math.sqrt(size);
		
		if (blockSize * blockSize != size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		initPossibilities();
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
		
//		for (int i = 0; i < size; ++i) {
//			for (int j = 0; j < size; ++j) {
//				if (this.table[i][j] > 0) {
//					eliminatePossibilitiesForCell(i, j);
//				}
//			}
//		}
	}

	private void checkIntegrity() {
		checkRowsIntegrity();
		checkColumnsIntegrity();
		checkBlocksIntegrity();
	}

	private void checkRowsIntegrity() {
		for (int i = 0; i < size; ++i) {
			int[] cardinality = new int[size];
			for (int j = 0; j < size; ++j) {
				if (table[i][j] < 0) {
					continue;
				}
				if (cardinality[table[i][j]] > 0) {
					throw new IllegalStateException("Integrity problem in row #" + (i + 1) + ".");
				}
				++cardinality[table[i][j]];
			}
		}
	}
	
	private void checkColumnsIntegrity() {
		for (int i = 0; i < size; ++i) {
			int[] cardinality = new int[size];
			for (int j = 0; j < size; ++j) {
				if (table[j][i] < 0) {
					continue;
				}
				if (cardinality[table[j][i]] > 0) {
					throw new IllegalStateException("Integrity problem in column #" + (i + 1) + ".");
				}
				++cardinality[table[j][i]];
			}
		}
	}
	
	private void checkBlocksIntegrity() {
		for (int blockX = 0; blockX < blockSize; ++blockX) {
			for (int blockY = 0; blockY < blockSize; ++blockY) {
				int[] cardinality = new int[size];
				for (int i = 0; i < blockSize; ++i) {
					int x = blockX * blockSize + i;
					for (int j = 0; j < blockSize; ++j) {
						int y = blockY * blockSize + j;
						if (table[x][y] < 0) {
							continue;
						}
						if (cardinality[table[x][y]] > 0) {
							throw new IllegalStateException("Integrity problem in block ("
									+ (x + 1) + "," + (y + 1) + ").");
						}
						++cardinality[table[x][y]];
					}
				}
			}
		}
	}

	private void initPossibilities() {
		possibilities = new boolean[size][size][size];
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				for (int k = 0; k < size; ++ k) {
					possibilities[i][j][k] = true;
				}
			}
		}
	}
	
	private void eliminatePossibilitiesForCell(int i, int j) {
		eliminatePossibilitiesInRow(i, j);
		eliminatePossibilitiesInColumn(j, i);
		eliminatePossibilitiesInBlock(i, j);
	}
	
	private void eliminatePossibilitiesInRow(int i, int j) {
		int value = table[i][j] - 1;
		for (int k = 0; k < size; ++k) {
			if (k == j) {
				continue;
			}
			possibilities[i][k][value] = false;
		}
	}
	
	private void eliminatePossibilitiesInColumn(int i, int j) {
		int value = table[i][j] - 1;
		for (int k = 0; k < size; ++k) {
			if (k == i) {
				continue;
			}
			possibilities[k][j][value] = false;
		}
	}
	
	private void eliminatePossibilitiesInBlock(int i, int j) {
		int value = table[i][j] - 1;
		int blockX = i / blockSize;
		int blockY = j / blockSize;
		
		for (int k = 0; k < blockSize; ++k) {
			int x = blockX * blockSize + k;
			for (int l = 0; l < blockSize; ++l) {
				int y = blockY * blockSize + l;
				if (x == i && y == j) {
					continue;
				}
				possibilities[x][y][value] = false;
			}
		}
	}
	
	private void findNextForLastCell(int i, int j) {
		
	}
	
	private boolean hasSinglePossibility(int i, int j) {
		int possibilityCount = 0;
		for (int k = 0; k < size; ++k) {
			if (possibilities[i][j][k]) {
				++possibilityCount;
				nextValue = k;
				if (possibilityCount > 1) {
					nextValue = -1;
					return false;
				}
			}
		}
		return possibilityCount == 1;
	}
	
	private void findNextInRow(int i) {
		for (int k = 0; k < size; ++k) {
			if (hasSinglePossibility(i, k)) {
				nextX = i;
				nextY = k;
				return;
			}
		}
	}
	
	private void findNextInColumn(int i) {
		for (int k = 0; k < size; ++k) {
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
