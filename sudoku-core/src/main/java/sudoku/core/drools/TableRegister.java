package sudoku.core.drools;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class TableRegister {
	
	private Set<Integer>[][] candidates;
	
	@SuppressWarnings("unchecked")
	public TableRegister(int size) {
		this.candidates = (Set<Integer>[][]) Array.newInstance(HashSet.class, size, size);
		
		for (int i = 0; i < this.candidates.length; i++) {
			Set<Integer>[] row = this.candidates[i];
			for (int j = 0; j < row.length; j++) {
				row[j] = new HashSet<Integer>();
				for(int k = 0; k < size; ++k) {
					row[j].add(k);
				}
			}
			
		}
	}
	
	public Set<Integer> getCandidates(int rowIndex, int columnIndex) {
		return this.candidates[rowIndex][columnIndex];
	}
	
	public boolean hasCandidate(int rowIndex, int columnIndex, int value) {
		return this.getCandidates(rowIndex, columnIndex).contains(value);
	}
	
	public void deleteCandidate(int rowIndex, int columnIndex, int value) {
		this.candidates[rowIndex][columnIndex].remove(value);
	}

}
