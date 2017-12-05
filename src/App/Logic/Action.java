package App.Logic;

import java.io.Serializable;


/**
 * Represents action taken on a file.
 * Objects of this class serve in a communication process between GUI and server/client.
 * */
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