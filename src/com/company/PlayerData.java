package com.company;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class PlayerData {
    private PlayerScreen player;
    private int[][] attackData = new int[11][11];
    private int[][] selfData = new int[11][11];
    private int numberOfTreasuresFound = 0;
    private ArrayList<Treasure> chest = new ArrayList<>();

    PlayerData( PlayerScreen player){
        this.player = player;
    }

    public void addTreasure(Coordinate a){
         if(treasuresLeft() < 5){
                chest.add(new Treasure(a));
                setSelfData(a);
         }
    }

    public void attackTreasure(Coordinate hitCord) {
        Iterator itr = chest.iterator();
        while (itr.hasNext()){
            Treasure temp = (Treasure)itr.next();
            temp.Hit(hitCord);
        }
    }

    public int treasuresLeft(){
        int temp = chest.size();
        return temp;
    }

    public void setAttackData(int x, int y, @NotNull String result) {
        if(result.equals("success")){
            attackData[x][y] = 1;
        }
        else if(result.equals("failure")){
            attackData[x][y] = 2;
        }
    }

    public void setSelfData(@NotNull Coordinate a){
        selfData[a.getX()][a.getY()]=1;
    }

    public int getNumberOfOwnTreasuresFound() {
        return numberOfTreasuresFound;
    }

    public ArrayList<Treasure> getChest(){
        return chest;
    }

    public int[][] getSelfData(){
        return selfData;
    }
    public int[][] getAttackData(){
        return attackData;
    }

    public boolean isHit(Coordinate point){
        for (int i=0;i<chest.size();){
            Treasure temp = chest.get(i);
            if(temp.isPointHit(point)){
                return true;
            }
            else{
                i++;
            }
        }
        return false;
    }

    public boolean isPlayerLost(){
        if(chest.size()==0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isFound(Coordinate hitCord){
        for (int i=0;i<chest.size();){
            Treasure temp = chest.get(i);
            if(temp.isTreasureFound()){
                numberOfTreasuresFound++;
                chest.remove(i);
                return true;
            }
            else{
                i++;
            }
        }
        return false;
    }

}