package sudoku.core;


class BlockIntegrity implements Integrity {

	@Override
	public IntegrityError check(Table table) {
		if (table == null) {
			throw new IllegalArgumentException("Table is null.");
		}
		
		int blockSize = table.getBlockSize();
		for (int blockRowIndex = 0; blockRowIndex < blockSize; ++blockRowIndex) {
			for (int blockColumnIndex = 0; blockColumnIndex < blockSize; ++blockColumnIndex) {
				int[] cardinality = new int[table.getSize()];
				for (int relRowIndex = 0; relRowIndex < blockSize; ++relRowIndex) {
					int absRowIndex = blockRowIndex * blockSize + relRowIndex;
					for (int relColumnIndex = 0; relColumnIndex < blockSize; ++relColumnIndex) {
						int absColumnIndex = blockColumnIndex * blockSize + relColumnIndex;
						Integer element = table.get(absRowIndex, absColumnIndex);
						if (element == null) {
							continue;
						}
						if (element >= table.getSize()) {
							throw new IllegalArgumentException("Element [" + absRowIndex + ","
									+ absColumnIndex + "] (" + element + ") out of range.");
						}
						if (cardinality[element] > 0) {
							return new IntegrityError(absRowIndex, absColumnIndex, element, this);
						}
						++cardinality[element];
					}
				}
			}
		}
		
		return null;
	}

}
