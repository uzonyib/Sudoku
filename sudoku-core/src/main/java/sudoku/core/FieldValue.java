package sudoku.core;

import java.util.HashMap;
import java.util.Map;

public final class FieldValue {
	
	private static final Map<Integer, FieldValue> FIELD_VALUES = new HashMap<Integer, FieldValue>();
	
	public static FieldValue valueOf(int index) {
		FieldValue fv = FIELD_VALUES.get(index);
		if (fv == null) {
			fv = new FieldValue(index);
			FIELD_VALUES.put(index, fv);
		}
		return fv;
	}
	
	private final int index;
	private final char displayValue;
	
	private FieldValue(int index) {
		if (index <= 0) {
			throw new IllegalArgumentException("Invalid field value index.");
		}
		this.index = index;
		
		String hexValue = Integer.toHexString(index);
		if (hexValue.length() > 1) {
			// TODO
		}
		this.displayValue = hexValue.charAt(0);
	}
	
	public int getIndex() {
		return index;
	}
	
	public char getDisplayValue() {
		return displayValue;
	}
	
	@Override
	public String toString() {
		return "" + displayValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof FieldValue)) {
			return false;
		}
		FieldValue fv = (FieldValue) obj;
		return this.index == fv.index;
	}
	
	@Override
	public int hashCode() {
		return this.index;
	}

}
