package DAL.Model;

import util.ImageSupport;

import java.io.Serializable;

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

    public int getId() {
        return id;
    }


    @Override
    public String toString() {
        String isImageNull = image == null ? "null" : "not null";
        String isImage2Null = image2 == null ? "null" : "not null";

        return "GameData{" +
                "x=" + x +
                ", y=" + y +
                ", n=" + n +
                ", image=" + isImageNull +
                ", image2=" + isImage2Null +
                ", NumberOfImage=" + NumberOfImage +
                '}';
    }


    public GameData() {
        id = (int) (Math.random() * 1000);
        n = (int) (Math.random() * 100000) % 5 + 3;
        x = (int) (Math.random() * 100000) % n;
        y = (int) (Math.random() * 100000) % n;

        randomImage();
    }

    private int NumberOfImage = 37;

    private void randomImage() {
        int index = (int) (Math.random() * 100000) % NumberOfImage + 1;

//        String currentDirectory = System.getProperty("user.dir");
//
//        // In ra đường dẫn thư mục đang thực thi
//        System.out.println("Thư mục đang thực thi: " + currentDirectory);

        this.image = ImageSupport.getImageAsByteArray("resource\\image\\image" + index + "_1.png");
        this.image2 = ImageSupport.getImageAsByteArray("resource\\image\\image" + index + "_2.png");

        if (image == null) {
            System.out.println("Image NULL");
        }
    }

}
