package pubsub.publisher;

import pubsub.model.Event;
import pubsub.model.Publisher;
import pubsub.service.IntermediaryService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class PublisherApp {

    public static void main(String[] args) {
        try {
            IntermediaryService inter = (IntermediaryService) Naming.lookup( "192.168.56.1/IntermediaryService");
            System.out.println("conectado ao server");
            Integer saida = -1;
            String evento, mensagem;
            Event novo;
            Publisher pub = new Publisher(1, "pub1");
            Scanner sc = new Scanner(System.in);
            while(saida != 0){
                System.out.println("0 - Sair");
                System.out.println("1 - Publicar Evento");
                System.out.println("2 - Enviar Mensagem");

                saida = sc.nextInt();

                if(saida == 1){
                    System.out.print("Nome do Evento : ");
                    evento = sc.next();
                    novo = new Event(pub, evento);
                    inter.publish(novo);

                }

            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
        }
    }

}
