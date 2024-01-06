package BLL;

import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import Constant.client.AppConstant;
import Model.User;

public abstract class Login_GUI_BLL {
    RmiClient rmiClient;
    GameControlInterface gameControlRemote;

    public Login_GUI_BLL() {
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

    public abstract void onLoginSuccess(User user);

    public void onLoginClick(String username, String password) {
        try {
            User user = gameControlRemote.logIn(username, password);

            if (user == null) {
                notification("Username or password is not correct!");
            } else {
                onLoginSuccess(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to login");
        }
    }
}
