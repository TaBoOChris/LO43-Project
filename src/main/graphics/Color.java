package main.graphics;

public class Color {

    public static final float[] WHITE = new Color(1,1,1,1).getColor();
    public static final float[] BLACK = new Color(0,0,0,1).getColor();

    public static final float[] RED = new Color(1,0,0,1).getColor();
    public static final float[] GREEN = new Color(0,1,0,1).getColor();
    public static final float[] BLUE = new Color(0,0,1,1).getColor();

    public static final float[] ORANGE = new Color(1,0.5f,0 , 1) . getColor();
    public static final float[] GREY = new Color(0.5f,0.5f,0.5f,1).getColor();
    public static final float[] YELLOW = new Color(1 , 1, 0, 1 ).getColor();
    public static final float[] PINK = new Color(1 , 0.6f, 0.8f, 1 ).getColor();
    public static final float[] BEIGE = new Color(1 , 1, 0.9f, 1 ).getColor();
    public static final float[] DEEPSKYBLUE = new Color(0 , 0.6f , 1, 1 ).getColor();
    public static final float[] GOLD = new Color(1 , 0.9f , 0, 1 ).getColor();


    public float r,g,b,a;

    public Color(float r ,float g ,float b,float a){
        this.a = a;
        this.r = r;
        this.b = b;
        this.g = g;
    }

    public float[] getColor(){
        return new float[] {r,g,b,a};
    }
}
