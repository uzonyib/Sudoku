package sudoku.core.drools;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.FieldValue;
import sudoku.core.Step;
import sudoku.core.Table;
import sudoku.core.drools.DroolsTable;

public class TableTest {
	
	private static int[][] easyTable = new int[][] {
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
				Assert.assertNull(table.get(i, j).getValue());
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
				if (easyTable[i][j] == 0) {
					Assert.assertNull(table.get(i, j).getValue());
				} else {
					Assert.assertEquals(table.get(i, j).getValue().getIndex(), easyTable[i][j]);
				}
			}
		}
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testNegativeValue() {
		new DroolsTable(new int[][] {
				{ 0, 0,  0, 0 },
				{ 0, 0, -1, 0 },
				{ 0, 0,  0, 0 },
				{ 0, 0,  0, 0 }
		});
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testTooHighValue() {
		new DroolsTable(new int[][] {
				{ 0, 0, 0, 0 },
				{ 0, 0, 0, 0 },
				{ 0, 5, 0, 0 },
				{ 0, 0, 0, 0 }
		});
	}
	
	@Test
	public void testSet() {
		Table table = new DroolsTable(4);
		table.set(0, 0, FieldValue.valueOf(2));
		Assert.assertEquals(table.get(0, 0).getValue().getIndex(), 2);
		Assert.assertEquals(table.get(0, 0).getValue().getDisplayValue(), '2');
		table.set(0, 0, FieldValue.valueOf(3));
		Assert.assertEquals(table.get(0, 0).getValue().getIndex(), 3);
		Assert.assertEquals(table.get(0, 0).getValue().getDisplayValue(), '3');
		table.set(1, 1, FieldValue.valueOf(2));
		Assert.assertEquals(table.get(1, 1).getValue().getIndex(), 2);
		Assert.assertEquals(table.get(1, 1).getValue().getDisplayValue(), '2');
	}
	
	@Test
	public void testTakeStep() {
		Table table = new DroolsTable(4);
		table.takeStep(new Step(0, 1, 2));
		Assert.assertEquals(table.get(0, 1).getValue().getIndex(), 2);
		table.takeStep(new Step(3, 1, 2));
		Assert.assertEquals(table.get(3, 1).getValue().getIndex(), 2);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testTakeStepWithNull() {
		Table table = new DroolsTable(4);
		table.takeStep(null);
	}

}
