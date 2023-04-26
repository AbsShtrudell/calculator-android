package by.shtrudell.expression;

import androidx.annotation.NonNull;

import java.util.Locale;

public class Operator implements Primitive{

    public static final Operator MULTIPLICATION = new Operator("×", "*");
    public static final Operator DIVISION = new Operator("÷", "/");
    public static final Operator ADDITION = new Operator("+", "+");
    public static final Operator SUBSTRACTION = new Operator("-", "-");
    public static final Operator MODULE = new Operator("%", "%");
    public static final Operator EXPONENTS = new Operator("^", "^");

    private static Operator createConstant(String view, String parse) {
        return new Operator(view, parse);
    }

    private final String view;
    private final String parse;
    private boolean blank = false;
    public Operator(String view, String parse) {
        this.view = view;
        this.parse = parse;
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
        return formater.addOperator(this);
    }

    @Override
    public Formater getFormater() {
        return new OperatorFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new Operator(view, parse);
    }

    @Override
    public String viewStyle() {
        return view;
    }

    @Override
    public String parseStyle() {
        return parse;
    }

    private class OperatorFormater extends NullFormater {

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
        public FormationResult addNumber(Number expression) {
            return new FormationResult(false, true, null);
        }
    }
}
