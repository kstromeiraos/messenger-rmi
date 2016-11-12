
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author J.Guzmán
 */
public class MainView extends javax.swing.JPanel {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private final ClientInterface clientObj;
    private final ServerInterface serverObj;
    private final User me;
    private final JFrame window;
    private final DefaultListModel listModel;

    /**
     * Creates new form MainView
     *
     * @param clientObj
     * @param serverObj
     * @param me
     * @param window
     */
    public MainView(ClientInterface clientObj, ServerInterface serverObj, User me, JFrame window) {
        initComponents();
        this.clientObj = clientObj;
        this.serverObj = serverObj;
        this.me = me;
        this.window = window;
        miEmail.setText(me.getEmail());
        listModel = new DefaultListModel();
        init();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        friendInput = new javax.swing.JTextPane();
        sendRequest = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        miEmail = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(320, 540));
        setMinimumSize(new java.awt.Dimension(320, 540));
        setPreferredSize(new java.awt.Dimension(320, 540));

        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(list);

        jScrollPane2.setViewportView(friendInput);

        sendRequest.setFont(new java.awt.Font("Liberation Sans", 0, 11)); // NOI18N
        sendRequest.setText("Search email");
        sendRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEmail(evt);
            }
        });

        logout.setFont(new java.awt.Font("Liberation Sans", 0, 13)); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        miEmail.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        miEmail.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/person.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(miEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(logout)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(miEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(sendRequest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void init() {
        showFriends(me.getFriends());
        showRequests();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                logout();
                System.exit(0);
            }
        });
    }

    public final void showFriends(HashMap<String, ClientInterfaceP2P> friends) {
        Iterator it = friends.entrySet().iterator();
        Map.Entry map;
        listModel.clear();
        while (it.hasNext()) {
            map = (Map.Entry) it.next();
            if (map.getValue() != null) {
                listModel.addElement(map.getKey().toString() + " - Online");
            }
        }
        it = friends.entrySet().iterator();
        while (it.hasNext()) {
            map = (Map.Entry) it.next();
            if (map.getValue() == null) {
                listModel.addElement(map.getKey().toString() + " - Offline");
            }
        }
        list.setModel(listModel);
    }

    public final void showRequests() {
        ArrayList<String> requests = me.getRequests();
        for (String request : requests) {
            if (!me.getFriends().containsKey(request)) {
                RequestView popup = new RequestView(serverObj, me, request, this);
                popup.setSize(420, 310);
                popup.setResizable(false);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        }
    }

    private boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void searchEmail(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchEmail
        Popup popup;
        if (isEmail(friendInput.getText())) {
            if (!me.getFriends().containsKey(friendInput.getText())) {
                try {
                    ArrayList<String> results = serverObj.search(friendInput.getText());
                    if (results.size() > 0) {
                        Request req = new Request(serverObj, me, results);
                        req.setResizable(false);
                        req.setLocationRelativeTo(null);
                        req.setVisible(true);
                    } else {
                        popup = new Popup("No hay resultados");
                        popup.setSize(210, 290);
                        popup.setResizable(false);
                        popup.setLocationRelativeTo(null);
                        popup.setVisible(true);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                popup = new Popup(friendInput.getText() + " ya es tu amigo");
                popup.setSize(210, 290);
                popup.setResizable(false);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        } else {
            popup = new Popup("Formato email no valido");
            popup.setSize(210, 290);
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        }
        friendInput.setText("");
    }//GEN-LAST:event_searchEmail

    private void removeAllListeners() {
        WindowListener[] w = this.window.getWindowListeners();
        for (WindowListener w1 : w) {
            this.window.removeWindowListener(w1);
        }
    }

    private void logout() {
        try {
            if (serverObj.logout(clientObj)) {
                window.getContentPane().setVisible(false);
                LoginView loginView = new LoginView(this.window);
                this.window.setContentPane(loginView);
                this.window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                removeAllListeners();
                this.window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event) {
                        System.exit(0);
                    }
                });
                this.window.setTitle("MsgIt");
                this.window.getContentPane().setVisible(true);
            } else {
                System.out.println("Logout failure");
                Popup popup = new Popup("Logout error. Try again");
                popup.setSize(210, 290);
                popup.setResizable(false);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }

        } catch (RemoteException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        logout();
    }//GEN-LAST:event_logoutActionPerformed

    private void listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listMouseClicked
        list = (JList) evt.getSource();
        if (evt.getClickCount() == 2) {
            list.locationToIndex(evt.getPoint());
            String emailOtherNotCut = (String) list.getSelectedValue();
            StringTokenizer t = new StringTokenizer(emailOtherNotCut, " ");
            String emailOtherCut = t.nextToken();
            if (!me.getOpenChats().contains(emailOtherCut) && me.getFriends().get(emailOtherCut) != null) {
                final Cliente_main view = new Cliente_main();
                view.setTitle("Chat con " + emailOtherCut);
                ChatView chatView = new ChatView(emailOtherCut, me, me.getEmail());
                view.setContentPane(chatView);
                view.setLocationRelativeTo(null);
                view.setSize(650, 650);
                view.setVisible(true);
                view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                view.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event) {
                        me.getOpenChats().remove(((ChatView) view.getContentPane()).getEmailother());
                        view.setVisible(false);
                    }
                });
                ((ClientImplementationP2P) ((ClientImplementation) clientObj).P2P()).setChatView(chatView);
                me.getOpenChats().add(emailOtherCut);
            }
        }    }//GEN-LAST:event_listMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane friendInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList list;
    private javax.swing.JButton logout;
    private javax.swing.JLabel miEmail;
    private javax.swing.JButton sendRequest;
    // End of variables declaration//GEN-END:variables
}
