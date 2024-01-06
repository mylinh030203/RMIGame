package Model;

import util.ImageSupport;

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

    private final int id;
    private final int x;
    private final int y;
    private final int n;

    byte[] image;
    byte[] image2;

    public byte[] getImage() {
        return image;
    }

    public byte[] getImage2() {
        return image2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getN() {
        return n;
    }

    public int getId() {return id;}


    @Override
    public String toString() {
        return "GameData{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                ", image=" + Arrays.toString(image) +
                ", image2=" + Arrays.toString(image2) +
                ", NumberOfImage=" + NumberOfImage +
                '}';
    }


    public GameData(){
        id = (int) (Math.random()*1000);
        n = (int) (Math.random() * 100000) % 5 + 3;
        x = (int) (Math.random() * 100000) % n;
        y = (int) (Math.random() * 100000) % n;

        randomImage();
    }

    private int NumberOfImage = 37;
    private void randomImage() {
        int index = (int)(Math.random()*100000) % NumberOfImage + 1;

        this.image = ImageSupport.getImageAsByteArray("D:\\intellij\\RMIGame\\RMIGame\\src\\image\\image"+index+"_1.png");
        this.image2 = ImageSupport.getImageAsByteArray("D:\\intellij\\RMIGame\\RMIGame\\src\\image\\image"+index+"_2.png");

        if (image == null) {
            System.out.println("Image NULL");
        }
    }

}
