
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author J.Guzmán
 */

public class User implements Serializable{
    private String email;
    private String password;
    private HashMap<String, ClientInterfaceP2P> friends;
    private ArrayList<String> requests;
    private ArrayList<String> openChats;

    public User(String email, String password, String ip, HashMap<String, ClientInterfaceP2P> friends) throws RemoteException{
        this.email = email;
        this.password = password;
        this.friends = friends;
        openChats = new ArrayList<>();
    }

    public User(String email) throws RemoteException{
        this.email = email;
    }

    public User() {
        openChats = new ArrayList<>();
        requests = new ArrayList<>();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, ClientInterfaceP2P> getFriends() {
        return friends;
    }

    public void setFriends(HashMap<String, ClientInterfaceP2P> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<String> requests) {
        this.requests = requests;
    }

    public ArrayList<String> getOpenChats() {
        return openChats;
    }

    public void setOpenChats(ArrayList<String> openChats) {
        this.openChats = openChats;
    }
    
}
