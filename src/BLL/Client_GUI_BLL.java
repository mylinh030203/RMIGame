package BLL;

import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import Constant.client.ClientConstant;
import Model.GameData;
import View.Client_GUI;

public abstract class Client_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;

    public Client_GUI_BLL() {
        rmiClient = new RmiClient();

        try {
            rmiClient.startConnectingToRmiServer(ClientConstant.SERVER_HOST, ClientConstant.SERVER_PORT);

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
            Boolean result = gameControlRemote.checkResult(1, x, y);

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
