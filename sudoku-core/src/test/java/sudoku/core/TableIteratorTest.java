package sudoku.core;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import sudoku.core.drools.DroolsTable;

public class TableIteratorTest {
	
	private static int[][] smallTable = new int[][] {
		{ 1, 2, 0, 4 },
		{ 3, 0, 0, 0 },
		{ 0, 0, 0, 0 },
		{ 0, 0, 0, 2 }
	};
	
	@Test
	public void iterator() {
		List<Field> list = new ArrayList<Field>();
		for (int i = 0; i < smallTable.length; ++i) {
			for (int j = 0; j < smallTable.length; ++j) {
				list.add(new Field(i, j, smallTable[i][j], 4, 2));
			}
		}
		
		Table table = new DroolsTable(smallTable);
		int index = 0;
		for (Field field : table) {
			Assert.assertEquals(list.get(index), field);
			++index;
		}
		Assert.assertEquals(list.size(), index);
	}

}
