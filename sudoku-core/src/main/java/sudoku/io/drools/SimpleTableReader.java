package sudoku.io.drools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import sudoku.core.drools.DroolsTable;
import sudoku.io.TableReader;

public class SimpleTableReader implements TableReader {

	@Override
	public DroolsTable read(InputStream is) throws IOException {
		return read(new BufferedReader(new InputStreamReader(is)));
	}
	
	@Override
	public DroolsTable read(Reader r) throws IOException {
		return read(new BufferedReader(r));
		
	}
	
	protected DroolsTable read(BufferedReader reader) throws IOException {
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
		return new DroolsTable(table);
	}

}
