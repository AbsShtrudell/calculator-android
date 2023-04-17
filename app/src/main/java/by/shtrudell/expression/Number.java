package by.shtrudell.expression;

import android.util.Pair;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Number implements Primitive{
    private final List<Digit> digits;
    private Pair<Integer, Dot> dotPosition;

    public Number(Digit[] digits) {
        this.digits = List.of(digits);
    }

    public Number() {
        digits = new ArrayList<>();
    }

    @Override
    public boolean deleteLast() {
        if(isBlank()) return false;

        if(dotPosition != null && dotPosition.first == digits.size())
            dotPosition = null;
        else digits.remove(digits.size() - 1);

        return true;
    }

    @Override
    public boolean isBlank() {
        return digits.isEmpty();
    }

    @Override
    public FormationResult format(Formater formater) {
        return new FormationResult(false, true, null);
    }

    @Override
    public Formater getFormater() {
        return new NumFormater();
    }

    @Override
    public Primitive clone() {
        return new Number();
    }

    @Override
    public String viewStyle() {
        var stringBuilder = new StringBuilder();

        for(var digit : digits)
            stringBuilder.append(digit.viewStyle());

        if(dotPosition != null)
            stringBuilder.insert(dotPosition.first, dotPosition.second.viewStyle());

        return stringBuilder.toString();
    }

    @Override
    public String parseStyle() {
        var stringBuilder = new StringBuilder();

        for(var digit : digits)
            stringBuilder.append(digit.parseStyle());

        if(dotPosition != null)
            stringBuilder.insert(dotPosition.first, dotPosition.second.parseStyle());

        return stringBuilder.toString();
    }

    private class NumFormater implements Formater {

        @Override
        public FormationResult addDigit(Digit digit) {
            if(digit == null) return new FormationResult(false, false, null);

            if(digits.size() == 1 && digits.get(0).getValue() == '0' && dotPosition == null)
                digits.remove(0);

            digits.add(digit);

            return new FormationResult(true, true, null);
        }

        @Override
        public FormationResult addOperator(Operator operator) {
            if(operator == null) return new FormationResult(false, false, null);
            else return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addBracket(Bracket bracket) {
            if(bracket == null) return new FormationResult(false, false, null);
            else return new FormationResult(false, true, bracket.isLeft()? Operator.MULTIPLICATION : null);
        }

        @Override
        public FormationResult addFunction(Function function) {
            if(function == null) return new FormationResult(false, false, null);
            else return new FormationResult(false, true, Operator.MULTIPLICATION);
        }

        @Override
        public FormationResult addDot(Dot dot) {
            if(dot == null || dotPosition != null) return new FormationResult(false, false, null);

            dotPosition = new Pair<>(digits.size(), dot);
            return new FormationResult(true, true, null);
        }
    }
}
