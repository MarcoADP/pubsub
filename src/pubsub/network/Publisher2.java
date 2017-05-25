package pubsub.network;

import pubsub.Client;

public class Publisher2 {
    public static void main(String[] args) {
        try {
            Client p2 = new Client("P2", "I3");

            p2.publisherMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
