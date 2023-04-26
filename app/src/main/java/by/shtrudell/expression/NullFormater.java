package by.shtrudell.expression;

import org.mariuszgromada.math.mxparser.Expression;

public class NullFormater implements Formater{
    @Override
    public FormationResult addDigit(Digit digit) {
        return new FormationResult(false, true, null);
    }

    @Override
    public FormationResult addOperator(Operator operator) {
        return new FormationResult(false, true, null);
    }

    @Override
    public FormationResult addBracket(Bracket bracket) {
        return new FormationResult(false, true, null);
    }

    @Override
    public FormationResult addFunction(Function function) {
        return new FormationResult(false, true, null);
    }

    @Override
    public FormationResult addDot(Dot dot) {
        return new FormationResult(false, false, null);
    }

    @Override
    public FormationResult addExpression(CalcExpression expression) {
        return new FormationResult(false, false, null);
    }

    @Override
    public FormationResult addNumber(Number expression) {
        return new FormationResult(false, false, null);
    }
}
