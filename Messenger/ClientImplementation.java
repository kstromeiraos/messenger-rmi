
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author J.Guzmán
 */
public class ClientImplementation extends UnicastRemoteObject implements ClientInterface {

    private User user;
    private final ClientInterfaceP2P interfaceP2P;
    private final ServerInterface serverObj;
    private MainView mainView;

    public ClientImplementation(User user, ServerInterface serverObj) throws RemoteException {
        this.user = user;
        this.serverObj = serverObj;
        this.mainView = null;
        this.interfaceP2P = new ClientImplementationP2P(user);
    }

    @Override
    public User getUser() throws java.rmi.RemoteException {
        return this.user;
    }

    public void setUser(User user) {
        ((ClientImplementationP2P) interfaceP2P).setMe(user);
        this.user = user;
    }

    @Override
    public void notifyConnection(String friendEmail, ClientInterfaceP2P friend) throws RemoteException {

        if (user.getFriends().containsKey(friendEmail)) {
            user.getFriends().put(friendEmail, friend);
            Popup popup = new Popup(friendEmail + " online");
            popup.setSize(210, 290);
            popup.setResizable(false);
            popup.setTitle("CONEXION");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
            int x = 0, y = (int) rect.getMaxY() - popup.getHeight() - 35;
            popup.setLocation(x, y);
            popup.setVisible(true);
        } else {
            System.out.println(friendEmail + " no es mi amigo");
        }
        mainView.showFriends(user.getFriends());
    }

    @Override
    public void notifyDisconnection(String friendEmail) throws RemoteException {

        if (user.getFriends().containsKey(friendEmail)) {
            user.getFriends().put(friendEmail, null);
            Popup popup = new Popup(friendEmail + " disconnect");
            popup.setSize(210, 290);
            popup.setResizable(false);
            popup.setTitle("DESCONEXION");
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
            Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
            int x = 0, y = (int) rect.getMaxY() - popup.getHeight() - 35;
            popup.setLocation(x, y);
            popup.setVisible(true);
        } else {
            System.out.println(friendEmail + " no es mi amigo");
        }
        mainView.showFriends(user.getFriends());
    }

    @Override
    public ClientInterfaceP2P getClientInterfaceP2P() throws RemoteException {
        return this.interfaceP2P;
    }

    @Override
    public void notifyRequest(String friendEmail) throws RemoteException {
        if (!user.getFriends().containsKey(friendEmail)) {
            RequestView popup = new RequestView(serverObj, user, friendEmail, mainView);
            popup.setSize(420, 310);
            popup.setResizable(false);
            popup.setTitle("FRIEND REQUEST");
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        } else {
            System.out.println(friendEmail + " ya es mi amigo");
        }
    }

    @Override
    public void addFriend(String friendEmail) throws RemoteException {
        if (!user.getFriends().containsKey(friendEmail)) {
            user.getFriends().put(friendEmail, null);
        } else {
            System.out.println(friendEmail + " ya es mi amigo");
        }
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public ClientInterfaceP2P P2P() {
        return this.interfaceP2P;
    }
}
