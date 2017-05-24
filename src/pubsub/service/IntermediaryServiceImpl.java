package pubsub.service;

import pubsub.model.Event;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class IntermediaryServiceImpl extends UnicastRemoteObject implements IntermediaryService {
    public IntermediaryServiceImpl() throws RemoteException {
        eventos = new ArrayList<>();
        //super();
    }

    public List<Event> eventos;


    @Override
    public void publish(Event novo) throws RemoteException{
        System.out.println("chegou!");
        eventos.add(novo);
        showEvents();
    }

    private void showEvents() throws RemoteException{
        for(Event e: eventos){
            System.out.println("Evento : " + e.getDescription() + " -- Created by : " + e.getPublisher().getName());
        }
    }
}
