package pubsub;

import javafx.util.Pair;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Intermediary extends UnicastRemoteObject implements IIntermediary {

    private String id;

    private List<Pair<String, String>> subscriptions;
    private List<Pair<String, String>> routing;
    private Set<String> neighbors;
    private String url;

    public void showSubsctriptions(){
        System.out.println("SUBS : ");
        for(Pair pair : subscriptions){
            System.out.println(pair.getKey() + " -- " + pair.getValue());
        }
        System.out.println(" -- fim --");
    }

    public void showRouting(){
        System.out.println("ROUTES : ");
        for(Pair pair : routing){
            System.out.println(pair.getKey() + " -- " + pair.getValue());
        }
        System.out.println(" -- fim --");
    }

    public Intermediary(String id) throws RemoteException, UnknownHostException {
        this.id = id;
        subscriptions = new ArrayList<>();
        routing = new ArrayList<>();
        neighbors = new HashSet<>();
        url = Util.createUrl(id);
    }

    @Override
    public void publish(String nodeId, String event, String message, String creator) throws RemoteException, UnknownHostException {
        System.out.println(String.format("%s: evento %s recebido de %s", id, event, nodeId));
        matchAndNotify(nodeId, event, message, creator);
        fowardPublish(nodeId, event, message, creator);
    }

    private void matchAndNotify(String nodeId, String event, String message, String creator) throws UnknownHostException {
        for(Pair pair : subscriptions){
            if(pair.getValue().toString().equals(event)){
                IClient subscriber = Util.getClientById(pair.getKey().toString());
                if(subscriber != null){
                    try {
                        subscriber.notify(event, message, creator);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        /*for (String subscriberId : subscriptions.keySet()) {
            if (subscriptions.get(subscriberId).equals(event)) {
                IClient subscriber = Util.getClientById(subscriberId);
                if (subscriber != null) {
                    try {
                        subscriber.notify(event, message, creator);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
    }

    private void fowardPublish(String nodeId, String event, String message, String creator) throws UnknownHostException, RemoteException {
        for(Pair pair : routing){
           if(pair.getValue().toString().equals(event)){
               IIntermediary intermediary = Util.getIntermediaryById(pair.getKey().toString());
               System.out.println("ids > " + intermediary.getId() + " -- " + nodeId);
               if (intermediary != null && !intermediary.getId().equals(nodeId)) {
                   try {
                       intermediary.publish(this.id, event, message, creator);
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
        /*for (String intermediaryId : routing.keySet()) {
            if (routing.get(intermediaryId).equals(event)) {
                IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
                if (intermediary != null) {
                    try {
                        intermediary.publish(this.id, event, message, creator);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }*/
    }

    @Override
    public void subscribe(String nodeId, String subscription) throws RemoteException, UnknownHostException {
        System.out.println(String.format("%s: recebeu subscrição do evento %s de %s.", id, subscription, nodeId));
        Pair pair = new Pair(nodeId, subscription);
        if (isClient(nodeId)) {
           subscriptions.add(pair);
            //subscriptions.put(nodeId, subscription);
        } else {
            routing.add(pair);
            //routing.put(nodeId, subscription);
        }

        for (String neighborId : neighbors) {
            if (!neighborId.equals(nodeId)) {
                IIntermediary neighbor = Util.getIntermediaryById(neighborId);
                if (neighbor != null) {
                    neighbor.subscribe(this.id, subscription);
                }
            }
        }

        this.showSubsctriptions();
        this.showRouting();
    }

    private boolean isClient(String id) {
        return !id.startsWith("I");
    }

    public void addNeighbor(String neighborId) {
        this.neighbors.add(neighborId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Intermediary that = (Intermediary) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public void showStatus() throws RemoteException{
        System.out.println("Intermediário " + this.getId() + " executando no endereco : " + this.getUrl());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String getId() throws RemoteException{
        return id;
    }

    public String getUrl() {
        return url;
    }
}
