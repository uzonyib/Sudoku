package sudoku.core;

public interface Table extends Iterable<Field> {
	
	int getSize();
	
	int getBlockSize();
	
	Field get(int rowIndex, int columnIndex);
	
	void set(int rowIndex, int columnIndex, FieldValue value);
	
}
