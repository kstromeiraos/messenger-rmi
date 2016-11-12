
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author J.Guzmán
 */
public class Request extends javax.swing.JFrame {

    private ServerInterface serverObj;
    private User me;
    private ArrayList<String> results;
    private DefaultListModel listModel;

    public Request(ServerInterface serverObj, User me, ArrayList<String> results) {
        initComponents();
        this.serverObj = serverObj;
        this.me = me;
        this.results = results;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 300));
        setMinimumSize(new java.awt.Dimension(500, 300));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Your results");

        jButton1.setText("Send request");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendRequest(evt);
            }
        });

        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 12, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton1))
                        .addGap(20, 20, 20))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public final void showResults(ArrayList<String> results) {
        listModel.clear();
        for (String result : results) {
            listModel.addElement(result);
        }
        jList1.setModel(listModel);
    }

    private void sendRequest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendRequest
        String friendInput = (String) jList1.getSelectedValue();
        Popup popup;
        try {
            if (!serverObj.sendRequest(me, friendInput)) {
                popup = new Popup("Error al enviar solicitud");

            } else {
                popup = new Popup("Solicitud enviada correctamente");
            }
            popup.setSize(210, 290);
            popup.setResizable(false);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sendRequest

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
