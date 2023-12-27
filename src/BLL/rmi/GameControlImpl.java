package BLL.rmi;

import BLL.Repository.GameDataRepository;
import BLL.Repository.UserRepository;
import Model.GameData;
import Model.User;

import java.util.List;

public class GameControlImpl implements GameControlInterface {

    GameDataRepository gameDataRepository = new GameDataRepository();
    UserRepository userRepository = new UserRepository();

    public GameControlImpl() {

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
    public GameData getGameData(int userId) {
        // Tạo mới gamedata random bởi contructor new GameData();
        GameData gameData = new GameData();

        // Thêm vào csdl
        GameData newGameData =  gameDataRepository.insert(gameData);


        return newGameData;
    }

    @Override
    public boolean checkResult(int userId, int x, int y) {
        GameData currentGame = gameDataRepository.findByUserId(userId);

        if (currentGame.getX() == x && currentGame.getY() == y) {
            //TODO: Cộng diem cho user

            return true;
        }

        return false;
    }

    @Override
    public List<User> getRanking() {

        return null;
    }
}
