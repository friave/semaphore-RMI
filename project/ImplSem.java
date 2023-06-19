package project;

import java.rmi.Remote;
import java.rmi.RemoteException;

// Implementing the remote interface 
public interface ImplSem extends Remote {  
   
   public String release(int x) throws RemoteException, InterruptedException;
   public String take(int x) throws RemoteException, InterruptedException;
} 