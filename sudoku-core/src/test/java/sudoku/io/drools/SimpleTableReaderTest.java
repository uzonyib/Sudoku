package sudoku.io.drools;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;

import org.testng.annotations.Test;

import sudoku.core.Table;
import sudoku.io.TableReader;

public class SimpleTableReaderTest {
	
	private static final TableReader READER = new SimpleTableReader();
	
	@Test(expectedExceptions = NumberFormatException.class)
	public void testIncorrectValue() throws IOException {
		READER.read(new StringReader("12a4\n1234\n1234\n1234"));
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testIncorrectLineLength() throws IOException {
		READER.read(new StringReader("1234\n123\n1234\n1234"));
	}
	
	@Test
	public void testCorrectData() throws IOException {
		Table table = READER.read(new StringReader("1000\n0200\n0030\n0004"));
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
