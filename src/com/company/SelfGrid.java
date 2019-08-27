package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelfGrid extends BattleGrid {
    private boolean isSelfGridListener;
    private String name;
    private Point firstPoint = new Point(0,0);
    private Treasure_ treasure;
    private JPanel thePanel = new JPanel();

    public SelfGrid(String name, Treasure_ treasure) {
        super();
        this.name = name;
        this.treasure = treasure;
    }

    public void getJpanel(Point newPoint){
        thePanel = this.getComponentAt(newPoint);
    }

    @Override
    protected JPanel getCell()
    {
        JPanel firstCell = new JPanel();
        firstCell.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
        firstCell.setPreferredSize(new Dimension(20, 20));
        firstCell.setBackground(Color.black);

        firstCell.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isSelfGridListener) {
                    firstPoint = firstCell.getLocation();
                    double xPos = (firstPoint.getX()/20+1);
                    int x = (int) xPos;
                    double yPos = (firstPoint.getY()/20+1);
                    int y = (int) yPos;

                    Coordinate a = new Coordinate(x,y);

                    if(name.equals("Player1")){
                        treasure.getPlayer1Data().addTreasure(a);
                        draw();
                    }
                    if(name.equals("Player2")){
                        treasure.getPlayer2Data().addTreasure(a);
                        draw();
                    }
                }
            }
        });
        return firstCell;
    }

    public void setSelfGridListener (boolean selfGridListener){
        this.isSelfGridListener = selfGridListener;
    }

    public void draw(){

        int[][] temp=null;
        if(name.equals("Player1")){
            temp = treasure.getPlayer1Data().getSelfData();
        }
        else if(name.equals("Player2")){
            temp = treasure.getPlayer2Data().getSelfData();
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                assert temp != null;
                if(temp[i][j]==1){
                    int x = numberToPanel(i);
                    int y = numberToPanel(j);
                    Point p = new Point(x,y);
                    getJpanel(p);
                    thePanel.setBackground(Color.CYAN);
                }
               if(temp[i][j]==0){
                   int x = numberToPanel(i);
                   int y = numberToPanel(j);
                   Point p = new Point(Math.abs(x),Math.abs(y));
                   getJpanel(p);
                   thePanel.setBackground(Color.BLACK);
                }
            }
        }
    }

    public int numberToPanel(int s){
        int temp = (s-1)*20;
        return temp;
    }

}