package pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void notify(String event, String message, String creator) throws RemoteException;

    void showStatus() throws RemoteException;
}
