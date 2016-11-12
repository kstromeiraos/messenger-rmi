
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author J.Guzmán
 */
public class ChatView extends javax.swing.JPanel {

    private final String emailother;
    private final String myemail;
    private final ClientInterfaceP2P peerObj;
    private User me;

    public ChatView(String other, User me, String myemail) {
        initComponents();
        this.emailother = other;
        this.me = me;
        this.peerObj = me.getFriends().get(other);
        this.myemail = myemail;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        inputText = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setMaximumSize(new java.awt.Dimension(650, 650));
        setMinimumSize(new java.awt.Dimension(650, 650));
        setPreferredSize(new java.awt.Dimension(650, 650));

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        inputText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTextActionPerformed(evt);
            }
        });

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(inputText, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputText)
                    .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void inputTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTextActionPerformed

    private void send(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send
        try {
            peerObj.send(">" + myemail + ": " + inputText.getText(), myemail, emailother);
            this.textArea.append(">" + myemail + ": " + inputText.getText()+"\n");
        } catch (RemoteException ex) {
            Logger.getLogger(ChatView.class.getName()).log(Level.SEVERE, null, ex);
        }
        inputText.setText("");
    }//GEN-LAST:event_send

    public JTextArea getjTextArea1() {
        return textArea;
    }

    public String getEmailother() {
        return emailother;
    }
       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputText;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}
