package pubsub.publisher;

import pubsub.model.Event;
import pubsub.model.Publisher;
import pubsub.service.IntermediaryService;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class PublisherApp {

    public void menu(IntermediaryService inter, Publisher pub) throws RemoteException{
        Integer saida = -1;
        Scanner sc = new Scanner(System.in);
        while(saida != 0){
            System.out.println("0 - Sair");
            System.out.println("1 - Publicar Evento");
            System.out.println("2 - Enviar Mensagem");

            saida = sc.nextInt();

            if(saida == 1){
                publisher(inter, pub);
            } else if(saida == 2){
                sendMessage();
            }

        }
    }

    public void publisher(IntermediaryService inter, Publisher pub) throws RemoteException{
        Event novo;
        String evento;
        Scanner sc = new Scanner(System.in);
        System.out.print("Nome do Evento : ");

        evento = sc.next();
        novo = new Event(pub, evento);
        inter.publish(novo);
    }

    public void sendMessage() throws RemoteException{

    }

}
