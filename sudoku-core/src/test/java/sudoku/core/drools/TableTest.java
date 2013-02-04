package sudoku.core.drools;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.Step;
import sudoku.core.Table;
import sudoku.core.drools.DroolsTable;

public class TableTest {
	
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
		new DroolsTable((int[][]) null);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithEmptyTable() {
		new DroolsTable(new int[0][0]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithNegativeSize() {
		new DroolsTable(-9);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithZeroSize() {
		new DroolsTable(0);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithLongRows() {
		new DroolsTable(new int[9][10]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithBadSize() {
		new DroolsTable(new int[10][10]);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testConstructorWithNonSquareSize() {
		new DroolsTable(10);
	}
	
	@Test
	public void testTableCreationBySize() {
		Table table = new DroolsTable(9);
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				Assert.assertNull(table.get(i, j));
			}
		}
	}
	
	@Test
	public void testIntegrity() {
		Table table = new DroolsTable(easyTable);
		Assert.assertEquals(9, table.getSize());
		Assert.assertEquals(3, table.getBlockSize());
		
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				// TODO fix
				if (easyTable[i][j] < 0) {
					Assert.assertNull(table.get(i, j));
				} else {
					Assert.assertEquals(table.get(i, j).intValue(), easyTable[i][j]);
				}
			}
		}
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testNegativeValue() {
		new DroolsTable(new int[][] {
				{ -1, -1, -1, -1 },
				{ -1, -1, -2, -1 },
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testTooHighValue() {
		new DroolsTable(new int[][] {
				{ -1, -1, -1, -1 },
				{ -1, -1, -1, -1 },
				{ -1,  5, -1, -1 },
				{ -1, -1, -1, -1 }
		});
	}
	
	@Test
	public void testSet() {
		Table table = new DroolsTable(4);
		table.set(0, 0, 2);
		Assert.assertEquals(table.get(0, 0).intValue(), 2);
		table.set(0, 0, 3);
		Assert.assertEquals(table.get(0, 0).intValue(), 3);
		table.set(1, 1, 2);
		Assert.assertEquals(table.get(1, 1).intValue(), 2);
	}
	
	@Test
	public void testTakeStep() {
		Table table = new DroolsTable(4);
		table.takeStep(new Step(0, 1, 2));
		Assert.assertEquals(table.get(0, 1).intValue(), 2);
		table.takeStep(new Step(3, 1, 2));
		Assert.assertEquals(table.get(3, 1).intValue(), 2);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testTakeStepWithNull() {
		Table table = new DroolsTable(4);
		table.takeStep(null);
	}

}
