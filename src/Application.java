import BLL.RMI.GameControlInterface;
import BLL.RMI.RmiClient;
import Model.GameData;

public class Application {
    public static void main(String[] args) {
        RmiClient rmiClient = new RmiClient();
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


                result = gameControl.checkResult(0, x, y);
                System.out.println(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
