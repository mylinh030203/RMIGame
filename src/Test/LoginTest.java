package Test;

import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import DAL.Model.GameData;
import DAL.Model.User;
import util.enum_class.ResultStatus;

public class LoginTest {
    public static void main(String[] args) {

        RmiClient rmiClient = RmiClient.getInstance();
        try {
            rmiClient.startConnectingToRmiServer("localhost", 111);

            GameControlInterface gameControl = rmiClient.getRemoteObject();

            User user = gameControl.logIn("lvtdel", "abcd1234");

            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
