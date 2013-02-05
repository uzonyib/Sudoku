package sudoku.core.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import sudoku.core.Solution;
import sudoku.core.Solver;
import sudoku.core.Table;

public class DroolsSolver implements Solver {

	@Override
	public Solution solve(Table table) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(ResourceFactory.newClassPathResource("sudoku.drl"), ResourceType.DRL);
		if (builder.hasErrors()) {
			System.out.println(builder.getErrors());
			return null;
		}
		
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(builder.getKnowledgePackages());
		StatefulKnowledgeSession session = kb.newStatefulKnowledgeSession();
		
		Solution solution = new Solution(table);
		session.setGlobal("solution", solution);
		DroolsTable droolsTable = (DroolsTable) solution.getResultTable();
		for (int i = 0; i < table.getSize(); ++i) {
			for (int j = 0; j < table.getSize(); ++j) {
				session.insert(droolsTable.getField(i, j));
			}
		}		

		session.fireAllRules();
		return solution;
	}

}
