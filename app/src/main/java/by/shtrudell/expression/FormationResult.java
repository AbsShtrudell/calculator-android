package by.shtrudell.expression;

import java.util.Objects;

public final class FormationResult {
    private final boolean attached;
    private final boolean validContinuation;
    private final Primitive connectingPrimitive;

    FormationResult(boolean attached, boolean validContinuation, Primitive connectingPrimitive) {
        this.attached = attached;
        this.validContinuation = validContinuation;
        this.connectingPrimitive = connectingPrimitive;
    }

    public boolean attached() {
        return attached;
    }

    public boolean validContinuation() {
        return validContinuation;
    }

    public Primitive connectingPrimitive() {
        return connectingPrimitive;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (FormationResult) obj;
        return this.attached == that.attached &&
                this.validContinuation == that.validContinuation &&
                Objects.equals(this.connectingPrimitive, that.connectingPrimitive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attached, validContinuation, connectingPrimitive);
    }

    @Override
    public String toString() {
        return "FormationResult[" +
                "attached=" + attached + ", " +
                "validContinuation=" + validContinuation + ", " +
                "connectingPrimitive=" + connectingPrimitive + ']';
    }


}
