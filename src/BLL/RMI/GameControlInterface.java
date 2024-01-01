package BLL.RMI;

import Model.GameData;
import Model.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GameControlInterface extends Remote
{
    //return User
    User register(String username, String password) throws RemoteException;
    User logIn(String username, String password) throws RemoteException;

    //return GameData{image, image_2, n)
    // n là kích thước ma trận vuông chứa ảnh
    GameData getGameData() throws RemoteException;

    //
    boolean checkResult(int userId, int x, int y) throws RemoteException;

    // return List<User>
    List<User> getRanking() throws RemoteException;
}
