package model;

import java.util.List;

public class Ranking {

    private final Competition competition;

    public Ranking(final Competition competition) {
        this.competition = competition;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void populateData(final MatchHistory matchHistory) {
        for (final Match match : matchHistory.getMatches()) {
            competition.updateRank(match.winer(), match.loser());
        }
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "competition=" + competition +
                '}';
    }

    public List<Player> getSortedPlayers(final Competition.SortingCriteria criteria) {
        return competition.getSortedPlayers(criteria);
    }
}
