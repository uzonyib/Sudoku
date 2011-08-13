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
		this.blockSize = (short) Math.sqrt(this.size);
		
		if (this.blockSize * this.blockSize != this.size) {
			throw new IllegalArgumentException("Invalid table size.");
		}
		
		initPossibilities();
		this.table = new short[size][size];
		for (short i = 0; i < this.size; ++i) {
			for (short j = 0; j < this.size; ++j) {
				if (table[i][j] > 0) {
					this.table[i][j] = table[i][j];
					this.eliminatePossibilitiesForCell(i, j);
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
		short value = this.table[i][j];
		for (short k = 0; k < this.size; ++k) {
			if (k == j) {
				continue;
			}
			this.possibilities[i][k][value] = 0;
		}
	}
	
	private void eliminatePossibilitiesInColumn(short i, short j) {
		short value = this.table[i][j];
		for (short k = 0; k < this.size; ++k) {
			if (k == i) {
				continue;
			}
			this.possibilities[k][j][value] = 0;
		}
	}
	
	private void eliminatePossibilitiesInBlock(short i, short j) {
		short value = this.table[i][j];
		short blockX = (short) (i / blockSize);
		short blockY = (short) (j / blockSize);
		
		for (short k = 0; k < this.blockSize; ++k) {
			short x = (short) (blockX * this.blockSize + k);
			for (short l = 0; l < this.blockSize; ++l) {
				short y = (short) (blockY * this.blockSize + l);
				if (x == i && y == j) {
					continue;
				}
				this.possibilities[x][y][value] = 0;
			}
		}
	}
	
	private void findNextForLastCell(short i, short j) {
		
	}
	
	private boolean hasSinglePossibility(short i, short j) {
		short nonZeroCount = 0;
		for (short k = 0; k < this.size; ++k) {
			if (this.possibilities[i][j][k] != 0) {
				++nonZeroCount;
				this.nextValue = this.possibilities[i][j][k];
				if (nonZeroCount > 1) {
					this.nextValue = 0;
					return false;
				}
			}
		}
		return nonZeroCount == 1;
	}
	
	private void findNextInRow(short i) {
		for (short k = 0; k < this.size; ++k) {
			if (hasSinglePossibility(i, k)) {
				this.nextX = i;
				this.nextY = k;
				return;
			}
		}
	}
	
	private void findNextInColumn(short i) {
		for (short k = 0; k < this.size; ++k) {
			if (hasSinglePossibility(k, i)) {
				this.nextX = k;
				this.nextY = i;
				return;
			}
		}
	}

	public int getSize() {
		return size;
	}

}
