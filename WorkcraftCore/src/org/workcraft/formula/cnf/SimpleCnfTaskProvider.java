package org.workcraft.formula.cnf;

import static org.workcraft.formula.encoding.CnfOperations.not;
import static org.workcraft.formula.encoding.CnfOperations.or;

import java.util.HashMap;
import java.util.Map;

import org.workcraft.formula.BooleanVariable;
import org.workcraft.formula.Literal;
import org.workcraft.formula.sat.MiniSatCnfPrinter;

public class SimpleCnfTaskProvider implements CnfGenerator<Cnf> {

    @Override
    public CnfTask getCnf(Cnf cnf) {
        Map<String, BooleanVariable> vars = new HashMap<>();
        for (CnfClause clause : cnf.getClauses()) {
            for (Literal literal : clause.getLiterals()) {
                BooleanVariable variable = literal.getVariable();
                String label = variable.getLabel();
                if (!label.isEmpty()) {
                    vars.put(label, variable);
                }
            }
        }
        cnf.getClauses().add(or(not(Literal.ZERO)));
        cnf.getClauses().add(or(Literal.ONE));
        return new CnfTask(cnf.toString(new MiniSatCnfPrinter()), vars);
    }

}
