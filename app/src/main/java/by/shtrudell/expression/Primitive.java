package by.shtrudell.expression;

import androidx.annotation.NonNull;

public interface Primitive {
    boolean deleteLast();
    boolean isBlank();
    FormationResult format(Formater formater);
    Formater getFormater();
    Primitive clone();
    String viewStyle();
    String parseStyle();
}
