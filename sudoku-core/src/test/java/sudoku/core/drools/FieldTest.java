package sudoku.core.drools;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FieldTest {
	
	@Test
	public void testBlockIndex() {
		Assert.assertEquals(new Field(0, 2, 0, 9, 3).getBlockIndex(), 0);
		Assert.assertEquals(new Field(0, 3, 0, 9, 3).getBlockIndex(), 1);
		Assert.assertEquals(new Field(1, 4, 0, 9, 3).getBlockIndex(), 1);
		Assert.assertEquals(new Field(2, 5, 0, 9, 3).getBlockIndex(), 1);
		Assert.assertEquals(new Field(2, 6, 0, 9, 3).getBlockIndex(), 2);
	}

}
