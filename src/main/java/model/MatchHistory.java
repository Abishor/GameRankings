package model;

import reader.Reader;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class MatchHistory {
    private final Reader<Integer, Integer> reader;
    private final List<Match> matches;

    public MatchHistory(final Reader reader) {
        checkNotNull(reader);
        this.reader = reader;
        final List<Pair<Integer, Integer>> pairs = reader.read();
        checkNotNull(pairs);

        matches = new LinkedList<>();
        if (!pairs.isEmpty()) {
            matches.addAll(pairs.stream().map(Match::new).collect(Collectors.toList()));
        }
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void addMatch(final Pair<Integer, Integer> match) {
        matches.add(new Match(match));
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "matches=" + matches +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final MatchHistory matchHistory = (MatchHistory) o;

        return matches.equals(matchHistory.matches);

    }

    @Override
    public int hashCode() {
        return matches.hashCode();
    }
}
