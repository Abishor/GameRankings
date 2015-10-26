package writer;

import model.Competition;
import model.Player;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class WriterImpl implements Writer<Competition, String> {
    @Override
    public String getDescDefaultRanking(final Competition competition) {
        // We'd like to have the best performing player first
        checkNotNull(competition, "A competition must be supplied for representing");

        final List<Player> players = competition.getPlayers();
        checkArgument(!players.isEmpty(), "Cannot represent an empty competition");

        Collections.sort(players, (o1, o2) -> o1.compareTo(o2) * -1);
        return players.toString();
    }

    @Override
    public String getRankingByScore(final Competition competition) {
        final List<Player> data = getData(competition, Competition.SortingCriteria.SCORE);
        final StringBuilder result = new StringBuilder("Sorted by score:\n");
        for (final Player player : data) {
            result.append(player.getName()).append(" - ").append(player.getLossCount()).append(" points\n");
        }
        return result.toString();
    }

    @Override
    public String getRankingByGamesPlayed(final Competition competition) {
        final List<Player> data = getData(competition, Competition.SortingCriteria.GAMES);
        final StringBuilder result = new StringBuilder("Sorted by number of games played:\n");
        for (final Player player : data) {
            result.append(player.getName()).append(" - ").append(player.getGamesPlayed()).append(" games\n");
        }
        return result.toString();
    }

    @Override
    public String getRankingByNumberOfWins(final Competition competition) {
        final List<Player> data = getData(competition, Competition.SortingCriteria.WIN);
        final StringBuilder result = new StringBuilder("Sorted by number of wins:\n");
        for (final Player player : data) {
            result.append(player.getName()).append(" - ").append(player.getWinCount()).append(" wins\n");
        }
        return result.toString();
    }

    @Override
    public String getRankingByNumberOfLosses(final Competition competition) {
        final List<Player> data = getData(competition, Competition.SortingCriteria.LOSS);
        final StringBuilder result = new StringBuilder("Sorted by number of losses:\n");
        for (final Player player : data) {
            result.append(player.getName()).append(" - ").append(player.getLossCount()).append(" losses\n");
        }
        return result.toString();
    }

    @Override
    public String getDescRanking(final Competition competition) {
        final List<Player> data = getData(competition, Competition.SortingCriteria.RANK);
        final StringBuilder result = new StringBuilder("Best to not-so-skilled as follows:\n");
        for (final Player player : data) {
            result.append(player.getName()).append(String.format(" - %.2f\n", player.getRanking()));
        }
        return result.toString();
    }

    private static List<Player> getData(final Competition competition, final Competition.SortingCriteria criteria) {
        checkNotNull(competition, "A competition must be supplied for representing");

        final List<Player> players = competition.getSortedPlayers(criteria);
        checkArgument(!players.isEmpty(), "Cannot represent an empty competition");

        return players;
    }
}
