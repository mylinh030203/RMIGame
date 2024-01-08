package BLL;

import BLL.Authentication.Auth;
import BLL.Constant.AppConstant;
import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import DAL.Model.User;

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
                Auth.getInstance().setCurrentUser(user);
                onLoginSuccess(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
            notification("Error when try to login");
        }
    }

    public void onRegisterClick(String username, String password, String fullName, int age){
        try {
            boolean result = this.gameControlRemote.register(username, password, fullName, age);

            if (result) {
                notification("Register success!");
            } else {
                notification("Username already exist!");
            }
        } catch (Exception e) {
           e.printStackTrace();
           notification("Error when try to register!");
        }
    }
}
