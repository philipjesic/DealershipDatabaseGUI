package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ManagerDealershipController {
    public JPanel ManagerDealershipPanel;
    private JButton seeDealershipsButton;

    public ManagerDealershipController() {
        seeDealershipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ResultSet rs = SQL.getDealerships(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                }
                catch (Exception ex) {
                    String message = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
