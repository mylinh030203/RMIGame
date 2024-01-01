import BLL.RMI.RmiServer;
import Constant.AppConstant;

public class ServerHost {
    public static void main(String[] args) {
        RmiServer rmiServer =  new RmiServer();
        try {
            rmiServer.startBindingOnRmiServer(AppConstant.SERVER_HOST, AppConstant.SERVER_PORT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
