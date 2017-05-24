package pubsub.service;

import pubsub.model.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public interface IntermediaryService extends Remote{

    public void publish(Event novo) throws RemoteException;

}
