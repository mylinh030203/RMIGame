package BLL;

import BLL.RMI.GameControlInterface;
import BLL.RMI.RmiClient;
import Constant.AppConstant;
import Model.GameData;

public abstract class Client_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;

    public Client_GUI_BLL() {
        rmiClient = RmiClient.getInstance();

        try {
            rmiClient.startConnectingToRmiServer(AppConstant.SERVER_HOST, AppConstant.SERVER_PORT);

            this.gameControlRemote = rmiClient.getRemoteObject();
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to start game!");
        }
    }

    abstract public void updateClientUI(GameData gameData);
    // Dialog thông báo mess
    abstract public void notification(String mess);

    public void onStartGame() {
        try {
            GameData gameData = gameControlRemote.getGameData();

            updateClientUI(gameData);
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to start game!");
        }
    }

    // Khi người dùng click ảnh chọn đáp án
    public void onClickAns(String x_y) {
        String[] ans = x_y.split(" ");
        int x = Integer.parseInt(ans[0]);
        int y = Integer.parseInt(ans[1]);

        try {
            boolean result = gameControlRemote.checkResult(1, x, y);

            if (result) {
                GameData newGameData = gameControlRemote.getGameData();
                updateClientUI(newGameData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to check result!");
        }
    }

    public void onExitGame() {
        rmiClient.stopConnectingToRmiServer();
    }

//    public void on
}
