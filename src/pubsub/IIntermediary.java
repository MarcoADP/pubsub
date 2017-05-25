package pubsub;

import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IIntermediary extends Remote {

    String id = "0";

    String getId() throws RemoteException;

    void publish(String nodeId, String event, String message, String creator) throws RemoteException, UnknownHostException;

    void subscribe(String nodeId, String subscription) throws RemoteException, UnknownHostException;

    void showStatus() throws RemoteException;
}
