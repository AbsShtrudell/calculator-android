package by.shtrudell.expression;

import androidx.annotation.NonNull;

public class Function implements Primitive{
    private final String name;
    private boolean blank;
    public Function(String name) {
        this.name = name;
    }

    @Override
    public boolean deleteLast() {
        return blank = true;
    }

    @Override
    public boolean isBlank() {
        return blank;
    }

    @Override
    public FormationResult format(Formater formater) {
        return formater.addFunction(this);
    }

    @Override
    public Formater getFormater() {
        return new FuncFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new Function(name);
    }

    @Override
    public String viewStyle() {
        return parseStyle();
    }

    @Override
    public String parseStyle() {
        return name + "(";
    }

    private class FuncFormater extends NullFormater {

        @Override
        public FormationResult addDigit(Digit digit) {
            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addOperator(Operator operator) {
            return new FormationResult(false, false, null);
        }

        @Override
        public FormationResult addBracket(Bracket bracket) {
            return new FormationResult(false, bracket.isLeft(), null);
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
        public FormationResult addNumber(Number expression) {
            return new FormationResult(false, true, null);
        }
    }
}
