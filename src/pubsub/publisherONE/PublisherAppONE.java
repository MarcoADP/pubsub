package pubsub.publisherONE;

import pubsub.model.Event;
import pubsub.model.Publisher;
import pubsub.publisher.PublisherApp;
import pubsub.service.IntermediaryService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class PublisherAppONE{

    public static void main(String[] args) {
        try {
            IntermediaryService inter = (IntermediaryService) Naming.lookup( "192.168.56.1/IntermediaryServiceONE");
            System.out.println("conectado ao intermediaryONE");
            Random ran = new Random();
            Publisher pub = new Publisher(ran.nextInt(), "pub1");
            PublisherApp app = new PublisherApp();
            app.menu(inter, pub);

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
        }
    }

}
