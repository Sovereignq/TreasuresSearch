package com.company;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Treasure {
    Coordinate a;
    boolean aHit;

    @Contract(pure = true)
    Treasure(Coordinate a){
        this.a = a;
    }

    public boolean compareTreasure(@NotNull Treasure treasure){
        if(treasure.getA().compareCoord(this.a)){
            return true;
        }
        return false;
    }

    public Coordinate getA() {
        return a;
    }

    public boolean isPointHit(@NotNull Coordinate hit){
        if(hit.getX() == a.getX()&&hit.getY() == a.getY()){
            return true;
        }
        return false;
    }

    public void Hit(@NotNull Coordinate hit){
        if(hit.getY() == a.getY() && hit.getX() == a.getX()){
            aHit = true;
        }
    }

    public boolean isTreasureFound(){
        if(aHit){
            return true;
        }
        else {
            return false;
        }
    }

}