import model.Competition;
import model.MatchHistory;
import model.Ranking;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import writer.WriterImpl;

import java.util.Scanner;

public class GameRanking {
    private static final Scanner in = new Scanner(System.in);

    private static MatchHistory matchHistory;
    private static Ranking ranking;

    private static final String PROMPT =
            "Please select the criteria for ranking:\n" +
                    "1. " + Competition.SortingCriteria.DEFAULT.name() + "\n" +
                    "2. " + Competition.SortingCriteria.GAMES.name() + "\n" +
                    "3. " + Competition.SortingCriteria.LOSS.name() + "\n" +
                    "4. " + Competition.SortingCriteria.RANK.name() + "\n" +
                    "5. " + Competition.SortingCriteria.SCORE.name() + "\n" +
                    "6. " + Competition.SortingCriteria.WIN.name() + "\n" +
                    "7. Generate report \n" +
                    "0. EXIT";

    public static void main(final String[] args) {
        new ClassPathXmlApplicationContext("spring/bootstrap.xml");

        if (matchHistory != null && ranking != null) {
            ranking.populateData(matchHistory);

            int choice = -1;

            while (choice != 0) {
                System.out.println(PROMPT);

                System.out.println("Please input choice...");
                choice = readInt();

                while (choice < 0 || choice > 7) {
                    System.out.println("Please input choice...");
                    choice = readInt();
                }

                switch (choice) {
                    case 0:
                        return;
                    case 1:
                        System.out.print(new WriterImpl().getDescDefaultRanking(ranking.getCompetition()));
                        break;
                    case 2:
                        System.out.print(new WriterImpl().getRankingByGamesPlayed(ranking.getCompetition()));
                        break;
                    case 3:
                        System.out.print(new WriterImpl().getRankingByNumberOfLosses(ranking.getCompetition()));
                        break;
                    case 4:
                        System.out.print(new WriterImpl().getDescRanking(ranking.getCompetition()));
                        break;
                    case 5:
                        System.out.print(new WriterImpl().getRankingByScore(ranking.getCompetition()));
                        break;
                    case 6:
                        System.out.print(new WriterImpl().getRankingByNumberOfWins(ranking.getCompetition()));
                        break;
                    case 7:
                        System.out.println("Please input player (name or id)...");
                        final String input = in.nextLine();
                        final int id = parseInt(input);
                        if (id != -1) {
                            System.out.println(ReportGenerator.getReport(id, matchHistory, ranking.getCompetition()));
                        } else {
                            System.out.println(ReportGenerator.getReport(input, matchHistory, ranking.getCompetition()));
                        }
                        break;
                }
            }
        } else {
            System.out.println("System not correctly instantiated");
        }
    }

    private static int readInt() {
        int position = -1;
        try {
            position = Integer.parseInt(in.nextLine());
        } catch (final NumberFormatException e) {
            System.out.println("Please input a valid choice...");
        }
        return position;
    }

    private static int parseInt(final String input) {
        try {
            return Integer.parseInt(input);
        } catch (final NumberFormatException e) {
            System.out.println("Please input a valid choice...");
            return -1;
        }
    }

    public static void setMatchHistory(final MatchHistory matchHistory) {
        GameRanking.matchHistory = matchHistory;
    }

    public static void setRanking(final Ranking ranking) {
        GameRanking.ranking = ranking;
    }
}
