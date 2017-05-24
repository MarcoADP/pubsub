package pubsub.network;

import pubsub.Intermediary;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Intermediary2 {

    public Intermediary2() {
        try {
            Intermediary i2 = new Intermediary("I2");
            i2.addNeighbor("I1");

            Naming.rebind("//localhost/" + i2.getId(), i2);

            System.out.println("Intermediário " + i2.getId() + " executando.");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) throws RemoteException {
        new Intermediary2();
    }
}
