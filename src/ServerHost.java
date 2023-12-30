import BLL.rmi.RmiServer;

public class ServerHost {
    public static void main(String[] args) {
        RmiServer rmiServer =  new RmiServer();
        try {
            rmiServer.startBindingOnRmiServer("localhost", 111);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
