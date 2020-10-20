import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Match {
    private String player1;
    private String player2;
    private String winner;
    private int player1GamesWonInCurrentSet;
    private int player2GamesWonInCurrentSet;
    private int player1ScoreGame;
    private int player2ScoreGame;
    private int player1ScoreSet;
    private int player2ScoreSet;
    private Map<Integer, String> gameScoreValues = new HashMap<Integer, String>() {{
        put(0, "0");
        put(1, "15");
        put(2, "30");
        put(3, "40");
        put(4, "ad");
    }};
    private boolean matchSetIsOnTieBreak;


    public Match(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {
        this.matchSetIsOnTieBreak = false;
        //player1
        this.player1ScoreGame = 0;
        this.player1ScoreSet = 0;
        this.player1GamesWonInCurrentSet = 0;
        //player2
        this.player2ScoreGame = 0;
        this.player2ScoreSet = 0;
        this.player2GamesWonInCurrentSet = 0;
    }

    private void resetGameScore() {
        this.player1ScoreGame = 0;
        this.player2ScoreGame = 0;

    }

    private void resetSetScore() {
        this.setPlayer1GamesWonInCurrentSet(0);
        this.setPlayer2GamesWonInCurrentSet(0);
        this.matchSetIsOnTieBreak = false;
    }


    public void player1Scored() {
        int newScore = this.player1ScoreGame + 1;
        if (newScore == 4 && this.player2ScoreGame == this.player1ScoreGame) {
            this.player1ScoreGame++;
        } else {
            this.player1ScoreGame++;
            this.checkGameWinner();
        }
    }


    public void player2Scored() {
        int newScore = this.player2ScoreGame + 1;
        if (newScore == 4 && this.player2ScoreGame == this.player1ScoreGame) {
            this.player2ScoreGame++;
        } else {
            this.player2ScoreGame++;
            this.checkGameWinner();
        }
    }

    private void checkGameWinner() {
        if (this.player1ScoreGame > this.player2ScoreGame) {
            if (this.player1ScoreGame >= 3) {
                this.player1GamesWonInCurrentSet++;
                this.resetGameScore();
            }
        } else if (this.player1ScoreGame < this.player2ScoreGame) {
            if (this.player2ScoreGame >= 3) {
                this.player2GamesWonInCurrentSet++;
                this.resetGameScore();
            }
        }
        this.checkSetWinner();
    }

    private void checkSetWinner() {

        if (this.player1GamesWonInCurrentSet == 6 && this.player2GamesWonInCurrentSet == 6) {
            this.matchSetIsOnTieBreak = true;
        } else if (this.player1GamesWonInCurrentSet >= 6 && this.player1GamesWonInCurrentSet - this.player2GamesWonInCurrentSet >= 2) {

            this.player1ScoreSet++;
            this.resetSetScore();
        } else if (this.player2GamesWonInCurrentSet >= 6 && this.player2GamesWonInCurrentSet - this.player1GamesWonInCurrentSet >= 2) {

            this.player1ScoreSet++;
            this.resetSetScore();

        }

        this.checkMatchWinner();
    }


    private void checkMatchWinner() {
        if (this.player1ScoreSet >= 3 && this.player2ScoreSet < this.player1ScoreSet) {
            this.winner = this.player1;
        } else if (this.player2ScoreSet >= 3 && this.player1ScoreSet < this.player2ScoreSet) {
            this.winner = this.player2;
        }
    }


}
