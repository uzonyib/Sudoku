package sudoku.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sudoku.core.table.SimpleTable;
import sudoku.core.table.Table;

public class SimpleLoader implements TableLoader {

	@Override
	public Table load(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		int[][] table = null;
		int rowIndex = 0;
		int size = -1;
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			if (size < 0) {
				size = line.length();
				table = new int[size][size];
			} else if (line.length() != size) {
				throw new IllegalArgumentException("Non-uniform line length.");
			}
			
			for (int columnIndex = 0; columnIndex < size; ++columnIndex) {
				table[rowIndex][columnIndex] = Integer.valueOf("" + line.charAt(columnIndex)) - 1;
			}
			
			++rowIndex;
		}
		return new SimpleTable(table);
	}

}
