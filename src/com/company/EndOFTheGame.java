package com.company;

import org.jetbrains.annotations.Contract;

public class EndOFTheGame implements GameState {
    private Treasure_ treasure;
    private PlayerScreen player1;
    private PlayerScreen player2;

    @Contract(pure = true)
    EndOFTheGame(Treasure_ treasure, PlayerScreen player1, PlayerScreen player2){
        this.treasure = treasure;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void player1Turn (){
        System.exit(0);
    }
    public void player2turn (){
        System.exit(0);
    }
}