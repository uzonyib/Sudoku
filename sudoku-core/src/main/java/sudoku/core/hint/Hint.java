package sudoku.core.hint;

import java.util.List;

import sudoku.core.table.Table;

public interface Hint {
	
	List<Step> advise(Table table);
	
	int getCost();

}
