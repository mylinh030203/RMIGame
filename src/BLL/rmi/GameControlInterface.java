package BLL.rmi;

import Model.GameData;
import Model.User;

import java.util.List;

public interface GameControlInterface
{
    //return User
    User register(String username, String password);
    User logIn(String username, String password);

    //return GameData{image, image_2, n)
    // n là kích thước ma trận vuông chứa ảnh
    GameData getGameData(int userId);

    //
    boolean checkResult(int userId, int x, int y);

    // return List<User>
    List<User> getRanking();
}
