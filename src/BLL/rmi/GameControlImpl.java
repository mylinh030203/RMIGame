package BLL.rmi;

import BLL.Repository.GameDataRepository;
import BLL.Repository.UserRepository;
//import Data.Database.DataSQL_Model;
import Model.GameData;
import Model.User;
import util.PasswordHash;
import util.enum_class.ResultStatus;

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
    public boolean register(String username, String password) {

        //TODO:  validate

        //
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(PasswordHash.generateMD5(password));

        return userRepository.insert(newUser);
    }

    @Override
    public User logIn(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return null;
        }

        String passwordHash = PasswordHash.generateMD5(password);

        if (user.getPassword().equals(passwordHash))
            return user;

        // trả về null nếu username hoặc pass không đúng, cần thông báo ở client nếu nhận được
        // giá trị null
        return null;
    }

    @Override
    public GameData getGameData() {
        System.out.println(GameData.getInstance());

        return GameData.getInstance();
    }

    @Override
    public int getGameDataId() {
        return GameData.getInstance().getId();
    }

    @Override
    public ResultStatus checkResult(int userId, int gameDataId, int x, int y) {
        GameData currentGameData = GameData.getInstance();

        if (gameDataId != currentGameData.getId())
            return ResultStatus.EXPIRED;

        boolean resultCheck = currentGameData.getX() == x && currentGameData.getY() == y;

        ResultStatus resultStatus;
        if (resultCheck) {
            //TODO: Cộng diem cho user
//            sql.increasePoint(userId);
            System.out.println(x+"  check "+y);
            GameData.destroyInstance();

            resultStatus = ResultStatus.CORRECT;
        } else {
            resultStatus = ResultStatus.WRONG;
        }
        System.out.println("Check result: userId = " + userId + " result = " + resultStatus);
        return resultStatus;
    }

    @Override
    public List<User> getRanking() {

//        return sql.getListUser();
        return null;
    }
}
