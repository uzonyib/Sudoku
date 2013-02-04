package sudoku.io.drools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import sudoku.core.drools.DroolsTable;
import sudoku.io.TableReader;

public class FormattedTableReader implements TableReader {
	
	private SimpleTableReader wrappedReader = new SimpleTableReader();

	@Override
	public DroolsTable read(InputStream is) throws IOException {
		return read(new BufferedReader(new InputStreamReader(is)));
		
	}

	@Override
	public DroolsTable read(Reader r) throws IOException {
		return read(new BufferedReader(r));
	}
	
	protected DroolsTable read(BufferedReader reader) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (String line = reader.readLine(); line != null; line = reader.readLine()) {
			line = line.replaceAll(" ", "").replaceAll("\\|", "").replaceAll("-", "")
					.replaceAll("\\.", "0");
			if (line.length() == 0) {
				continue;
			}
			sb.append(line).append('\n');
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return wrappedReader.read(new StringReader(sb.toString()));
	}

}
