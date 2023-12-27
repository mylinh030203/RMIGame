package Model;

public class GameData {
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

    int NumberOfImage = 37;
    public GameData(){
        n = (int) (Math.random() * 100000) % 5 + 3;
        x = (int) (Math.random() * 100000) % n;
        y = (int) (Math.random() * 100000) % n;
        color = (int)(Math.random()*100000) % NumberOfImage + 1;

//        image = getImage(color)//
    }

}
