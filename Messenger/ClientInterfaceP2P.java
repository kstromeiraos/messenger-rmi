
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J.Guzmán
 */

public interface ClientInterfaceP2P extends Remote{
    public void send(String msj, String from, String to) throws RemoteException;
}
