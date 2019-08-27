package com.company;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Coordinate {
    int x;
    int y;

    @Contract(pure = true)
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }

    public boolean compareCoord(@NotNull Coordinate coordinate){
        if(coordinate.getX() == this.x && coordinate.getY() == this.y){
            return true;
        }
        return false;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public String toString(){
        return "\nX=" + x + " and Y=" + y;
    }

}