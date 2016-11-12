/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author J.Guzmán
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInterface {

    private final HashMap<String, ClientInterface> users;

    public ServerImplementation() throws RemoteException {
        super();
        users = new HashMap<>();
    }

    @Override
    public User login(ClientInterface clientObj) throws java.rmi.RemoteException, ClassNotFoundException, SQLException {
        Connection con;
        Statement stat;
        User usuario = clientObj.getUser();
        User u;
        ArrayList<User> friends;

        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://23.94.38.122:3306/messenger", "root", "1234");

        stat = con.createStatement();

        String seleccionar = "SELECT * FROM usuarios WHERE email='" + usuario.getEmail() + "' AND password = '" + usuario.getPassword() + "'";
        ResultSet rs = stat.executeQuery(seleccionar);

        if (rs.next()) {
            friends = decodeFriends(rs.getString("friends"));
            usuario = new User(usuario.getEmail(), usuario.getPassword(), usuario.getIp(), friends);
            clientObj.setUser(usuario);
            if (!users.containsKey(usuario.getEmail())) {
                users.put(usuario.getEmail(), clientObj);
            }
            return usuario;
        } else {
            return null;
        }
    }

    private ArrayList<User> decodeFriends(String friendsLine) {
        ArrayList<User> friends = new ArrayList<>();
        User user;
        StringTokenizer t = new StringTokenizer(friendsLine,",");
        while (t.hasMoreElements()) {//metodo para descomponer el string y obtener los emails
            user = new User(t.nextToken());
            getIsOnline(user);
            friends.add(user);
        }
        return friends;
    }

    private void getIsOnline(User user){
        if (users.containsKey(user.getEmail()))
            user.setOnline(true);
        else
            user.setOnline(false);
    }
    
    @Override
    public void logout(User me) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void send(String message, User me, User other) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendRequest(User me, String email) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptRequest(User me, User other) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
