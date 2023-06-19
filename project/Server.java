package project;

import java.rmi.registry.Registry; 
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException; 
import java.rmi.server.UnicastRemoteObject; 

public class Server extends UnicastRemoteObject implements ImplSem{

    private int available = 10;

    public Server() throws RemoteException {
        super();
    } 

    @Override
    public synchronized String release(int x) throws RemoteException {
        available += x;
        this.notifyAll();
        return "Released "+x+" unit/s, avialable = " + available;
    }
    
    @Override
    public synchronized String take(int x) throws RemoteException, InterruptedException {
        while(available<x){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } 
        available -= x;
        return "Took "+x+" unit/s, avialable = " + available;
    }

   public static void main(String args[]) { 
        try {
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(7000);
            registry.rebind("server", server);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
   }
} 