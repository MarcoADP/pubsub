package pubsub.network;

import pubsub.Client;

import java.rmi.Naming;

public class Subscriber1 {
    public Subscriber1() {
        try {
            Client a1 = new Client("A1", "I2");
            Naming.rebind(a1.getUrl(), a1);

            a1.showStatus();

            a1.subcscriberMenu();

            //a1.receiveEvent("x");
            //a1.receiveEvent("a");
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    public static void main(String[] args) {
        new Subscriber1();
    }
}
