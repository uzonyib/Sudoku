package sudoku.core.table;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.table.SimpleTable;

public class SimpleTableTest {
	
	private static int[][] easyTable = new int[][] {
			{  3, -1,  4,  6,  8, -1,  2, -1,  0 },
			{  0,  2,  8,  4,  7, -1, -1,  6, -1 },
			{  6,  5,  1,  2,  0, -1,  4, -1,  8 },
			{  1,  4,  3,  8, -1, -1,  7,  5,  2 },
			{  7, -1, -1, -1,  4, -1,  0,  3,  1 },
			{  5, -1,  2, -1, -1,  7, -1,  8, -1 },
			{ -1,  6, -1,  0, -1, -1,  1, -1,  7 },
			{  2, -1,  7,  1, -1,  4, -1,  0, -1 },
			{  8,  1, -1, -1,  2,  6,  3, -1,  5 }
	};
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithNull() {
		new SimpleTable(null);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithEmptyTable() {
		new SimpleTable(new int[0][0]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithNegativeSize() {
		new SimpleTable(-9);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithZeroSize() {
		new SimpleTable(0);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithLongRows() {
		new SimpleTable(new int[9][10]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithBadSize() {
		new SimpleTable(new int[10][10]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithNonSquareSize() {
		new SimpleTable(10);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testRowIntegrity() {
		new SimpleTable(new int[][] {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{  0, -1, -1, -1, -1, -1, -1, -1,  0 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testColumnIntegrity() {
		new SimpleTable(new int[][] {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1,  8, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1,  8, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testBlockIntegrity() {
		new SimpleTable(new int[][] {
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1,  7, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1,  7, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 },
				{ -1, -1, -1, -1, -1, -1, -1, -1, -1 }
		});
	}
	
	@Test
	public void testTableCreationBySize() {
		Table table = new SimpleTable(9);
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				Assert.assertEquals(table.get(i, j), -1);
			}
		}
	}
	
	@Test
	public void testIntegrity() {
		Table table = new SimpleTable(easyTable);
		Assert.assertEquals(9, table.getSize());
		Assert.assertEquals(3, table.getBlockSize());
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				Assert.assertEquals(table.get(i, j), easyTable[i][j]);
			}
		}
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testNegativeValue() {
		new SimpleTable(new int[][] {
				{ -1, -1, -1, -1 },
				{ -1, -1, -2, -1 },
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testTooHighValue() {
		new SimpleTable(new int[][] {
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 },
				{ -1,  5, -1, -1 },
				{ -1, -1, -1, -1 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIntegrityOnSet() {
		Table table = new SimpleTable(4);
		table.set(0, 0, 0);
		table.set(1, 1, 0);
	}

}
