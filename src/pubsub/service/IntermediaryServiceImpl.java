package pubsub.service;

import javafx.util.Pair;
import pubsub.model.Event;
import pubsub.model.Subscriber;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class IntermediaryServiceImpl extends UnicastRemoteObject implements IntermediaryService {
    public IntermediaryServiceImpl() throws RemoteException {
        events = new ArrayList<>();
        subscriptions = new ArrayList<Pair<Subscriber, Event>>();;
        //super();
    }

    public List<Event> events;
    public List<Pair<Subscriber, Event>> subscriptions;


    @Override
    public void publish(Event novo) throws RemoteException{
        System.out.println("chegou!");
        events.add(novo);
        showEvents();
    }

    @Override
    public void subscribe(Subscriber sub, String target) throws RemoteException {
        Event evento = getEvent(target);
        subscriptions.add(new Pair<>(sub, evento));
    }

    @Override
    public List<Event> getEvents() throws RemoteException{
        return events;
    }

    public Event getEvent(String name){
        for(Event e : events){
            if(e.getDescription().equals(name)){
                return e;
            }
        }

        return null;
    }

    public void showEvents() throws RemoteException{
        System.out.print("\n\n" + "EVENTOS : \n");
        for(Event e: events){
            System.out.println("Evento : " + e.getDescription() + " -- Created by : " + e.getPublisher().getId());
        }
    }

    private void showSubcribes() throws RemoteException{
        for(Pair<Subscriber, Event> p : subscriptions){
            System.out.println("Sub : " + p.getKey() + " -- Event : " + p.getValue());
        }
    }
}
