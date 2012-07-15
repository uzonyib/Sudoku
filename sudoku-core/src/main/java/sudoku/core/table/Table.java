package sudoku.core.table;

public interface Table {
	
	int getSize();
	
	int getBlockSize();
	
	int get(int rowIndex, int columnIndex);
	
	String getElement(int rowIndex, int columnIndex);
	
	boolean checkIntegrity();
	
	void set(int rowIndex, int columnIndex, int value);

}
