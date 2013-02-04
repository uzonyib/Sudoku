package sudoku.core;

public interface Table {
	
	int getSize();
	
	int getBlockSize();
	
	Integer get(int rowIndex, int columnIndex);
	
	String getElement(int rowIndex, int columnIndex);
	
	void set(int rowIndex, int columnIndex, int value);
	
	void takeStep(Step step);

}
