package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AttackGrid extends BattleGrid {
    private String name;
    private int enemyTreasuresFoundPlayer1 = 0;
    private int enemyTreasuresFoundPlayer2 = 0;
    private boolean isAttackGridListener ;
    private Treasure_ treasure;
    private PlayerScreen player;
    private JPanel thePanel = null;


    public AttackGrid(String name, Treasure_ treasure, PlayerScreen player) {
        super();
        this.name = name;
        this.player = player;
        this.treasure = treasure;
    }

    @Override
    protected JPanel getCell()
    {
        JPanel panel = new JPanel();
        panel.setBackground(Color.black);
        panel.setBorder(BorderFactory.createLineBorder(Color.red, 2));
        panel.setPreferredSize(new Dimension(20, 20));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isAttackGridListener) {

                    Point i = panel.getLocation();
                    double xPos = (i.getX() / 20 + 1);
                    int x = (int) xPos;
                    double yPos = (i.getY() / 20 + 1);
                    int y = (int) yPos;

                    if (name.equals("Player1")) {
                        if(!treasure.getTakeTurnAttack()) {
                            treasure.setTakeTurnAttack(true);
                            Coordinate hit = new Coordinate(x, y);
                            treasure.getPlayer2Data().attackTreasure(hit);

                            boolean success = treasure.getPlayer2Data().isHit(hit);
                            if (success) {
                                treasure.getPlayer1Data().setAttackData(x, y, "success");
                                draw();
                            } else {
                                treasure.getPlayer1Data().setAttackData(x, y, "failure");
                                draw();
                            }

                            boolean isFound = treasure.getPlayer2Data().isFound(hit);
                            if (isFound) {
                                enemyTreasuresFoundPlayer1++;
                                treasure.getPlayer2().treasureBeginning.setText(Integer.toString(5 - enemyTreasuresFoundPlayer1));
                                treasure.getPlayer1().enemyTreasureFound.setText(Integer.toString(enemyTreasuresFoundPlayer1));
                                JOptionPane.showMessageDialog(panel, "Сокровище Player 2 найдено\nНажмите ОК, чтобы ход перешел к оппоненту");
                                player.hideScreen();
                                treasure.getPlayer2().showScreen();
                                String ownTreasuresFoundPlayer2 = Integer.toString(treasure.getPlayer2Data().getNumberOfOwnTreasuresFound());
                                treasure.getPlayer2().ownTreasureFound.setText(ownTreasuresFoundPlayer2);
                            }
                        }
                        boolean lost = treasure.getPlayer2Data().isPlayerLost();
                            if (lost) {
                                treasure.setState(treasure.getEndOfTheGame());
                                JOptionPane.showMessageDialog(panel, "Вы (player 1) выиграли\nНажмите ОК, чтобы выйти из игры");
                                treasure.player1Turn();
                            }

                        }
                        if (name.equals("Player2")) {
                            if(treasure.getTakeTurnAttack()) {
                                treasure.setTakeTurnAttack(false);
                                Coordinate hit = new Coordinate(x, y);
                                treasure.getPlayer1Data().attackTreasure(hit);

                                boolean success = treasure.getPlayer1Data().isHit(hit);
                                if (success) {
                                    treasure.getPlayer2Data().setAttackData(x, y, "success");
                                    draw();
                                } else {
                                    treasure.getPlayer2Data().setAttackData(x, y, "failure");
                                    draw();
                                }

                                boolean isFound = treasure.getPlayer1Data().isFound(hit);
                                if (isFound) {
                                    enemyTreasuresFoundPlayer2++;
                                    treasure.getPlayer1().treasureBeginning.setText(Integer.toString(5 - enemyTreasuresFoundPlayer2));
                                    treasure.getPlayer2().enemyTreasureFound.setText(Integer.toString(enemyTreasuresFoundPlayer2));
                                    JOptionPane.showMessageDialog(panel, "Сокровище Player 1 найдено\nНажмите ОК, чтобы ход перешел к оппоненту");
                                    player.hideScreen();
                                    treasure.getPlayer1().showScreen();
                                    String ownTreasuresFoundPlayer1 = Integer.toString(treasure.getPlayer1Data().getNumberOfOwnTreasuresFound());
                                    treasure.getPlayer1().ownTreasureFound.setText(ownTreasuresFoundPlayer1);
                                }
                            }
                                boolean lost = treasure.getPlayer1Data().isPlayerLost();
                                if (lost) {
                                    treasure.setState(treasure.getEndOfTheGame());
                                    JOptionPane.showMessageDialog(panel, "Вы (player 2) выиграли\nНажмите ОК, чтобы выйти из игры");
                                    treasure.player2turn();
                                }
                            }
                        }


            }
        });

        return panel;
    }
    public void setAttackGridListener (boolean attackGridListener){
        this.isAttackGridListener = attackGridListener;
    }

    public void getJpanel(Point newPoint){
        thePanel = this.getComponentAt(newPoint);
    }

    public void draw(){
        int[][] temp=null;
        if(name.equals("Player1")){
            temp = treasure.getPlayer1Data().getAttackData();
        }
        else if(name.equals("Player2")){
            temp = treasure.getPlayer2Data().getAttackData();
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                if(temp[i][j]==1){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);
                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.GREEN);
                }
                if(temp[i][j]==2){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);
                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.WHITE);
                }
            }
        }
    }

    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
    }

}