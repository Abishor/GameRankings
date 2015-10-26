import model.*;

import java.util.Collections;

public class ReportGenerator {
    public static String getReport(final Integer id, final MatchHistory matchHistory, final Competition competition) {
        final Player currentPlayer = findById(id, competition);

        if (currentPlayer == null) {
            return "The information did not match any player on record";
        }

        final StringBuilder result = new StringBuilder(currentPlayer.getName()).append("\n");

        for (final Match match : matchHistory.getMatches()) {
            final Pair<Integer, Integer> players = match.getPlayers();
            if (players.first.equals(id)) {
                final Player adversary = findById(players.second, competition);
                if (adversary != null) {
                    result.append("* won against ").append(adversary.getId()).append(" - ").append(adversary.getName()).append("\n");
                }
            }
            if (players.second.equals(id)) {
                final Player adversary = findById(players.first, competition);
                if (adversary != null) {
                    result.append("* lost against ").append(adversary.getId()).append(" - ").append(adversary.getName()).append("\n");
                }
            }
        }
        return result.toString();
    }

    public static String getReport(final String name, final MatchHistory matchHistory, final Competition competition) {
        final Player player = findByName(name, competition);

        if (player == null) {
            return "The information did not match any player on record";
        }

        return getReport(player.getId(), matchHistory, competition);
    }

    private static Player findById(final Integer id, final Competition competition) {
        final int index = Collections.binarySearch(competition.getPlayers(), new Player(id, "DummyName"), (o1, o2) -> o1.getId().compareTo(o2.getId()));
        if (index < 0) {
            return null;
        }
        return competition.getPlayers().get(index);
    }

    private static Player findByName(final String name, final Competition competition) {
        for (final Player player : competition.getPlayers()) {
            if (player.getName().equalsIgnoreCase(name)) {
                return findById(player.getId(), competition);
            }
        }
        return null;
    }
}
