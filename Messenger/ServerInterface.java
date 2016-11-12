/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author J.Guzmán
 */
public interface ServerInterface extends Remote{
    public User login(ClientInterface clientObj) throws java.rmi.RemoteException, ClassNotFoundException, SQLException;
    public boolean logout(ClientInterface clientObj) throws java.rmi.RemoteException;
    public ArrayList<String> search(String email) throws RemoteException;
    public boolean sendRequest(User user, String email) throws java.rmi.RemoteException;
    public boolean answerRequest(User user, String email, boolean accept) throws java.rmi.RemoteException;
}
