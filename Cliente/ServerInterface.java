/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente;

import java.rmi.Remote;
import java.sql.SQLException;

/**
 *
 * @author J.Guzmán
 */
public interface ServerInterface extends Remote{
    public User login(ClientInterface clientObj) throws java.rmi.RemoteException, ClassNotFoundException, SQLException;
    public void logout(User me) throws java.rmi.RemoteException;
    public void send(String message, User me, User other) throws java.rmi.RemoteException;
    public void sendRequest(User me, String email) throws java.rmi.RemoteException;
    public void acceptRequest(User me, User other) throws java.rmi.RemoteException;
}
