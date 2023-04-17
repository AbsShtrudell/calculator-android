package by.shtrudell.expression;

import androidx.annotation.NonNull;

public class Bracket implements Primitive{
    private boolean left = true;
    private boolean blank = false;
    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
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
        return formater.addBracket(this);
    }

    @Override
    public Formater getFormater() {
        return new BracketFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new Bracket();
    }

    @Override
    public String viewStyle() {
        return parseStyle();
    }

    @Override
    public String parseStyle() {
        return left? "(" : ")";
    }

    private class BracketFormater implements Formater {

        @Override
        public FormationResult addDigit(Digit digit) {
            return new FormationResult(false, true, left? null : Operator.MULTIPLICATION);
        }

        @Override
        public FormationResult addOperator(Operator operator) {
            return new FormationResult(false, !left, null);
        }

        @Override
        public FormationResult addBracket(Bracket bracket) {
            return new FormationResult(false, true, !left && bracket.isLeft() ? Operator.MULTIPLICATION : null);
        }

        @Override
        public FormationResult addFunction(Function function) {
            return new FormationResult(false, true, left? null : Operator.MULTIPLICATION);
        }

        @Override
        public FormationResult addDot(Dot dot) {
            return new FormationResult(false, false, null);
        }
    }
}
