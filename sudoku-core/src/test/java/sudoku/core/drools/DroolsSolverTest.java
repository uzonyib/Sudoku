package sudoku.core.drools;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.Solution;
import sudoku.core.Solution.ResultStatus;
import sudoku.core.Solver;

public class DroolsSolverTest {
	
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
	
	@Test//(enabled = false)
	public void solveEasyTable() {
		Solver solver = new DroolsSolver();
		Solution solution = solver.solve(new DroolsTable(easyTable));
		Assert.assertNotNull(solution);
		Assert.assertNotNull(solution.getResultTable());
		Assert.assertTrue(solution.getResultStatus() == ResultStatus.SOLVED);
		
		System.out.println(solution);
	}
	
	private static int[][] smallTable = new int[][] {
		{  0,  1, -1,  3 },
		{  2, -1, -1, -1 },
		{ -1, -1, -1, -1 },
		{ -1, -1, -1,  1 }
	};
	
	@Test
	public void solveSmallTable() {
		Solver solver = new DroolsSolver();
		Solution solution = solver.solve(new DroolsTable(smallTable));
		Assert.assertNotNull(solution);
		Assert.assertNotNull(solution.getResultTable());
		Assert.assertTrue(solution.getResultStatus() == ResultStatus.SOLVED);
		
		System.out.println(solution);
	}

}
