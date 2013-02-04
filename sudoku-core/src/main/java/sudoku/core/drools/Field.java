package sudoku.core.drools;

import java.util.HashSet;
import java.util.Set;

public class Field {
	
	private int rowIndex;
	private int columnIndex;
	private int blockIndex;
	private Integer value;
	private Set<Integer> candidates = new HashSet<Integer>();
	
	public Field(int rowIndex, int columnIndex, Integer value, int size, int blockSize) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.blockIndex = ((rowIndex / blockSize) * blockSize) + columnIndex / blockSize;
		this.value = ((value == null || value < 0) ? null : value);
		if (this.value == null) {
			for (int i = 0; i < size; ++i) {
				this.candidates.add(i);
			}
		}
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public int getBlockIndex() {
		return blockIndex;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public void setValue(Integer value) {
		this.value = value;
		this.candidates.clear();
	}
	
	public Integer getSingleCandidate() {
		return this.candidates.size() == 1 ? this.candidates.iterator().next() : null;
	}
	
	public boolean hasCandidate(int value) {
		return this.candidates.contains(value);
	}
	
	public void deleteCandidate(int value) {
		this.candidates.remove(value);
	}
	
	public int getCandidateCount() {
		return this.candidates.size();
	}

}
