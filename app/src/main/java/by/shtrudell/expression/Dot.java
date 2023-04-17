package by.shtrudell.expression;

import androidx.annotation.NonNull;

public class Dot implements Primitive{

    @Override
    public boolean deleteLast() {
        return false;
    }

    @Override
    public boolean isBlank() {
        return false;
    }

    @Override
    public FormationResult format(Formater formater) {
        return formater.addDot(this);
    }

    @Override
    public Formater getFormater() {
        return new NullFormater();
    }

    @NonNull
    @Override
    public Primitive clone() {
        return new Dot();
    }

    @Override
    public String viewStyle() {
        return ",";
    }

    @Override
    public String parseStyle() {
        return ".";
    }
}
