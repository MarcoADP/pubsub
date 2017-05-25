package pubsub.network;

import pubsub.Client;

import java.rmi.Naming;

public class Subscriber2 {
    public Subscriber2() {
        try {
            Client a2 = new Client("A2", "I3");
            Naming.rebind(a2.getUrl(), a2);

            a2.showStatus();

            a2.subcscriberMenu();

            //a2.receiveEvent("y");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) {
        new Subscriber2();
    }
}
