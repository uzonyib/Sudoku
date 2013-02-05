package sudoku.core.drools;

import java.util.HashSet;
import java.util.Set;

import sudoku.core.Field;
import sudoku.core.FieldValue;

public class DroolsField extends Field {
	
	private Set<FieldValue> candidates = new HashSet<FieldValue>();
	
	public DroolsField(int rowIndex, int columnIndex, Integer value, int size, int blockSize) {
		super(rowIndex, columnIndex, value, size, blockSize);
		if (this.value == null) {
			for (int i = 1; i <= size; ++i) {
				this.candidates.add(FieldValue.valueOf(i));
			}
		}
	}
	
	public FieldValue getSingleCandidate() {
		return this.candidates.size() == 1 ? this.candidates.iterator().next() : null;
	}
	
	public boolean hasCandidate(FieldValue fieldValue) {
		return this.candidates.contains(fieldValue);
	}
	
	public void deleteCandidate(FieldValue fieldValue) {
		this.candidates.remove(fieldValue);
	}
	
	public int getCandidateCount() {
		return this.candidates.size();
	}

}
