/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author J.Guzmán
 */
public interface ClientInterface extends Remote {
    public User getUser() throws RemoteException;
    
    public ClientInterfaceP2P getClientInterfaceP2P() throws RemoteException;
    
    public void notifyConnection(String friendEmail, ClientInterfaceP2P friend) throws RemoteException;

    public void notifyDisconnection(String friendEmail) throws RemoteException;

    public void notifyRequest(String friendEmail) throws RemoteException;
    
    public void addFriend(String friendEmail) throws RemoteException;
    }
