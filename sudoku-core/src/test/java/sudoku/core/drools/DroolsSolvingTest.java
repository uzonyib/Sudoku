package sudoku.core.drools;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.Solution;
import sudoku.core.Solution.ResultStatus;
import sudoku.core.Solver;
import sudoku.io.drools.FormattedTableReader;

public class DroolsSolvingTest {
	
	private static final String[] TABLE_FILES = new String[] {
		"table_0.txt"//, "table_5.txt"
	};
	
	@Test
	public void solveTables() throws IOException {
		for (String tableFile : TABLE_FILES) {
			DroolsTable table = new FormattedTableReader().read(
					this.getClass().getClassLoader().getResourceAsStream(tableFile));
			Solver solver = new DroolsSolver();
			Solution solution = solver.solve(new DroolsTable(table));
			Assert.assertNotNull(solution);
			Assert.assertNotNull(solution.getResultTable());
			Assert.assertEquals(solution.getResultStatus(), ResultStatus.SOLVED);
		}
	}

}
