
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J.Guzmán
 */
public class RequestView extends javax.swing.JFrame {

    private ServerInterface serverObj;
    private User user;
    private String friendEmail;
    private MainView mainView;

    public RequestView(ServerInterface serverObj, User user, String friendEmail, MainView mainView) {
        initComponents();
        this.friendEmail = friendEmail;
        this.serverObj = serverObj;
        this.user = user;
        this.mainView = mainView;
        jLabel2.setText(jLabel2.getText() + friendEmail);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 400));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Friend Request");

        jLabel2.setText("You've received a friend request from: ");

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptRequest(evt);
            }
        });

        jButton2.setText("Rechazar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                denyRequest(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(81, 81, 81))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addGap(42, 42, 42)
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acceptRequest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptRequest
        try {
            serverObj.answerRequest(user, friendEmail, true);
            this.setVisible(false);
        } catch (RemoteException ex) {
            Logger.getLogger(RequestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainView.showFriends(user.getFriends());
    }//GEN-LAST:event_acceptRequest

    private void denyRequest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_denyRequest
        try {
            serverObj.answerRequest(user, friendEmail, false);
            this.setVisible(false);
        } catch (RemoteException ex) {
            Logger.getLogger(RequestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        mainView.showFriends(user.getFriends());
    }//GEN-LAST:event_denyRequest


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
