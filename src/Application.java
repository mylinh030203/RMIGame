import BLL.rmi.GameControlInterface;
import BLL.rmi.RmiClient;
import BLL.rmi.RmiServer;
import Model.GameData;
import util.enum_class.ResultStatus;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Application {
    public static void main(String[] args) {
        RmiClient rmiClient = RmiClient.getInstance();
        try {
            rmiClient.startConnectingToRmiServer("localhost", 111);

            GameControlInterface gameControl =  rmiClient.getRemoteObject();
            GameData gameData = gameControl.getGameData();
//            System.out.println(gameData);

            Boolean result  = false;
            while (!result){
                // lấy từ giao diện người chơi
                int x = (int) (Math.random() * 10);
                int y = (int) (Math.random() * 10);

                System.out.println("X: " + x + ", Y: " + y);


                result = gameControl.checkResult(0,gameData.getId(), x, y) == ResultStatus.CORRECT;
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
