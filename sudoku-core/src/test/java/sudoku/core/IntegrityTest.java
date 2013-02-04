package sudoku.core;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.BlockIntegrity;
import sudoku.core.ColumnIntegrity;
import sudoku.core.IntegrityError;
import sudoku.core.RowIntegrity;
import sudoku.core.Table;
import sudoku.core.drools.DummyTable;

public class IntegrityTest {
	
	private static Table table = new DummyTable(new int[][] {
		{ 4, 0, 5, 7, 9, 4, 3, 0, 1 },
		{ 1, 3, 9, 5, 8, 0, 0, 7, 0 },
		{ 7, 6, 2, 3, 1, 0, 5, 0, 9 },
		{ 2, 5, 4, 9, 0, 0, 8, 6, 3 },
		{ 8, 0, 0, 0, 5, 0, 1, 4, 2 },
		{ 6, 0, 3, 0, 0, 8, 0, 9, 0 },
		{ 0, 7, 0, 1, 0, 0, 2, 0, 8 },
		{ 3, 6, 8, 2, 0, 5, 0, 1, 0 },
		{ 9, 2, 0, 0, 3, 7, 4, 2, 6 }
	});
	
	@Test
	public void testRowRule() {
		IntegrityError e = new RowIntegrity().check(table);
		Assert.assertNotNull(e);
		Assert.assertEquals(e.getMessage(), "RowIntegrity error at (1,6), value 4.");
	}
	
	@Test
	public void testColumnRule() {
		IntegrityError e = new ColumnIntegrity().check(table);
		Assert.assertNotNull(e);
		Assert.assertEquals(e.getMessage(), "ColumnIntegrity error at (8,2), value 6.");
	}
	
	@Test
	public void testBlockRule() {
		IntegrityError e = new BlockIntegrity().check(table);
		Assert.assertNotNull(e);
		Assert.assertEquals(e.getMessage(), "BlockIntegrity error at (9,8), value 2.");
	}

}
