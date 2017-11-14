package Utils;

public class Pair<S, T> {
    private S a;
    private T b;

    public Pair(S a, T b) {
        this.a = a;
        this.b = b;
    }

    public S first() {
        return this.a;
    }

    public T second() {
        return this.b;
    }
}
