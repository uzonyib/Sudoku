package sudoku.core.drools;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sudoku.core.FieldValue;
import sudoku.core.Solution;
import sudoku.core.Solver;
import sudoku.core.Table;

public class DroolsSolver implements Solver {
	
	private static final Logger LOGGER = LoggerFactory.getLogger("DroolsLogger");
	
	private static final String[] RULE_FILES = new String[] {
		"rules/sudoku.drl",
		"rules/eliminate_candidate.drl",
		"rules/single_candidate.drl",
		"rules/value_to_single_field.drl"
	};

	@Override
	public Solution solve(Table table) {
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		for (String ruleFile : RULE_FILES) {
			builder.add(ResourceFactory.newClassPathResource(ruleFile), ResourceType.DRL);
		}
		if (builder.hasErrors()) {
			LOGGER.error(builder.getErrors().toString());
			return null;
		}
		
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(builder.getKnowledgePackages());
		StatefulKnowledgeSession session = kb.newStatefulKnowledgeSession();
		
		DroolsTable droolsTable = new DroolsTable(table);
		DroolsSolution solution = new DroolsSolution(droolsTable);
		session.setGlobal("solution", solution);
		DroolsTable resultTable = solution.getResultTable();
		for (int i = 1; i <= droolsTable.getSize(); ++i) {
			session.insert(FieldValue.valueOf(i));
		}
		
		for (int i = 0; i < droolsTable.getSize(); ++i) {
			for (int j = 0; j < droolsTable.getSize(); ++j) {
				DroolsField field = resultTable.get(i, j);
				field.addPropertyChangeListener(solution);
				session.insert(field);
			}
		}		

		session.fireAllRules();
		return solution;
	}

}
