import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Match {
    private String player1;
    private String player2;
    private String winner;
    private String player1ScoreGame;
    private String player2ScoreGame;
    private int player1ScoreSet;
    private int player2ScoreSet;

    public Match(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void startGame() {
        //player1
        this.setPlayer1ScoreGame("0");
        this.setPlayer1ScoreSet(0);
        //player2
        this.setPlayer2ScoreSet(0);
        this.setPlayer2ScoreGame("0");
    }


    public void player1Scored() {
        String previousScore = this.getPlayer1ScoreGame();
        switch (previousScore) {
            case "0":
                this.setPlayer1ScoreGame("15");
                break;
            case "15":
                this.setPlayer1ScoreGame("30");
                break;
            case "30":
                this.setPlayer1ScoreGame("40");
                break;
            case "40":
                if (this.getPlayer2ScoreGame().equals("40")) {
                    this.setPlayer1ScoreGame("Ad");
                } else {
                    this.resetGameScore();
                    this.setPlayer1ScoreSet(this.getPlayer1ScoreSet()+1);
                    this.checkGameWinner();
                }
                break;
            case "Ad":
                this.resetGameScore();
                this.setPlayer1ScoreSet(this.getPlayer1ScoreSet()+1);
                this.checkGameWinner();

                break;
            default:
                break;
        }
    }

    private void resetGameScore(){
        this.setPlayer1ScoreGame("0");
        this.setPlayer2ScoreGame("0");
    }


    public void player2Scored() {
        String previousScore= this.getPlayer2ScoreGame();
        switch (previousScore) {
            case "0":
                this.setPlayer2ScoreGame("15");
                break;
            case "15":
                this.setPlayer2ScoreGame("30");
                break;
            case "30":
                this.setPlayer2ScoreGame("40");
                break;
            case "40":
                if (this.getPlayer1ScoreGame().equals("40")) {
                    this.setPlayer2ScoreGame("Ad");
                } else {
                    this.resetGameScore();
                    this.setPlayer2ScoreSet(this.getPlayer2ScoreSet()+1);
                    this.checkGameWinner();
                }
                break;
            case "Ad":
                this.resetGameScore();
                this.setPlayer2ScoreSet(this.getPlayer2ScoreSet()+1);
                this.checkGameWinner();
                break;
            default:
                break;
        }
    }

    private void checkGameWinner(){
        if(this.getPlayer1ScoreSet()>=3 && this.getPlayer2ScoreSet()<this.getPlayer1ScoreSet()){
            this.setWinner(this.player1);
        }else if(this.getPlayer2ScoreSet()>=3 && this.getPlayer1ScoreSet()<this.getPlayer2ScoreSet()){
            this.setWinner(this.player2);
        }
    }
}
