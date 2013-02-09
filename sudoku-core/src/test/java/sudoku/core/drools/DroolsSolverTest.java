package sudoku.core.drools;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.Solution;
import sudoku.core.Solution.ResultStatus;
import sudoku.core.Solver;

public class DroolsSolverTest {
	
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
	
	@Test//(enabled = false)
	public void solveEasyTable() {
		Solver solver = new DroolsSolver();
		Solution solution = solver.solve(new DroolsTable(easyTable));
		Assert.assertNotNull(solution);
		Assert.assertNotNull(solution.getResultTable());
		Assert.assertEquals(solution.getResultStatus(), ResultStatus.SOLVED);
	}
	
	private static int[][] smallTable = new int[][] {
		{ 1, 2, 0, 4 },
		{ 3, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 2 }
	};
	
	@Test
	public void solveSmallTable() {
		Solver solver = new DroolsSolver();
		Solution solution = solver.solve(new DroolsTable(smallTable));
		Assert.assertNotNull(solution);
		Assert.assertNotNull(solution.getResultTable());
		Assert.assertEquals(solution.getResultStatus(), ResultStatus.SOLVED);
		Assert.assertEquals(solution.getResultTable(), new DroolsTable(new int[][] {
				{ 1, 2, 3, 4 },
				{ 3, 4, 2, 1 },
				{ 2, 1, 4, 3 },
				{ 4, 3, 1, 2 }
		}));
	}
	
	private static int[][] smallTable2 = new int[][] {
		{ 1, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 1 }
	};
	
	@Test
	public void solveSmallTable2() {
		Solver solver = new DroolsSolver();
		Solution solution = solver.solve(new DroolsTable(smallTable2));
		Assert.assertNotNull(solution);
		Assert.assertNotNull(solution.getResultTable());
		Assert.assertEquals(solution.getResultStatus(), null);
		Assert.assertEquals(solution.getResultTable(), new DroolsTable(new int[][] {
				{ 1, 0, 0, 0 },
				{ 0, 0, 1, 0 },
				{ 0, 1, 0, 0 },
				{ 0, 0, 0, 1 }
		}));
		Assert.assertEquals(solution.getSteps().size(), 2);
	}

}
