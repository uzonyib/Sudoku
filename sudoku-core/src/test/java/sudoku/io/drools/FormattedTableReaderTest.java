package sudoku.io.drools;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;

import org.testng.annotations.Test;

import sudoku.core.Table;
import sudoku.io.TableReader;

public class FormattedTableReaderTest {
	
	private static final TableReader READER = new FormattedTableReader();
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void testIncorrectValue() throws IOException {
		READER.read(new StringReader((
				"  - -   - -  \n" +
				"| 1 2 | a 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -  \n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -  "
				)));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectLineLength() throws IOException {
		READER.read(new StringReader((
				"  - -   - -  \n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -  \n" +
				"| 1 2 | 3   |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -  "
				)));
	}
	
	@Test
	public void testCorrectData() throws IOException {
		Table table = READER.read(new StringReader((
				"  - -   - -  \n" +
				"| 1 . | . . |\n" +
				"| . 2 | . . |\n" +
				"  - -   - -  \n" +
				"| . . | 3 . |\n" +
				"| . . | . 4 |\n" +
				"  - -   - -  "
				)));
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (i != j) {
					Assert.assertNull(table.get(i, j).getValue());
				} else {
					Assert.assertEquals(i + 1, table.get(i, j).getValue().getIndex());
				}
			}
		}
	}

}
