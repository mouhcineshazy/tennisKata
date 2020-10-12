
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

public class MatchTest {
    static final String zebida = "zebida federrer";
    static final String hamid = "hamid djokovic";

    @Test
    public void player1_scored_first_point_of_a_game() {
        Match game = new Match(zebida, hamid);
        game.startGame();
        game.player1Scored();
        assertEquals("15", game.getPlayer1ScoreGame());
    }


    @Test
    public void player2_scored_first_point_of_a_game() {

        Match game = new Match(zebida, hamid);
        game.startGame();
        game.player2Scored();
        assertEquals("15", game.getPlayer2ScoreGame());
    }

    @Test
    public void player1_scored_wins_set_given_his_current_score_was_40() {

        Match game = new Match(zebida, hamid);
        game.startGame();
        game.setPlayer1ScoreGame("40");
        game.player1Scored();
        assertEquals("0", game.getPlayer1ScoreGame());
        assertEquals(1, game.getPlayer1ScoreSet());
    }

    @Test
    public void player2_scored_get_advantage_given_the_current_game_score_is_40_40() {

        Match game = new Match(zebida, hamid);
        game.startGame();
        game.setPlayer1ScoreGame("40");
        game.setPlayer2ScoreGame("40");
        game.player1Scored();
        assertEquals("Ad", game.getPlayer1ScoreGame());

    }

    @Test
    public void player1_scored_wins_game_given_he_has_advantage() {

        Match game = new Match(zebida, hamid);
        game.startGame();
        game.setPlayer1ScoreGame("Ad");
        game.player1Scored();
        assertEquals("0", game.getPlayer1ScoreGame());
        assertEquals("0", game.getPlayer2ScoreGame());
        assertEquals(1, game.getPlayer1ScoreSet());

    }

    @Test
    public void player_wins_set_given_he_has_set_score_2() {

        Match game = new Match(zebida, hamid);
        game.startGame();
        game.setPlayer1ScoreSet(2);
        game.setPlayer1ScoreGame("40");
        game.player1Scored();
        assertEquals("0", game.getPlayer1ScoreGame());
        assertEquals("0", game.getPlayer2ScoreGame());
        assertEquals(3, game.getPlayer1ScoreSet());

    }

    @Test
    public void player_wins_match_given_he_has_won_2_sets(){
        Match game = new Match(zebida, hamid);
        game.startGame();
        game.setPlayer1ScoreSet(2);
        game.setPlayer1ScoreGame("40");
        game.player1Scored();
        assertEquals("0", game.getPlayer1ScoreGame());
        assertEquals("0", game.getPlayer2ScoreGame());
        assertEquals(3, game.getPlayer1ScoreSet());
        assertEquals(zebida, game.getWinner());
    }

    @Test
    public void no_player_wins_match_given_player_one_has_is_ahead_by_2_sets(){
        Match game = new Match(this.zebida, this.hamid);
        game.startGame();
        game.setPlayer1ScoreSet(1);
        game.setPlayer2ScoreSet(1);
        game.setPlayer1ScoreGame("40");
        game.player1Scored();
        System.out.println(game.getPlayer1());
        System.out.println(game.getWinner());
        assertEquals("0", game.getPlayer1ScoreGame());
        assertEquals("0", game.getPlayer2ScoreGame());
        assertEquals(2, game.getPlayer1ScoreSet());
        assertEquals(null, game.getWinner());
    }


}
