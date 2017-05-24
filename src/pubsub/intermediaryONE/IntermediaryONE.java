package pubsub.intermediaryONE;

import pubsub.service.IntermediaryService;
import pubsub.service.IntermediaryServiceImpl;

import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * Created by MarcoADP on 23/05/2017.
 */
public class IntermediaryONE {
    public IntermediaryONE(){
        try {
            IntermediaryService interService = new IntermediaryServiceImpl();
            Naming.rebind("192.168.56.1/IntermediaryServiceONE", interService);

            System.out.println("Server started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws RemoteException {
        java.rmi.registry.LocateRegistry.createRegistry(1099);
        new IntermediaryONE();
    }
}
