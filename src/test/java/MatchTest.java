
import org.junit.Test;

import static org.junit.Assert.*;

public class MatchTest {
    private static final String ROGER_FEDERRER = "Roger Federrer";
    private static final String NOVAK_DJOKOVIC = "Novak Djokovic";

    @Test
    public void player1_scored_first_point_of_a_game() {
        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.player1Scored();
        assertEquals(1, game.getPlayer1ScoreGame());
    }


    @Test
    public void player2_scored_first_point_of_a_game() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.player2Scored();
        assertEquals(1, game.getPlayer2ScoreGame());
    }

    @Test
    public void player1_scored_wins_game_given_his_current_score_is_30_and_player2_score_is_below_30() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1ScoreGame(2);
        game.setPlayer2ScoreGame(1);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(1,game.getPlayer1GamesWonInCurrentSet());
    }

    @Test
    public void player1_scored_wins_game_given_his_current_score_is_30_and_player2_score_is_30() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1ScoreGame(2);
        game.setPlayer2ScoreGame(2);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(1,game.getPlayer1GamesWonInCurrentSet());
    }

    @Test
    public void player2_scored_get_advantage_given_the_current_game_score_is_40_40() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1ScoreGame(3);
        game.setPlayer2ScoreGame(3);
        game.player1Scored();
        assertEquals(4, game.getPlayer1ScoreGame());

    }

    @Test
    public void player1_scored_wins_game_given_he_has_advantage() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1ScoreGame(4);
        game.setPlayer2ScoreGame(3);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(0, game.getPlayer2ScoreGame());
        assertEquals(1, game.getPlayer1GamesWonInCurrentSet());
    }

    @Test
    public void player1_wins_set_given_he_has_5_games_won_in_current_set_and_player2_set_score_is_strictly_less_than_5() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1GamesWonInCurrentSet(5);
        game.setPlayer2GamesWonInCurrentSet(4);
        game.setPlayer1ScoreGame(3);
        game.player1Scored();
        assertEquals(1,game.getPlayer1ScoreSet());

    }

    @Test
    public void player1_wins_set_given_he_has_6_games_won_in_current_set_and_player2_set_score_is_equal_to_5() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1GamesWonInCurrentSet(6);
        game.setPlayer2GamesWonInCurrentSet(5);
        game.setPlayer1ScoreGame(2);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(0, game.getPlayer2ScoreGame());
        assertEquals(0, game.getPlayer1GamesWonInCurrentSet());
        assertEquals(1,game.getPlayer1ScoreSet());
        game.setPlayer1GamesWonInCurrentSet(5);
        game.setPlayer1ScoreGame(3);
        game.player1Scored();
        assertEquals(2,game.getPlayer1ScoreSet());

    }

    @Test
    public void set_is_on_tie_break_when_player1_and_player2_are_on_tie_score_6_6() {

        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1GamesWonInCurrentSet(5);
        game.setPlayer2GamesWonInCurrentSet(6);
        game.setPlayer1ScoreGame(2);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(0, game.getPlayer2ScoreGame());
        assertEquals(6, game.getPlayer1GamesWonInCurrentSet());
        assertTrue(game.isMatchSetIsOnTieBreak());

    }


    @Test
    public void player1_wins_match_given_player_one_is_ahead_by_2_sets(){
        Match game = new Match(ROGER_FEDERRER, NOVAK_DJOKOVIC);
        game.startGame();
        game.setPlayer1ScoreSet(2);
        game.setPlayer2ScoreSet(1);
        game.setPlayer1GamesWonInCurrentSet(5);
        game.setPlayer2GamesWonInCurrentSet(4);
        game.setPlayer1ScoreGame(2);
        game.player1Scored();
        assertEquals(0, game.getPlayer1ScoreGame());
        assertEquals(0, game.getPlayer2ScoreGame());
        assertEquals(0, game.getPlayer1GamesWonInCurrentSet());
        assertEquals(ROGER_FEDERRER,game.getWinner());
    }


}
