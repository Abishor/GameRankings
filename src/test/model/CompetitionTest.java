package model;


import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CompetitionTest {
    Competition competition;

    List<Pair<Integer, String>> PLAYERS;
    List<Pair<Integer, Integer>> MATCHES;

    @Before
    public void setup() {
        PLAYERS = Arrays.asList(new Pair<>(1, "Alice"), new Pair<>(2, "Bob"), new Pair<>(3, "Charles"), new Pair<>(4, "Dana"));
        MATCHES = Arrays.asList(new Pair<>(1, 2), new Pair<>(1, 3), new Pair<>(4, 1), new Pair<>(2, 4));

        competition = new Competition(null);
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyConstruct() {
        new Competition(null);
    }

    @Test
    public void testConstruct() throws Exception {
        final Competition competition = new Competition(null);
        assertNotNull(competition);
    }

    @Test
    public void testSetUpMatches() {

    }

    public void testGetSortedPlayers() throws Exception {

    }

    public void testUpdateRank() throws Exception {

    }

    public void testToString() throws Exception {

    }
}