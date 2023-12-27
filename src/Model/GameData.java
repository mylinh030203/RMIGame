package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class GameData implements Serializable {
    private volatile static GameData instance;

    public static synchronized GameData getInstance() {
        if (instance == null) {
            System.out.println("Da tao moi GameData");
            instance = new GameData();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

    private int x, y, n, color;

    byte[] image;
    byte[] image2;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "GameData{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                ", color=" + color +
                ", image=" + Arrays.toString(image) +
                ", image2=" + Arrays.toString(image2) +
                ", NumberOfImage=" + NumberOfImage +
                '}';
    }

    int NumberOfImage = 37;
    public GameData(){
        n = (int) (Math.random() * 100000) % 5 + 3;
        x = (int) (Math.random() * 100000) % n;
        y = (int) (Math.random() * 100000) % n;
        color = (int)(Math.random()*100000) % NumberOfImage + 1;

//        image = getImage(color)//
    }


}
