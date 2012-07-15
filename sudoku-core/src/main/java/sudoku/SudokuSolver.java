package sudoku;

import java.util.Arrays;

import sudoku.core.hint.Step;
import sudoku.core.table.Table;

public class SudokuSolver {
	
	private Table sudokuTable;
	private int size;
	private int blockSize;

	private boolean[][][] possibilities;
	
	public SudokuSolver(Table sudokuTable) {
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
				Arrays.fill(possibilities[i][j], true);
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
	
	private int findStepInCell(int i, int j) {
		int possibility = -1;
		for (int k = 0; k < size; ++k) {
			if (possibilities[i][j][k]) {
				if (possibility >= 0) {
					return -1;
				}
				possibility = k;
			}
		}
		return possibility;
	}
	
	private Step findStepInRow(int i) {
		for (int k = 0; k < size; ++k) {
			int possibility = findStepInCell(i, k);
			if (possibility >= 0) {
				return new Step(i, k, possibility);
			}
		}
		return null;
	}
	
	private Step findStepInColumn(int i) {
		for (int k = 0; k < size; ++k) {
			int possibility = findStepInCell(k, i);
			if (possibility >= 0) {
				return new Step(k, i, possibility);
			}
		}
		return null;
	}

}
