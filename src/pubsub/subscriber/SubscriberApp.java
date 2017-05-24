package pubsub.subscriber;

import pubsub.model.Event;
import pubsub.model.Subscriber;
import pubsub.service.IntermediaryService;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class SubscriberApp implements Serializable{

    public void showEvents(List<Event> events) {
        for (Event e : events) {
            System.out.println("Evento : " + e.getDescription() + " -- Created by : " + e.getPublisher().getId());
        }
    }


    public static void main(String[] args) {
        try {
            IntermediaryService inter = (IntermediaryService) Naming.lookup( "192.168.56.1/IntermediaryService");
            System.out.println("conectado ao server");
            Integer saida = -1;
            String evento, mensagem;
            Random ran = new Random();
            Subscriber sub = new Subscriber(ran.nextInt(), "sub");
            Scanner sc = new Scanner(System.in);
            while(saida != 0){
                System.out.println("0 - Sair");
                System.out.println("1 - Mostrar Eventos");
                System.out.println("2 - Se Inscrever");

                saida = sc.nextInt();

                if(saida == 1){
                    System.out.println("Eventos : ");
                    //evento = sc.next();
                    //novo = new Event(sub, evento);
                    SubscriberApp s = new SubscriberApp();
                    s.showEvents(inter.getEvents());

                } else if (saida == 2){
                    System.out.print("Escolha o evento : ");
                    evento = sc.next();
                }

            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
        }
    }
}
