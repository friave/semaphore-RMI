package project;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;
import java.util.concurrent.TimeUnit;  

public class Client {  
    
   private Client() {}

   public static void main(String[] args)
        	throws InterruptedException, MalformedURLException, RemoteException, NotBoundException {
            ImplSem semaphore = (ImplSem) Naming.lookup("//localhost:7000/server");
            Random generator = new Random();

            while (true) {
                int rand = Math.abs(generator.nextInt()) % 5 + 1;
                String response = semaphore.take(rand);
                System.out.println(response);
                TimeUnit.SECONDS.sleep(3);
                response = semaphore.release(rand);
                System.out.println(response);
            }
        } 
    }