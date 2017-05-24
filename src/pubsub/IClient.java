package pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void notify(String event) throws RemoteException;
}
