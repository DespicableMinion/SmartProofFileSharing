package App.Logic;

import java.io.Serializable;


public class Action implements Serializable {
    protected static final long serialVersionUID = 1112122200L;

    public enum Type { MODIFY, SAVE, SHARE, HASH }

    private Type type;
    private String message;

    public Action(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return this.type;
    }

    public String getMessage() {
        return this.message;
    }
}