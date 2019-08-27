package com.company;

import org.jetbrains.annotations.Contract;

public class MiddleOfTheGame implements GameState{
    private Treasure_ treasure;
    private PlayerScreen player1;
    private PlayerScreen player2;

    @Contract(pure = true)
    MiddleOfTheGame(Treasure_ treasure, PlayerScreen player1, PlayerScreen player2){
        this.treasure = treasure;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void player1Turn (){
        player1.getSelfGrid().setSelfGridListener(false);
        player1.getAttackGrid().setAttackGridListener(true);
    }
    public void player2turn (){
        player2.getSelfGrid().setSelfGridListener(false);
        player2.getAttackGrid().setAttackGridListener(true);
    }

}