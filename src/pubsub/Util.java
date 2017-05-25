package pubsub;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Util {

    public static IIntermediary getIntermediaryById(String id) throws UnknownHostException {
        try {
            System.out.println("id : " + id);
            return (IIntermediary) Naming.lookup(createUrl(id));
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IClient getClientById(String id) throws UnknownHostException {
        String ip = Util.getIP();
        try {
            return (IClient) Naming.lookup(createUrl(id));
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getIP() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public static String createUrl(String param) throws UnknownHostException {
        String ip = Util.getIP();
        return  ip + "/" + param;
    }
}
