package reader;

import model.Pair;

import java.util.List;

public interface Reader<T, V> {
    List<Pair<T, V>> read();
}
