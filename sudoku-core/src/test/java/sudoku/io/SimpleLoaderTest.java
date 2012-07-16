package sudoku.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.testng.annotations.Test;

import sudoku.core.table.Table;

public class SimpleLoaderTest {
	
	private static final TableLoader LOADER = new SimpleLoader();
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void testIncorrectValue() throws IOException {
		LOADER.load(new ByteArrayInputStream("12a4\n1234\n1234\n1234".getBytes()));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectLineLength() throws IOException {
		LOADER.load(new ByteArrayInputStream("1234\n123\n1234\n1234".getBytes()));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIntegrityCheck() throws IOException {
		LOADER.load(new ByteArrayInputStream("1234\n1234\n1234\n1234".getBytes()));
	}
	
	@Test
	public void testCorrectData() throws IOException {
		Table table = LOADER.load(new ByteArrayInputStream("1000\n0200\n0030\n0004".getBytes()));
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
