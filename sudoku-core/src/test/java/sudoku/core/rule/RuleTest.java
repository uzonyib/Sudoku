package sudoku.core.rule;

import org.testng.annotations.Test;

import sudoku.core.table.Table;
import sudoku.core.table.TestTable;

public class RuleTest {
	
	private static Table table = new TestTable(new int[][] {
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
	
	@Test(expectedExceptions = IntegrityException.class,
			expectedExceptionsMessageRegExp = "Row integrity error at \\(1,6\\), value 4.")
	public void testRowRule() throws IntegrityException {
		Rule.ROW_RULE.checkIntegrity(table);
	}
	
	@Test(expectedExceptions = IntegrityException.class,
			expectedExceptionsMessageRegExp = "Column integrity error at \\(8,2\\), value 6.")
	public void testColumnRule() throws IntegrityException {
		Rule.COLUMN_RULE.checkIntegrity(table);
	}
	
	@Test(expectedExceptions = IntegrityException.class,
			expectedExceptionsMessageRegExp = "Block integrity error at \\(9,8\\), value 2.")
	public void testBlockRule() throws IntegrityException {
		Rule.BLOCK_RULE.checkIntegrity(table);
	}

}
