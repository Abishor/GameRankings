package model;

import reader.Reader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class Competition {
    private final Reader<Integer, String> reader;
    private final List<Player> players;

    public Competition(final Reader reader) {
        checkNotNull(reader);
        this.reader = reader;

        final List<Pair<Integer, String>> players = reader.read();
        checkNotNull(players);

        this.players = new ArrayList<>();
        this.players.addAll(players.stream().map(player -> new Player(player.first, player.second)).collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Player> getSortedPlayers(final Competition.SortingCriteria criteria) {
        final List<Player> result = new ArrayList<>(players);
        switch (criteria) {
            case DEFAULT:
                Collections.sort(result);
                break;
            case LOSS:
                Collections.sort(result, (o1, o2) -> o1.getLossCount().compareTo(o2.getLossCount()));
                break;
            case RANK:
                Collections.sort(result, (o1, o2) -> o1.getRanking().compareTo(o2.getRanking()) * -1);
                break;
            case SCORE:
                Collections.sort(result, (o1, o2) -> o1.getScore().compareTo(o2.getScore()));
                break;
            case WIN:
                Collections.sort(result, (o1, o2) -> o1.getWinCount().compareTo(o2.getWinCount()));
                break;
            case GAMES:
                Collections.sort(result, (o1, o2) -> o1.getGamesPlayed().compareTo(o2.getGamesPlayed()));
                break;
        }
        return result;
    }

    public void updateRank(final int winnerId, final int loserId) {
        final Player winner = players.get(winnerId), loser = players.get(loserId);

        if (winner != null && loser != null) {
            elo(winner, loser);
        }
    }

    public List<Pair<Player, Player>> setUpMatches() {
        final List<Player> playedGames = getSortedPlayers(Competition.SortingCriteria.GAMES);
        final List<Pair<Player, Player>> result = new LinkedList<>();

        Player firstPlayer = null;
        for (final Player player : playedGames) {
            if (firstPlayer == null) {
                firstPlayer = player;
            } else {
                result.add(new Pair<>(firstPlayer, player));
                firstPlayer = null;
            }
        }
        return result;
    }

    private static void elo(final Player winner, final Player loser) {
        final double ratingDiff = loser.getRanking() - winner.getRanking();
        final double expectedScore = 1 / (1 + Math.pow(10, ratingDiff / 400));

        // Players below 2100 - ICC seems to adopt a global K=32
        final int factor = 32;
        final double scoreChange = factor * (1 - expectedScore);
        winner.handleWin(scoreChange);
        loser.handleLoss(scoreChange);
    }


    @Override
    public String toString() {
        return "Competition{" +
                "Players=" + players +
                '}';
    }

    public enum SortingCriteria {
        RANK, SCORE, WIN, LOSS, DEFAULT, GAMES
    }
}
