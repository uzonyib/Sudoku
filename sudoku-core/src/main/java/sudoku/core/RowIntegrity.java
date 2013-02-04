package sudoku.core;


class RowIntegrity implements Integrity {

	@Override
	public IntegrityError check(Table table) {
		if (table == null) {
			throw new IllegalArgumentException("Table is null.");
		}
		
		for (int rowIndex = 0; rowIndex < table.getSize(); ++rowIndex) {
			int[] cardinality = new int[table.getSize()];
			for (int columnIndex = 0; columnIndex < table.getSize(); ++columnIndex) {
				Integer element = table.get(rowIndex, columnIndex);
				if (element == null) {
					continue;
				}
				if (element >= table.getSize()) {
					throw new IllegalArgumentException("Element [" + rowIndex + ","
							+ columnIndex + "] (" + element + ") out of range.");
				}
				if (cardinality[element] > 0) {
					return new IntegrityError(rowIndex, columnIndex, element, this);
				}
				++cardinality[element];
			}
		}
		
		return null;
	}

}
