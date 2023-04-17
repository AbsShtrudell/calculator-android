package by.shtrudell.expression;

public interface Formater {
    FormationResult addDigit(Digit digit);
    FormationResult addOperator(Operator operator);
    FormationResult addBracket(Bracket bracket);
    FormationResult addFunction(Function function);
    FormationResult addDot(Dot dot);
}
