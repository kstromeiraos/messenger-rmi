
import java.awt.Desktop;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author J.Guzmán
 */
public class LoginView extends javax.swing.JPanel {

    private final JFrame window;
    private String registryURL;
    private ServerInterface serverObj;
    private ClientInterface clientObj;
    private User me;

    public LoginView(JFrame window) throws RemoteException {
        initComponents();
        this.window = window;
        link.setText("<html><u>Sign up!</u>");
        link1.setText("<html><u>Change password</u>");
        registryURL = "rmi://localhost:1099/callback";        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        Ico = new javax.swing.JLabel();
        link = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        link1 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(320, 540));
        setMinimumSize(new java.awt.Dimension(320, 540));
        setPreferredSize(new java.awt.Dimension(320, 540));

        emailLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        emailLabel.setText("E-mail");

        emailField.setText("guzman@guzman.com");
        emailField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailFieldActionPerformed(evt);
            }
        });

        passwordLabel.setFont(new java.awt.Font("Liberation Sans", 1, 15)); // NOI18N
        passwordLabel.setText("Password");

        loginButton.setText("Log In");
        loginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        Ico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logo_msn.png"))); // NOI18N

        link.setBackground(new java.awt.Color(255, 255, 255));
        link.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        link.setForeground(new java.awt.Color(6, 69, 163));
        link.setText("Don't have an account yet? Sign up!");
        link.setBorder(null);
        link.setBorderPainted(false);
        link.setContentAreaFilled(false);
        link.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkActionPerformed(evt);
            }
        });

        jPasswordField1.setText("guzman");
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        link1.setBackground(new java.awt.Color(255, 255, 255));
        link1.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        link1.setForeground(new java.awt.Color(6, 69, 163));
        link1.setText("Want to change my password");
        link1.setBorder(null);
        link1.setBorderPainted(false);
        link1.setContentAreaFilled(false);
        link1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                link1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(Ico))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordLabel)
                            .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(emailLabel)
                            .addComponent(jPasswordField1)
                            .addComponent(link, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(link1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Ico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(loginButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(link)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(link1)
                .addGap(14, 14, 14))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void emailFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailFieldActionPerformed

    private void init() {
        me = new User();
        try {
            serverObj = (ServerInterface) Naming.lookup(registryURL); //Looking up for registryURL
            clientObj = new ClientImplementation(me, serverObj);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            System.out.println("Exception connecting to " + registryURL + ". Check port and host\n");
        }

    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed

        init();
        me.setEmail(emailField.getText());
        me.setPassword(String.valueOf(jPasswordField1.getPassword()));
        try {
            me = serverObj.login(clientObj);
        } catch (RemoteException | ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (me != null) {
            ((ClientImplementation) this.clientObj).setUser(me);
            MainView mainView = new MainView(clientObj, serverObj, me, window);
            ((ClientImplementation) clientObj).setMainView(mainView);
            this.window.getContentPane().setVisible(false);
            this.window.setContentPane(mainView);
            this.window.getContentPane().setVisible(true);
        } else {
            Popup popup = new Popup("Login error");
            popup.setSize(290, 210);
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void linkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkActionPerformed
        URI uri = null;
        try {
            uri = new URI("http://fojo.es/messenger/");
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        open(uri);
    }//GEN-LAST:event_linkActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed

	}//GEN-LAST:event_jPasswordField1ActionPerformed

    private void link1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_link1ActionPerformed
        URI uri = null;
        try {
            uri = new URI("http://fojo.es/messenger/password.html");
        } catch (URISyntaxException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
        open(uri);
    }//GEN-LAST:event_link1ActionPerformed

    private static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) { /* TODO: error handling */ }
        } else { /* TODO: error handling */ }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ico;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JButton link;
    private javax.swing.JButton link1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel passwordLabel;
    // End of variables declaration//GEN-END:variables
}
