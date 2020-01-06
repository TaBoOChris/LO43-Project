package main.math;

import main.game.Game;

public class Vector2 <T>{

    private T x;
    private T y;

    public Vector2(T x, T y){
        this.x = x;
        this.y = y;
    }


    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
