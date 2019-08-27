package com.company;

public class Treasure_ implements GameState {
    private GameState beginningOfTheGame;
    private GameState middleOfTheGame;
    private GameState endOfTheGame;
    private GameState state;
    private boolean takeTurnAttack;

    private PlayerData player1Data;
    private PlayerData player2Data;
    private PlayerScreen player1 ;
    private PlayerScreen player2;

    private Treasure_() {
        player1 = new PlayerScreen("Player1", true,this);
        player2 = new PlayerScreen("Player2", false,this);
        player1Data = new PlayerData(player1);
        player2Data = new PlayerData(player2);
        beginningOfTheGame = new BeginningOfTheGame(this, player1,player2);
        middleOfTheGame = new MiddleOfTheGame(this, player1,player2);
        endOfTheGame = new EndOFTheGame(this, player1,player2);
        this.state = beginningOfTheGame;
    }

    public static void main(String[] args) {
        Treasure_ game = new Treasure_();
        game.player1Turn();
        game.player2turn();
    }

    public void player1Turn() {
        state.player1Turn();
    }

    public void player2turn() {
        state.player2turn();
    }

    public GameState getMiddleOfTheGame() {
        return middleOfTheGame;
    }

    public GameState getEndOfTheGame() {
        return endOfTheGame;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setTakeTurnAttack(boolean isPlayerTurn){
        this.takeTurnAttack = isPlayerTurn;
    }

    public boolean getTakeTurnAttack() {
        return takeTurnAttack;
    }

    public PlayerData getPlayer2Data() {
        return player2Data;
    }

    public PlayerData getPlayer1Data() {
        return player1Data;
    }

    public PlayerScreen getPlayer1() {
        return player1;
    }

    public PlayerScreen getPlayer2() {
        return player2;
    }
}