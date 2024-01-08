package Test;

import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import DAL.Model.User;

public class RegisterTest {
    public static void main(String[] args) {

        RmiClient rmiClient = RmiClient.getInstance();
        try {
            rmiClient.startConnectingToRmiServer("localhost", 111);

            GameControlInterface gameControl = rmiClient.getRemoteObject();

            String username = "lvtdel3";
            String password = "abcd1234";
            String fullName = "Vo The Luc";
            int age = 21;

            boolean result = gameControl.register(username, password, fullName, age);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
