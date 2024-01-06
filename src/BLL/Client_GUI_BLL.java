package BLL;

import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import Constant.client.AppConstant;
import Constant.client.ClientConstant;
import Model.GameData;


public abstract class Client_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;
    GameData gameData;

    public Client_GUI_BLL() {
        rmiClient = RmiClient.getInstance();

        try {
            rmiClient.startConnectingToRmiServer(AppConstant.SERVER_HOST, AppConstant.SERVER_PORT);

            this.gameControlRemote = rmiClient.getRemoteObject();

            //  Liên tục check dữ liệu mới
            checkNewGameData();
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to start game!");
        }


    }

    // Dialog thông báo mess
    abstract public void notification(String mess);

    abstract public void updateClientUI();

    abstract public void onResultWrong();

    public GameData onStartGame() {
        try {
            this.gameData = gameControlRemote.getGameData();

            return this.gameData;

        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to start game!");
        }

        return null;
    }

    // Khi người dùng click ảnh chọn đáp án
    public void onClickAns(String x_y) {
        String[] ans = x_y.split(" ");
        int x = Integer.parseInt(ans[0]);
        int y = Integer.parseInt(ans[1]);

        System.out.println(x+ " " + y);

        try {
            boolean result = gameControlRemote.checkResult(1, x, y);

            if (result) {
                updateClientUI();
            } else {
                onResultWrong();
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

    private void checkNewGameData() {
        Thread checkUpdateThread = new Thread(() -> {

            while (true) {
                try {
                    if (gameControlRemote.getGameDataId() != gameData.getId()) {
                        updateClientUI();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notification("Error when try to check update game data!");
                }

                try {
                    Thread.sleep(ClientConstant.TIME_REFRESH_MILI_SECOND);
                } catch (InterruptedException ignored) {
                }
            }

        });

        checkUpdateThread.start();
    }
}
