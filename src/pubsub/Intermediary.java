package pubsub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Intermediary extends UnicastRemoteObject implements IIntermediary {

    private String id;

    private Map<String, String> subscriptions;
    private Map<String, String> routing;
    private Set<String> neighbors;

    public Intermediary(String id) throws RemoteException {
        this.id = id;
        subscriptions = new HashMap<>();
        routing = new HashMap<>();
        neighbors = new HashSet<>();
    }

    @Override
    public void publish(String nodeId, String event) throws RemoteException {
        System.out.println(String.format("%s: evento %s recebido de %s", id, event, nodeId));
        matchAndNotify(event);
        fowardPublish(event);
    }

    private void matchAndNotify(String event) {
        for (String subscriberId : subscriptions.keySet()) {
            if (subscriptions.get(subscriberId).equals(event)) {
                IClient subscriber = Util.getClientById(subscriberId);
                if (subscriber != null) {
                    try {
                        subscriber.notify(event);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void fowardPublish(String event) {
        for (String intermediaryId : routing.keySet()) {
            if (routing.get(intermediaryId).equals(event)) {
                IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
                if (intermediary != null) {
                    try {
                        intermediary.publish(this.id, event);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void subscribe(String nodeId, String subscription) throws RemoteException {
        System.out.println(String.format("%s: recebeu subscrição do evento %s de %s.", id, subscription, nodeId));

        if (isClient(nodeId)) {
            subscriptions.put(nodeId, subscription);
        } else {
            routing.put(nodeId, subscription);
        }

        for (String neighborId : neighbors) {
            if (!neighborId.equals(nodeId)) {
                IIntermediary neighbor = Util.getIntermediaryById(neighborId);
                if (neighbor != null) {
                    neighbor.subscribe(this.id, subscription);
                }
            }
        }
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
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }
}
