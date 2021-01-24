/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package storeinfosystem;

import wrapper.database;

/**
 *
 * @author humbata1
 */
public class Main {
    public static void main(String args[]){
      java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 database.openBusDatabase();
                GeneralGUI dialog = new GeneralGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setTitle("Store İnfo System");
                dialog.setResizable(false);
                dialog.setVisible(true);
            }
        });      
}
}
