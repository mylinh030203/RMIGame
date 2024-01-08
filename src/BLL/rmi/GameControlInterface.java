package BLL.rmi;

import Model.GameData;
import Model.User;
import util.enum_class.ResultStatus;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface GameControlInterface extends Remote
{
    //return User
    boolean register(String username, String password) throws RemoteException;
    User logIn(String username, String password) throws RemoteException;

    //return GameData{image, image_2, n)
    // n là kích thước ma trận vuông chứa ảnh
    GameData getGameData() throws RemoteException;

    int getGameDataId() throws  RemoteException;

    //
    ResultStatus checkResult(int userId, int gameDataId, int x, int y) throws RemoteException;

    // return List<User>
    List<User> getRanking() throws RemoteException;
}
