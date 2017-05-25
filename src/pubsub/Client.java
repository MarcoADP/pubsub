package pubsub;

import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements IClient {

    private String id;
    private String intermediaryId;
    private String url;

    public Client(String id, String intermediaryId) throws RemoteException, UnknownHostException {
        this.id = id;
        this.intermediaryId = intermediaryId;
        this.url = Util.createUrl(id);
    }

    public void notify(String event, String message, String creator) throws RemoteException {
        System.out.println(String.format("Cliente %s recebeu a mensagem %s do evento %s criada por %s de %s", this.id, message, event, creator, intermediaryId));
    }

    public void sendEvent(String event, String message, String creator) throws UnknownHostException {
        System.out.println("Enviando messagem " + message + " do evento " + event + "criado pelo publisher " + creator);
        IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
        if (intermediary != null) {
            try {
                intermediary.publish(this.id, event, message, creator);
                System.out.println("Mensage " + message + " do Evento " + event + " enviado.");
            } catch (RemoteException e) {
                System.out.println("Evento não foi enviado");
                e.printStackTrace();
            }
        }
    }

    public void mostrarMenu(){
        System.out.println("Escolha uma ação : ");
        System.out.println("");
    }

    public void publisherMenu() throws UnknownHostException {
        Scanner sc = new Scanner(System.in);
        Integer saida = -1;
        String evento, mensagem;
        while(saida != 0){
            //mostrarMenu();
            System.out.print("\n\nDigite o nome do Evento : ");
            evento = sc.next();
            System.out.print("Digite a mensagem : ");
            mensagem = sc.next();

            this.sendEvent(evento, mensagem, this.getId());

        }

    }

    public void subscribeAllEvents(List<String> eventos) throws UnknownHostException {
        for(String evento : eventos){
            this.receiveEvent(evento);
        }
    }

    public void subcscriberMenu() throws UnknownHostException {
        Scanner sc = new Scanner(System.in);
        String evento = "";
        List<String> eventos = new ArrayList<>();
        while(!evento.equals("0")){
            System.out.print("Digite o Evento desejado/0 para Sair : ");
            evento = sc.next();

            if(!evento.equals("0")){
                eventos.add(evento);
            }
            //this.receiveEvent(evento);


        }
        this.subscribeAllEvents(eventos);
    }

    public void receiveEvent(String event) throws UnknownHostException {
        System.out.println(String.format("Cliente %s subscreveu para o evento %s.", id, event));

        IIntermediary intermediary = Util.getIntermediaryById(intermediaryId);
        if (intermediary != null) {
            try {
                intermediary.subscribe(this.id, event);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return id != null ? id.equals(client.id) : client.id == null;
    }

    @Override
    public void showStatus() throws RemoteException{
        System.out.println("Cliente " + this.getId() + " executando no endereco : " + this.getUrl());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
