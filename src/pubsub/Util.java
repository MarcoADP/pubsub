package pubsub;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Util {

    public static IIntermediary getIntermediaryById(String id) {
        try {
            return (IIntermediary) Naming.lookup("//localhost/" + id);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static IClient getClientById(String id) {
        try {
            return (IClient) Naming.lookup("//localhost/" + id);
        } catch (NotBoundException | RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
