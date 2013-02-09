package sudoku.core.drools;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import sudoku.core.FieldValue;
import sudoku.core.Solution;
import sudoku.core.Step;

public class DroolsSolution extends Solution implements PropertyChangeListener {
	
	public DroolsSolution(DroolsTable startingTable) {
		super(startingTable);
		this.resultTable = new DroolsTable(startingTable);
	}
	
	@Override
	public DroolsTable getStartingTable() {
		return (DroolsTable) super.getStartingTable();
	}
	
	@Override
	public DroolsTable getResultTable() {
		return (DroolsTable) super.getResultTable();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("value".equals(evt.getPropertyName())) {
			this.addStep(new Step((DroolsField) evt.getSource(), (FieldValue) evt.getNewValue()));
		}
	}

}
