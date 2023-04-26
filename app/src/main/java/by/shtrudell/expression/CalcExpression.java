package by.shtrudell.expression;

import android.util.Pair;

import androidx.annotation.NonNull;

import java.util.Stack;

public class CalcExpression implements Primitive{

    private Stack<Pair<Integer, Primitive>> primitiveStack = new Stack<>();

    @Override
    public boolean deleteLast() {
        if(primitiveStack.empty()) return false;

        var last = primitiveStack.lastElement().second;
        last.deleteLast();

        if(last.isBlank())
            primitiveStack.pop();

        return !isBlank();
    }

    @Override
    public boolean isBlank() {
        return primitiveStack.empty();
    }

    @Override
    public FormationResult format(Formater formater) {
        return new FormationResult(false, true, null);
    }

    @Override
    public Formater getFormater() {
        return new ExpressionFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new CalcExpression();
    }

    @Override
    public String viewStyle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Pair<Integer, Primitive> primitive: primitiveStack)
            stringBuilder.append(primitive.second.viewStyle());

        return stringBuilder.toString();
    }

    @Override
    public String parseStyle() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Pair<Integer, Primitive> primitive: primitiveStack)
            stringBuilder.append(primitive.second.parseStyle());

        return stringBuilder.toString();
    }

    public void clear() {
        primitiveStack.clear();
    }

    private class ExpressionFormater extends NullFormater {

        @Override
        public FormationResult addDigit(Digit digit) {
            if(digit == null) return new FormationResult(false, false, null);

            if(primitiveStack.size() > 0) {
                var result = primitiveStack.lastElement().second.getFormater().addDigit(digit);
                if(result.validContinuation() && !result.attached()) {
                    var num = new Number();
                    num.getFormater().addDigit(digit);

                    primitiveStack.push(new Pair<>(primitiveStack.lastElement().first, num));
                }
            }
            else {
                var num = new Number();
                num.getFormater().addDigit(digit);

                primitiveStack.push(new Pair<>(0, num));
            }

            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addOperator(Operator operator) {
            if(operator == null) return new FormationResult(false, false, null);

            if(primitiveStack.size() > 0) {
                var last = primitiveStack.lastElement();

                var result = last.second.getFormater().addOperator(operator);
                if(result.validContinuation() && !result.attached())
                    primitiveStack.push(new Pair<>(last.first, operator));
            }
            else primitiveStack.push(new Pair<>(0, operator));

            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addBracket(Bracket bracket) {
            if(bracket == null) return new FormationResult(false, false, null);

            if(primitiveStack.size() > 0) {
                var result = primitiveStack.lastElement().second.getFormater().addBracket(bracket);

                if(result.validContinuation() && !result.attached()) {
                    if(primitiveStack.lastElement().first > 0) {
                        if(result.connectingPrimitive() == null) {
                            bracket.setLeft(true);
                            primitiveStack.push(new Pair<>(primitiveStack.lastElement().first + 1, bracket));
                        }
                        else  {
                            bracket.setLeft(false);
                            primitiveStack.push(new Pair<>(primitiveStack.lastElement().first - 1, bracket));
                        }
                    }
                    else {
                        bracket.setLeft(true);
                        if(result.connectingPrimitive() != null)
                            primitiveStack.push(new Pair<>(primitiveStack.lastElement().first, result.connectingPrimitive()));

                        primitiveStack.push(new Pair<>(primitiveStack.lastElement().first + 1, bracket));
                    }
                }
            }
            else primitiveStack.push(new Pair<>(1, bracket));

            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addFunction(Function function) {
            if(function == null) return new FormationResult(false, false, null);

            if(primitiveStack.size() > 0) {
                var result = primitiveStack.lastElement().second.getFormater().addFunction(function);

                if(result.validContinuation() && !result.attached()) {
                    if(result.connectingPrimitive() != null)
                        primitiveStack.push(new Pair<>(primitiveStack.lastElement().first, result.connectingPrimitive()));

                    primitiveStack.push(new Pair<>(primitiveStack.lastElement().first + 1, function));
                }
            }
            else
                primitiveStack.push(new Pair<>(1, function));

            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addDot(Dot dot) {
            if(primitiveStack.empty() || dot == null) return new FormationResult(false, false, null);

            primitiveStack.lastElement().second.getFormater().addDot(dot);

            return new FormationResult(false, true, null);
        }

        @Override
        public FormationResult addNumber(Number number) {
            if(number == null) return new FormationResult(false, false, null);

            if(primitiveStack.size() > 0) {
                var result = primitiveStack.lastElement().second.getFormater().addNumber(number);

                if(result.validContinuation() && !result.attached()) {
                    if(result.connectingPrimitive() != null)
                        primitiveStack.push(new Pair<>(primitiveStack.lastElement().first, result.connectingPrimitive()));

                    primitiveStack.push(new Pair<>(primitiveStack.lastElement().first, number));
                }
            }
            else
                primitiveStack.push(new Pair<>(0, number));

            return new FormationResult(false, true, null);
        }
    }
}
