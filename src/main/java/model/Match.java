package model;

public class Match {
    private final Pair<Integer, Integer> players;

    public Match(final Pair<Integer, Integer> players) {
        this.players = players;
    }

    public Pair<Integer, Integer> getPlayers() {
        return players;
    }

    public Integer winer() {
        return players.first;
    }

    public Integer loser() {
        return players.second;
    }

    @Override
    public String toString() {
        return "Match{" +
                players.first + " vs " + players.second +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Match match = (Match) o;

        return players.equals(match.players);

    }

    @Override
    public int hashCode() {
        return players.hashCode();
    }
}
