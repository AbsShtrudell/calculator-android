package by.shtrudell.expression;

import androidx.annotation.NonNull;

public class Digit implements Primitive{
    private final char value;
    private boolean blank = false;

    public Digit(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
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
        return formater.addDigit(this);
    }

    @Override
    public Formater getFormater() {
        return new NullFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new Digit(value);
    }

    @Override
    public String viewStyle() {
        return parseStyle();
    }

    @Override
    public String parseStyle() {
        return String.valueOf(value);
    }
}
