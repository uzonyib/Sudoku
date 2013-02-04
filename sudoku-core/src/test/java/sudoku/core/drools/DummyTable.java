package sudoku.core.drools;

import sudoku.core.Step;
import sudoku.core.Table;

public class DummyTable implements Table {
	
	private int[][] table;
	
	public DummyTable(int[][] table) {
		this.table = table;
	}
	
	@Override
	public int getSize() {
		return table.length;
	}

	@Override
	public int getBlockSize() {
		return (int) Math.sqrt(table.length);
	}
	
	@Override
	public Integer get(int x, int y) {
		return table[x][y] <= 0 ? null : table[x][y] - 1;
	}
	
	@Override
	public void set(int rowIndex, int columnIndex, int value) {
		table[rowIndex][columnIndex] = value;
	}
	
	@Override
	public void takeStep(Step step) {
		set(step.getRowIndex(), step.getColumnIndex(), step.getValue());
	}
	
	@Override
	public String getElement(int rowIndex, int columnIndex) {
		return "" + get(rowIndex, columnIndex);
	}

}
