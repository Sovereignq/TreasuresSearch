package com.company;

import org.jetbrains.annotations.Contract;

public class BeginningOfTheGame implements GameState {
    private Treasure_ treasure;
    private PlayerScreen player1;
    private PlayerScreen player2;

    @Contract(pure = true)
    BeginningOfTheGame(Treasure_ treasure, PlayerScreen player1, PlayerScreen player2){
        this.player1 = player1;
        this.player2 = player2;
        this.treasure = treasure;
    }

    public void player1Turn (){
        player1.getSelfGrid().setSelfGridListener(true);
    }

    public void player2turn (){
        player2.getSelfGrid().setSelfGridListener(true);
        treasure.setState(treasure.getMiddleOfTheGame());
    }

}