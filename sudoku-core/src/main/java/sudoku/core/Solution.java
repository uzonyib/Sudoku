package sudoku.core;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Solution {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("DroolsLogger");
	
	public static enum ResultStatus {
		SOLVED, CONTRADICTION, CANNOT_BE_SOLVED;
	}

	protected ResultStatus resultStatus;
	protected List<Step> steps = new LinkedList<Step>();
	protected Table startingTable;
	protected Table resultTable;

	public Solution(Table startingTable) {
		this.startingTable = startingTable;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
		LOGGER.debug("Result status set to " + resultStatus);
	}
	
	public List<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		this.steps.add(step);
		LOGGER.debug("Step added: " + step);
		LOGGER.trace("Current table:\n" + resultTable);
	}

	public Table getStartingTable() {
		return startingTable;
	}

	public Table getResultTable() {
		return resultTable;
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
