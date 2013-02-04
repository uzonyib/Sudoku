package sudoku.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class IntegrityFactory {
	
	private static final Set<Integrity> INTEGRITIES = new HashSet<Integrity>(
			Arrays.asList(
					new RowIntegrity(),
					new ColumnIntegrity(),
					new BlockIntegrity()
			));

	private IntegrityFactory() {
		
	}
	
	public static Set<Integrity> getIntegrities() {
		return INTEGRITIES;
	}
	
}
