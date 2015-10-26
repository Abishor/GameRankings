package model;

import static com.google.common.base.Preconditions.checkNotNull;

public class Pair<T, V> {
    public final T first;
    public final V second;

    public Pair(final T first, final V second) {
        checkNotNull(first, "A key must be specified");
        checkNotNull(second, "A value must be specified");
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair<" + first +
                ", " + second +
                '>';
    }
}
