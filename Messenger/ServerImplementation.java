
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.Guzmán
 */
public class ServerImplementation extends UnicastRemoteObject implements ServerInterface, Serializable {

    private final HashMap<String, ClientInterface> users; //<email, clientInterface>
    private Connection con;
    private Statement stat;

    public ServerImplementation() throws RemoteException {
        super();
        users = new HashMap<>();
    }

    private void connectToDB() throws SQLException, ClassNotFoundException {
        con = new MySQLConnection().getConnection();
        stat = con.createStatement();
    }

    @Override
    public synchronized User login(ClientInterface clientObj) throws java.rmi.RemoteException, ClassNotFoundException, SQLException {
        HashMap<String, ClientInterfaceP2P> friends;
        ArrayList<String> requests;
        String friendsLine, requestsLine;
        User user = clientObj.getUser();

        System.out.println("Trying to log: " + user.getEmail());

        try{
        connectToDB();
        ResultSet rs = stat.executeQuery("SELECT * FROM usuarios WHERE email='" + user.getEmail() + "' AND password = '" + user.getPassword() + "'");

        if (rs.next()) {
            if (!users.containsKey(user.getEmail())) {
                users.put(user.getEmail(), clientObj); //ADD USER TO ONLINE HASHMAP
                //Decoding friends
                friendsLine = rs.getString("amigos");
                System.out.println(friendsLine);
                friends = decodeFriends(friendsLine, user);
                user.setFriends(friends);
                //Decoding requests
                requestsLine = rs.getString("peticiones");
                System.out.println("Requestsline: " + requestsLine);
                requests = decodeRequests(requestsLine);
                user.setRequests(requests);
                System.out.println("Loged " + user.getEmail() + "! Users online: " + users.size());
                return user;
            } else {
                System.out.println("User " + user.getEmail() + " already loged in");
            }
        } else {
            System.out.println("Login failure! Users online: " + users.size());
        }
        }catch(ClassNotFoundException | RemoteException | SQLException e){
            Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
        return null;
    }

    private HashMap<String, ClientInterfaceP2P> decodeFriends(String friendsLine, User online) throws RemoteException {
        HashMap<String, ClientInterfaceP2P> friends = new HashMap<>();
        ClientInterfaceP2P friendInterface, onlineInterface = users.get(online.getEmail()).getClientInterfaceP2P();
        String friendString;

        StringTokenizer t = new StringTokenizer(friendsLine, ",");
        while (t.hasMoreElements()) {
            friendString = t.nextToken();
            if (users.containsKey(friendString)) {
                friendInterface = users.get(friendString).getClientInterfaceP2P();
                users.get(friendString).notifyConnection(online.getEmail(), onlineInterface);
            } else {
                friendInterface = null;
            }
            friends.put(friendString, friendInterface);
        }
        return friends;
    }

    private ArrayList<String> decodeRequests(String requestsLine) throws RemoteException {
        ArrayList<String> requests = new ArrayList<>();
        String requestString;

        StringTokenizer t = new StringTokenizer(requestsLine, ",");
        while (t.hasMoreElements()) {
            requestString = t.nextToken();
            requests.add(requestString);
        }
        return requests;
    }

    @Override
    public synchronized boolean logout(ClientInterface clientObj) throws RemoteException {
        User me = clientObj.getUser();
        String friendEmail;

        if (users.containsKey(me.getEmail())) {
            users.remove(me.getEmail());
            Iterator it = me.getFriends().keySet().iterator();
            while (it.hasNext()) {
                friendEmail = (String) it.next();
                if (users.containsKey(friendEmail)) {
                    users.get(friendEmail).notifyDisconnection(me.getEmail());
                }
            }
            System.out.println("Users online: " + users.size());
            return true;
        }
        System.out.println("Users online: " + users.size());
        return false;
    }

    @Override
    public synchronized boolean sendRequest(User user, String email) throws RemoteException {//SOLO ENVIAR EL USER
//        User user = clientObj.getUser();
        if (users.containsKey(user.getEmail())) {
            if (users.containsKey(email)) {//SI ESTA CONECTADO
                users.get(email).notifyRequest(user.getEmail());
                return true;
            } else {//SI NO ESTA CONECTADO
                try {
                    connectToDB();
                    ResultSet rs = stat.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
                    if (rs.next()) {
                        String requestString = rs.getString("peticiones");
                        if ("".equals(requestString)) {
                            requestString = user.getEmail();
                        } else {
                            requestString += "," + user.getEmail();
                        }
                        stat.executeUpdate("UPDATE usuarios SET peticiones = '" + requestString + "' WHERE email = '" + email + "'");
                        return true;
                    }
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public synchronized boolean answerRequest(User user, String email, boolean accept) throws RemoteException {
        // user ==> el que acepta // email ==> el que es aceptado
        String amigosString, peticionesString;
        try {
            connectToDB();
            ResultSet rs = stat.executeQuery("SELECT * FROM usuarios WHERE email = '" + user.getEmail() + "'");
            if (rs.next()) {
                peticionesString = rs.getString("peticiones");
                if (accept) {
                    //Añadimos el nuevo amigo (el que envía la petición) a la tabla del usuario que RECIBE la petición
                    amigosString = rs.getString("amigos");
                    if ("".equals(amigosString)) {
                        amigosString = email;
                    } else {
                        amigosString += "," + email;
                    }
                    stat.executeUpdate("UPDATE usuarios SET amigos = '" + amigosString + "' WHERE email = '" + user.getEmail() + "'");

                    //Añadimos el nuevo amigo (el que RECIBE la petición) a la tabla del usuario que envía la petición
                    rs = stat.executeQuery("SELECT * FROM usuarios WHERE email = '" + email + "'");
                    if (rs.next()) {

                        amigosString = rs.getString("amigos");
                        if ("".equals(amigosString)) {
                            amigosString = user.getEmail();
                        } else {
                            amigosString += "," + user.getEmail();
                        }
                        stat.executeUpdate("UPDATE usuarios SET amigos = '" + amigosString + "' WHERE email = '" + email + "'");
                    }
                }
                //Eliminamos la petición de la tabla de peticiones del usuario que la recibió
                StringTokenizer t = new StringTokenizer(peticionesString, ",");
                peticionesString = "";
                while (t.hasMoreElements()) {
                    String temp = t.nextToken();
                    if (!temp.matches(email)) {
                        peticionesString += temp + ",";
                    }
                }
                stat.executeUpdate("UPDATE usuarios SET peticiones = '" + peticionesString + "' WHERE email = '" + user.getEmail() + "'");

                if (accept) {//si la solicitud es aceptada se les notifica a los dos de la conexion del otro si es que estan online
                    if (users.containsKey(user.getEmail())) {
                        users.get(user.getEmail()).addFriend(email);
                        if (users.containsKey(email)) {
                            users.get(email).addFriend(user.getEmail());
                            users.get(user.getEmail()).notifyConnection(email, users.get(email).getClientInterfaceP2P());
                            users.get(email).notifyConnection(user.getEmail(), users.get(user.getEmail()).getClientInterfaceP2P());
                        }
                    }
                }
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public synchronized ArrayList<String> search(String email) throws RemoteException{
        ArrayList<String> results = new ArrayList<>();
        try {
            connectToDB();
            ResultSet rs = stat.executeQuery("SELECT * FROM usuarios WHERE email = '%"+email+"%'");
            while (rs.next()) {
                String eachEmail = rs.getString("email");
                results.add(eachEmail);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServerImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return results;
    }

}
