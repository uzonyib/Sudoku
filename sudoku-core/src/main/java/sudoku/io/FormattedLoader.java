package sudoku.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import sudoku.core.table.Table;

public class FormattedLoader implements TableLoader {
	
	private TableLoader wrappedLoader = new SimpleLoader();

	@Override
	public Table load(InputStream is) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
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
		return wrappedLoader.load(new ByteArrayInputStream(sb.toString().getBytes()));
	}

}
