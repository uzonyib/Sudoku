package sudoku.core;

import java.util.LinkedList;
import java.util.List;

import sudoku.core.drools.DroolsTable;

public class Solution {
	
	public static enum ResultStatus {
		SOLVED, CONTRADICTION, CANNOT_BE_SOLVED;
	}

	private ResultStatus resultStatus;
	private List<Step> steps = new LinkedList<Step>();
	private Table startingTable;
	private Table resultTable;

	public Solution(Table startingTable) {
		this.startingTable = startingTable;
		this.resultTable = new DroolsTable(startingTable);
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		this.steps.add(step);
	}

	public Table getStartingTable() {
		return startingTable;
	}

	public void setStartingTable(Table startingTable) {
		this.startingTable = startingTable;
	}

	public Table getResultTable() {
		return resultTable;
	}

	public void setResultTable(Table resultTable) {
		this.resultTable = resultTable;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(startingTable).append("\n");
		for (Step step : steps) {
			sb.append(step).append("\n");
		}
		sb.append("\n");
		sb.append(resultTable).append("\n").append(resultStatus).append("\n");
		return sb.toString();
	}

}
