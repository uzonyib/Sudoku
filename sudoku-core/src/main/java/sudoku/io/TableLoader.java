package sudoku.io;

import java.io.IOException;
import java.io.InputStream;

import sudoku.core.table.Table;

public interface TableLoader {
	
	Table load(InputStream is) throws IOException;

}
