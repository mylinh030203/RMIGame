import BLL.Constant.AppConstant;
import BLL.rmi.RmiServer;

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
