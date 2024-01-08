package BLL;


import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import Constant.client.AppConstant;
import Model.User;

import java.util.List;

public abstract class BXH_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;

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

    public void onStart() {
        refresh();
    }

    // Nếu có nút refresh thì gọi hàm này, không thì xóa
    public void onRefresh() {
        refresh();
    }

    private void refresh() {
        try {
            List<User> userList = gameControlRemote.getRanking();

            updateBxhUI(userList);
        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to get ranking!");
        }
    }
}
