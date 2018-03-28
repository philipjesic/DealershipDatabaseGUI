package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class CustomerSearchForm {
    public JPanel CustomerSearchPanel;
    private JButton searchButton;
    private JTextField CustomerIDTextField;
    private JButton viewAvailableAppoimentsButton;
    private JButton createCustomerProfileMakeButton;
    private JTextField eg12345MustBeTextField;
    private JTextField eg0000000NoAreaTextField;
    private JTextField egBobTextField;
    private JTextField egJeffersonTextField;

    public CustomerSearchForm() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = CustomerIDTextField.getText();
                if (customerId != null && customerId.trim().length() > 0) {
                    try {
                        int customerID_INT = Integer.parseInt(customerId);
                        ResultSet rs = SQL.getCustomerAppointments(SQL.con, customerID_INT);
                        // commented out use to debug the GUI
                        // SQL.printDataGUI(rs);
                        // It creates and displays the table
                        JTable table = new JTable(MainForm.buildTableModel(rs));

                        // Closes the Connection

                        JOptionPane.showMessageDialog(null, new JScrollPane(table));
                    }
                    catch (Exception ex) {
                       String message = ex.getMessage();
                       JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                       JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        }
        );
        viewAvailableAppoimentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ResultSet rs = SQL.getAvailableAppointments(SQL.con);
                    JTable table = new JTable(MainForm.buildTableModel(rs));

                    // Closes the Connection

                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                }
                catch (Exception ex) {
                    String message = ex.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        createCustomerProfileMakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int cid = Integer.parseInt(eg12345MustBeTextField.getText());
                    int phonenumber = Integer.parseInt(eg0000000NoAreaTextField.getText());
                    String firstName = egBobTextField.getText();
                    String lastName = egJeffersonTextField.getText();

                    SQL.insertNewCustomer(SQL.con,cid,phonenumber,firstName,lastName);
                    ResultSet rs = SQL.getCustomer(SQL.con,cid);
                    JTable table = new JTable(MainForm.buildTableModel(rs));


                    // Closes the Connection

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
