package sudoku.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import sudoku.core.Table;

public interface TableReader {
	
	Table read(InputStream is) throws IOException;

	Table read(Reader r) throws IOException;

}
