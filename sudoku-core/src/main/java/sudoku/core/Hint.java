package sudoku.core;

import java.util.List;

public interface Hint {
	
	List<Step> advise(Table table);
	
	int getCost();

}
