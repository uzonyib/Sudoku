package sudoku;

public class SudokuSolver {
	
	private SudokuTable sudokuTable;
	private int size;
	private int blockSize;

	private boolean[][][] possibilities;
	
	private int nextX;
	private int nextY;
	private int nextValue;
	
	public SudokuSolver(SudokuTable sudokuTable) {
		this.sudokuTable = sudokuTable;
		this.size = sudokuTable.getSize();
		this.blockSize = sudokuTable.getBlockSize();
		
		initPossibilities();

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (this.sudokuTable.get(i, j) >= 0) {
					eliminatePossibilitiesForCell(i, j);
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
		int value = sudokuTable.get(i, j);
		for (int k = 0; k < size; ++k) {
			if (k == j) {
				continue;
			}
			possibilities[i][k][value] = false;
		}
	}
	
	private void eliminatePossibilitiesInColumn(int i, int j) {
		int value = sudokuTable.get(i, j);
		for (int k = 0; k < size; ++k) {
			if (k == i) {
				continue;
			}
			possibilities[k][j][value] = false;
		}
	}
	
	private void eliminatePossibilitiesInBlock(int i, int j) {
		int value = sudokuTable.get(i, j);
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

}
