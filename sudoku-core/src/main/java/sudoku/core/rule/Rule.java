package sudoku.core.rule;

import sudoku.core.table.Table;

public enum Rule {
	
	ROW_RULE {
		@Override
		public void checkIntegrity(Table table) throws IntegrityException {
			if (table == null) {
				throw new IllegalArgumentException("Table is null.");
			}
			
			for (int rowIndex = 0; rowIndex < table.getSize(); ++rowIndex) {
				int[] cardinality = new int[table.getSize()];
				for (int columnIndex = 0; columnIndex < table.getSize(); ++columnIndex) {
					int element = table.get(rowIndex, columnIndex);
					if (element < 0) {
						continue;
					}
					if (element >= table.getSize()) {
						throw new IllegalArgumentException("Element [" + rowIndex + ","
								+ columnIndex + "] (" + element + ") out of range.");
					}
					if (cardinality[element] > 0) {
						throw new IntegrityException(rowIndex, columnIndex, element, this);
					}
					++cardinality[element];
				}
			}
		}
	},
	COLUMN_RULE {
		@Override
		public void checkIntegrity(Table table) throws IntegrityException {
			if (table == null) {
				throw new IllegalArgumentException("Table is null.");
			}
			
			for (int columnIndex = 0; columnIndex < table.getSize(); ++columnIndex) {
				int[] cardinality = new int[table.getSize()];
				for (int rowIndex = 0; rowIndex < table.getSize(); ++rowIndex) {
					int element = table.get(rowIndex, columnIndex);
					if (element < 0) {
						continue;
					}
					if (element >= table.getSize()) {
						throw new IllegalArgumentException("Element [" + rowIndex + ","
								+ columnIndex + "] (" + element + ") out of range.");
					}
					if (cardinality[element] > 0) {
						throw new IntegrityException(rowIndex, columnIndex, element, this);
					}
					++cardinality[element];
				}
			}
		}
	},
	BLOCK_RULE {
		@Override
		public void checkIntegrity(Table table) throws IntegrityException {
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
							int element = table.get(absRowIndex, absColumnIndex);
							if (element < 0) {
								continue;
							}
							if (element >= table.getSize()) {
								throw new IllegalArgumentException("Element [" + absRowIndex + ","
										+ absColumnIndex + "] (" + element + ") out of range.");
							}
							if (cardinality[element] > 0) {
								throw new IntegrityException(absRowIndex, absColumnIndex, element, this);
							}
							++cardinality[element];
						}
					}
				}
			}
		}
	};
	
	public abstract void checkIntegrity(Table table) throws IntegrityException;
	
}
