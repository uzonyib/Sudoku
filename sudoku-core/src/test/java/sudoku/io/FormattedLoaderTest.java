package sudoku.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.testng.annotations.Test;

import sudoku.core.table.Table;

public class FormattedLoaderTest {
	
	private static final TableLoader LOADER = new FormattedLoader();
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void testIncorrectValue() throws IOException {
		LOADER.load(new ByteArrayInputStream((
				"  - -   - -\n" +
				"| 1 2 | a 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -\n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -"
				).getBytes()));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectLineLength() throws IOException {
		LOADER.load(new ByteArrayInputStream((
				"  - -   - -\n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -\n" +
				"| 1 2 | 3   |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -"
				).getBytes()));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIntegrityCheck() throws IOException {
		LOADER.load(new ByteArrayInputStream((
				"  - -   - -\n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -\n" +
				"| 1 2 | 3 4 |\n" +
				"| 1 2 | 3 4 |\n" +
				"  - -   - -"
				).getBytes()));
	}
	
	@Test
	public void testCorrectData() throws IOException {
		Table table = LOADER.load(new ByteArrayInputStream((
				"  - -   - -\n" +
				"| 1 . | . . |\n" +
				"| . 2 | . . |\n" +
				"  - -   - -\n" +
				"| . . | 3 . |\n" +
				"| . . | . 4 |\n" +
				"  - -   - -"
				).getBytes()));
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				if (i != j) {
					Assert.assertEquals(-1, table.get(i, j));
				} else {
					Assert.assertEquals(i, table.get(i, j));
				}
			}
		}
	}

}
