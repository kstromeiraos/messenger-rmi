
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author J.Guzmán
 */
public class ClientImplementationP2P extends UnicastRemoteObject implements ClientInterfaceP2P {
    private ChatView chatView;
    private User me;

    public ClientImplementationP2P(User me) throws RemoteException {
        this.me = me;
    }

    @Override
    public void send(String msj, final String from, String to) {
        System.out.println(msj);
        if (!me.getOpenChats().contains(from)) {
            final Cliente_main view = new Cliente_main();
            chatView = new ChatView(from, me, to); 
            view.setContentPane(chatView);
            view.setTitle("Chat con "+from);
            view.setSize(650, 650);
            view.setLocationRelativeTo(null);
            view.setVisible(true);
            view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            view.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent event) {
                    me.getOpenChats().remove(from);
                    view.setVisible(false);
                }
            });
            me.getOpenChats().add(from);
        }
        this.chatView.getjTextArea1().append(msj+"\n");
    }

    public void setMe(User me) {
        this.me = me;
    }

    public void setChatView(ChatView chatView) {
        this.chatView = chatView;
    }
}
