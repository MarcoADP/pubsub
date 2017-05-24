package pubsub.network;

import pubsub.Client;

public class Publisher1 {
    public static void main(String[] args) {
        try {
            Client p1 = new Client("P1", "I1");

            p1.sendEvent("x");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
