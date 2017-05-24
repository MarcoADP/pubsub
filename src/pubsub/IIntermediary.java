package pubsub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IIntermediary extends Remote {

    void publish(String nodeId, String event) throws RemoteException;

    void subscribe(String nodeId, String subscription) throws RemoteException;
}
