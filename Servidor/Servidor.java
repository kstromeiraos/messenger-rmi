/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author J.Guzmán
 */
public class Servidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String portNum, registryURL;
        try {
            startRegistry(1099);
            ServerImplementation exportedObj = new ServerImplementation(); //Creating a Server object
            registryURL = "rmi://localhost:1099/callback";
            Naming.rebind(registryURL, exportedObj);
            System.out.println("SERVER RUNNING...");
        } catch (IOException | NumberFormatException re) {
        }
    }

    //This method starts a RMI registry on the local host, if it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException {
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
            // This call will throw an exception if the registry does not already exist
        } catch (RemoteException e) {
            // No valid registry at that port.
            Registry registry = LocateRegistry.createRegistry(RMIPortNum);
        }
    }

}
