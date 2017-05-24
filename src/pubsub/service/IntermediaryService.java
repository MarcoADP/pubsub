package pubsub.service;

import pubsub.model.Event;
import pubsub.model.Subscriber;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public interface IntermediaryService extends Remote{

    public void publish(Event novo) throws RemoteException;

    public void subscribe(Subscriber sub, String target) throws RemoteException;

    //public void subscribe(Subscriber sub, Event target) throws RemoteException;

    public List<Event> getEvents() throws RemoteException;
}
