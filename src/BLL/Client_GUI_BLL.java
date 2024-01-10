package BLL;

import BLL.Authentication.Auth;
import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import BLL.Constant.AppConstant;
import BLL.Constant.ClientConstant;
import DAL.Model.GameData;
import util.enum_class.ResultStatus;


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
    public void onClickAns(int x, int y) {


        System.out.println(x + " " + y);

        try {
            String currentUsername = Auth.getInstance().getCurrentUser().getUsername();

            System.out.println("Current username: " + currentUsername);

            ResultStatus resultStatus = gameControlRemote.checkResult(currentUsername,gameData.getId(), x, y);

            if (resultStatus == ResultStatus.CORRECT) {
                // Hiện chúc mừng trên giao dieện nếu cần

            } else if(resultStatus == ResultStatus.WRONG) {
                onResultWrong();
            } else  {
                // Thông báo chậm tay, da co nguời tr lời úng

                System.out.println("Qúa hạn");
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
                    Thread.sleep(ClientConstant.TIME_REFRESH_GAME_DATA_MILI_SECOND);

//                    System.out.println("Check gameData update: client gameDataId: " + gameData.getId()
//                            + "server gameDataId: " + gameControlRemote.getGameDataId() );

                    if (gameControlRemote.getGameDataId() != gameData.getId()) {
                        updateClientUI();

                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    notification("Error when try to check update game data!");
                }
            }
        });

        checkUpdateThread.start();
    }
}
