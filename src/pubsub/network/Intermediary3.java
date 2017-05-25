package pubsub.network;

import pubsub.Intermediary;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Intermediary3 {

    public Intermediary3() {
        try {
            Intermediary i3 = new Intermediary("I3");
            i3.addNeighbor("I1");

            Naming.rebind(i3.getUrl(), i3);

            i3.showStatus();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) throws RemoteException {
        new Intermediary3();
    }
}
