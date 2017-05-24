package pubsub;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject implements IClient {

    private String id;
    private String intermediaryId;

    public Client(String id, String intermediaryId) throws RemoteException {
        this.id = id;
        this.intermediaryId = intermediaryId;
    }

    public void notify(String event) throws RemoteException {
        System.out.println(String.format("Cliente %s recebeu evento %s de %s", this.id, event, intermediaryId));
    }

    public void sendEvent(String event) {
        System.out.println("Enviando evento " + event);
        IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
        if (intermediary != null) {
            try {
                intermediary.publish(this.id, event);
                System.out.println("Evento " + event + " enviado.");
            } catch (RemoteException e) {
                System.out.println("Evento não foi enviado");
                e.printStackTrace();
            }
        }
    }

    public void receiveEvent(String event) {
        System.out.println(String.format("Cliente %s subscreveu para o evento %s.", id, event));

        IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
        if (intermediary != null) {
            try {
                intermediary.subscribe(this.id, event);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }
}
