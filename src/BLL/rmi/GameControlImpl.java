package BLL.rmi;

import DAL.Repository.GameDataRepository;
import DAL.Repository.UserRepository;
//import Data.Database.DataSQL_Model;
import DAL.Model.GameData;
import DAL.Model.User;
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
    public boolean register(String username, String password, String fullName, int age) {

        //TODO:  validate

        //
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFullname(fullName);
        newUser.setAge(age);
        newUser.setPassword(PasswordHash.generateMD5(password));
        newUser.setScore(0);

        return userRepository.insert(newUser);
    }

    @Override
    public User logIn(String username, String password) {
        User user = userRepository.findByUsername(username);
        System.out.println(user);

        if (user == null) {
            return null;
        }

        System.out.println("Pass from DB: " + user.getPassword());

        String passwordHash = PasswordHash.generateMD5(password);
        System.out.println("Pass md5: " + passwordHash);

        if (user.getPassword().equalsIgnoreCase(passwordHash)) {
            user.setPassword("");
            return user;
        }


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
        //
    }

    @Override
    public ResultStatus checkResult(String username, int gameDataId, int x, int y) {
        GameData currentGameData = GameData.getInstance();

        if (gameDataId != currentGameData.getId())
            return ResultStatus.EXPIRED;

        boolean resultCheck = currentGameData.getX() == x && currentGameData.getY() == y;

        ResultStatus resultStatus;
        if (resultCheck) {
            GameData.destroyInstance();
            //TODO: Cộng diem cho user
            userRepository.increaseScore(username);


            resultStatus = ResultStatus.CORRECT;
        } else {
            resultStatus = ResultStatus.WRONG;
        }
//        System.out.println("Check result: userId = " + username + " result = " + resultStatus);
        return resultStatus;
    }

    @Override
    public List<User> getRanking() {
        return userRepository.getListUser();
    }
}
