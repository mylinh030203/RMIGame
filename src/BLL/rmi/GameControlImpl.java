package BLL.rmi;

import BLL.Repository.GameDataRepository;
import BLL.Repository.UserRepository;
//import Data.Database.DataSQL_Model;
import Model.GameData;
import Model.User;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class GameControlImpl extends UnicastRemoteObject implements GameControlInterface {

    GameDataRepository gameDataRepository = new GameDataRepository();
    UserRepository userRepository = new UserRepository();
//    DataSQL_Model sql = new DataSQL_Model();
    public GameControlImpl() throws RemoteException {

    }

    @Override
    public User register(String username, String password) {

        //TODO:  validate

        //
        User newUser = new User();
        return userRepository.insert(newUser);
    }

    @Override
    public User logIn(String username, String password) {
        User user = userRepository.findByUsername(username);

//        if (user.password.equals(password))
//            return user;

        // trả về null nếu username hoặc pass không đúng, cần thông báo ở client nếu nhận được
        // giá trị null
        return null;
    }

    @Override
    public GameData getGameData() {
        System.out.println(GameData.getInstance());

        // TODO: Coppy GameData, thay x, y = -1 (dấu đáp án) sau đó mới return bản sao
        return GameData.getInstance();
    }

    @Override
    public boolean checkResult(int userId, int x, int y) {
        GameData currentGame = GameData.getInstance();

        if (currentGame.getX() == x && currentGame.getY() == y) {
            //TODO: Cộng diem cho user
//            sql.increasePoint(userId);

            GameData.destroyInstance();

            //TODO: Gọi callback từng client update game mới
            // hoặc để client liên tục check trạng thái và tự cập nhật
            return true;
        }

        return false;
    }

    @Override
    public List<User> getRanking() {

//        return sql.getListUser();
        return null;
    }
}
