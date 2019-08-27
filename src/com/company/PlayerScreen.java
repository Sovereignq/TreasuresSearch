package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerScreen extends JFrame {
    int size;
    boolean isbeginningOfTheGameOfPlayer1 = true;
    boolean isbeginningOfTheGameOfPlayer2 = true;
    Treasure_ treasure;
    JLabel ownTreasureFound;
    JLabel treasureBeginning;
    JLabel enemyTreasureFound;

    public PlayerScreen(String name, boolean show, Treasure_ treasure) {
        super(name);
        this.treasure = treasure;
        this.setLayout(new BorderLayout());
        this.add(new SelfGrid(name, treasure), BorderLayout.EAST);
        this.add(new AttackGrid(name, treasure,this), BorderLayout.WEST);
        this.add(new JLabel(name), BorderLayout.NORTH);

        JButton next = new JButton("next");

        Box verticalBox = Box.createVerticalBox();

        Box horizontalBox0 = Box.createHorizontalBox();
        horizontalBox0.add(new JLabel("Статус для: "+name));
        verticalBox.add(horizontalBox0, BorderLayout.WEST);

        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(new JLabel("Сокровищ в наличии: "));
        treasureBeginning = new JLabel("" + size);
        horizontalBox1.add(treasureBeginning);
        verticalBox.add(horizontalBox1, BorderLayout.SOUTH);

        Box horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.add(new JLabel("Своих сокровищ найдено: "));
        ownTreasureFound = new JLabel("" + size);
        horizontalBox2.add(ownTreasureFound);
        verticalBox.add(horizontalBox2, BorderLayout.EAST);

        Box horizontalBox3 = Box.createHorizontalBox();
        horizontalBox3.add(new JLabel("Сокровищ оппонента найдено: "));
        enemyTreasureFound = new JLabel(""+ size);
        horizontalBox3.add(enemyTreasureFound);
        verticalBox.add(horizontalBox3);

        next.addActionListener(e -> {

            if(name.equals("Player1")){
                size = treasure.getPlayer1Data().getChest().size();
                if(isbeginningOfTheGameOfPlayer1){
                    treasureBeginning.setText(Integer.toString(size));
                    isbeginningOfTheGameOfPlayer1 = false;
                }
                if(!isbeginningOfTheGameOfPlayer1){
                    treasure.player1Turn();
                }
                hideScreen();
                treasure.getPlayer2().showScreen();
            }
            if(name.equals("Player2")){
                size = treasure.getPlayer2Data().getChest().size();
                if(isbeginningOfTheGameOfPlayer2){
                    treasureBeginning.setText(Integer.toString(size));
                    isbeginningOfTheGameOfPlayer2 = false;
                }
                if(!isbeginningOfTheGameOfPlayer2){
                    treasure.player2turn();
                }
                hideScreen();
                treasure.getPlayer1().showScreen();
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.add(verticalBox, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(show);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showScreen(){
        this.setVisible(true);
    }

    public void hideScreen() {

        this.setVisible(false);
    }

    public SelfGrid getSelfGrid(){

        for(Component child : this.getContentPane().getComponents()){

            if(child instanceof SelfGrid ){
                return (SelfGrid)child;
            }
        }
        return null;
    }

    public AttackGrid getAttackGrid(){
        for(Component child : this.getContentPane().getComponents()){
            if(child instanceof AttackGrid ){
                return (AttackGrid) child;
            }

        }
        return null;
    }

}