package writer;

import model.Competition;

public interface Writer<T extends Competition, V> {
    V getDescDefaultRanking(final T competition);

    V getRankingByScore(final T competition);

    V getRankingByGamesPlayed(final T competition);

    V getRankingByNumberOfWins(final T competition);

    V getRankingByNumberOfLosses(final T competition);

    V getDescRanking(final T competition);
}
