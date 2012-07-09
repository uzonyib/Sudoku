package sudoku;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SudokuTableTest {
	
	private static int[][] simpleTable = new int[][] {
			{ 4, 0, 5, 7, 9, 0, 3, 0, 1 },
			{ 1, 3, 9, 5, 8, 0, 0, 7, 0 },
			{ 7, 6, 2, 3, 1, 0, 5, 0, 9 },
			{ 2, 5, 4, 9, 0, 0, 8, 6, 3 },
			{ 8, 0, 0, 0, 5, 0, 1, 4, 2 },
			{ 6, 0, 3, 0, 0, 8, 0, 9, 0 },
			{ 0, 7, 0, 1, 0, 0, 2, 0, 8 },
			{ 3, 0, 8, 2, 0, 5, 0, 1, 0 },
			{ 9, 2, 0, 0, 3, 7, 4, 0, 6 }
	};
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithNull() {
		new SudokuTable(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithEmptyTable() {
		new SudokuTable(new int[0][0]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithLongRows() {
		new SudokuTable(new int[9][10]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorWithBadSize() {
		new SudokuTable(new int[10][10]);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRowIntegrity() {
		new SudokuTable(new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testColumnIntegrity() {
		new SudokuTable(new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 8, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 8, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBlockIntegrity() {
		new SudokuTable(new int[][] {
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 7, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 7, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }
		});
	}
	
	@Test
	public void testIntegrity() {
		SudokuTable sudokuTable = new SudokuTable(simpleTable);
		assertEquals(9, sudokuTable.getSize());
		assertEquals(3, sudokuTable.getBlockSize());
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				assertEquals(simpleTable[i][j] - 1, sudokuTable.get(i, j));
			}
		}
	}

}
