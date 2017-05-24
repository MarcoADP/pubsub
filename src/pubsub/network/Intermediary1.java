package pubsub.network;

import pubsub.Intermediary;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Intermediary1 {

    public Intermediary1() {
        try {
            Intermediary i1 = new Intermediary("I1");
            i1.addNeighbor("I2");
            i1.addNeighbor("I3");

            Naming.rebind("//localhost/" + i1.getId(), i1);

            System.out.println("Intermediário " + i1.getId() + " executando.");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) throws RemoteException {
        LocateRegistry.createRegistry(1099);

        new Intermediary1();
    }
}
