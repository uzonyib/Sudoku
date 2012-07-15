package sudoku.core.table;


public class TestTable implements Table {
	
	private int[][] table;
	
	public TestTable(int[][] table) {
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
	public int get(int x, int y) {
		return table[x][y] - 1;
	}

	@Override
	public boolean checkIntegrity() {
		return true;
	}
	
	@Override
	public void set(int rowIndex, int columnIndex, int value) {
		table[rowIndex][columnIndex] = value;
	}
	
	@Override
	public String getElement(int rowIndex, int columnIndex) {
		return "" + get(rowIndex, columnIndex);
	}

}
