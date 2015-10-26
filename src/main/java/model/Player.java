package model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Class modeling a competitor
 */
public class Player implements Comparable {
    private final Integer id;
    private final String name;

    private Integer winCount, lossCount, gamesPlayed;
    /* The score will be computed according to the following protocol:
    * score += 10 for a win
    * score -= 5 for a loss
    * no change for draw
    */
    private Integer score;

    private Double ranking;

    public Player(final Integer id, final String name, final Double ranking) {
        checkNotNull(id, "An identifier must be supplied");
        checkArgument(!name.isEmpty(), "A name must be supplied");

        this.id = id;
        this.name = name;
        this.ranking = ranking;
        winCount = 0;
        lossCount = 0;
        gamesPlayed = 0;
        score = 0;
    }

    public Player(final Integer id, final String name) {
        this(id, name, 1000.0);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getWinCount() {
        return winCount;
    }

    public Integer getLossCount() {
        return lossCount;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public Integer getScore() {
        return score;
    }

    public Double getRanking() {
        return ranking;
    }

    public void handleWin(final Double diff) {
        ranking += diff;
        winCount++;
        gamesPlayed++;
        score += 10;
    }

    public void handleLoss(final Double diff) {
        ranking -= diff;
        lossCount++;
        gamesPlayed++;
        score -= 5;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Player player = (Player) o;

        return id == player.id && winCount == player.winCount && name.equals(player.name) && lossCount == player.lossCount;

    }

    @Override
    public int compareTo(final Object o) {
        if (o == null || getClass() != o.getClass()) return 1;

        final Player player = (Player) o;

        if (ranking == player.ranking) {
            if (winCount == player.winCount) {
                if (lossCount == player.lossCount) {
                    return 0;
                }
                return lossCount < player.lossCount ? 1 : -1;
            }
        }

        return ranking > player.ranking ? 1 : -1;
    }

    @Override
    public int hashCode() {
        int result;
        final long temp;
        result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + winCount;
        result = 31 * result + lossCount;
        result = 31 * result + gamesPlayed;
        result = 31 * result + score;
        temp = Double.doubleToLongBits(ranking);
        result = 31 * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", winCount=" + winCount +
                ", lossCount=" + lossCount +
                ", gamesPlayed=" + gamesPlayed +
                ", score=" + score +
                ", ranking=" + ranking +
                '}';
    }
}
