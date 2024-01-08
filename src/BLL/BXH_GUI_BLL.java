package BLL;


import BLL.Constant.AppConstant;
import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import BLL.Constant.ClientConstant;
import DAL.Model.User;

import java.util.List;

public abstract class BXH_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;
    boolean shouldRefresh;

    public BXH_GUI_BLL() {
        rmiClient = RmiClient.getInstance();

        try {
            rmiClient.startConnectingToRmiServer(AppConstant.SERVER_HOST, AppConstant.SERVER_PORT);

            this.gameControlRemote = rmiClient.getRemoteObject();
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to start game!");
        }
    }

    public abstract void notification(String mess);

    public abstract void updateBxhUI(List<User> userList);

    public void onShowRanking() {
        this.shouldRefresh = true;
        refresh();
    }

    public void onHide() {
        this.shouldRefresh = false;
    }

    private void refresh() {
        Thread refreshThread = new Thread(() -> {
            try {
                while (shouldRefresh) {
                    Thread.sleep(ClientConstant.TIME_REFRESH_RANKING_MILI_SECOND);

                    List<User> userList = gameControlRemote.getRanking();

                    userList.forEach(System.out::println);

                    updateBxhUI(userList);
                }
            } catch (Exception e) {
                e.printStackTrace();
                notification("Error when try to get ranking!");
            }

        });

        refreshThread.start();
    }
}
