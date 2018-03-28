package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ReceptionistForm {
    private JTextField ReceptionistIDTextField;
    private JButton searchButton;
    public JPanel ReceptionistSearchPanel;
    private JTextField HHMMSSEgTextField;
    private JTextField eg01NOVEMBER2017TextField;
    private JButton createNewAppointmentSpaceButton;
    private JTextField BookedApptReceptionistID;
    private JTextField BookedApptCustomerID;
    private JTextField BookedAppointmentTime;
    private JTextField BookedAppointmentDate;
    private JButton bookAppointmentMakeSureButton;
    private JTextField DeleteReceptionistIDTextfield;
    private JButton DeleteReceptionistIDButton;

    public ReceptionistForm() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = ReceptionistIDTextField.getText();
                if (customerId != null && customerId.trim().length() > 0) {
                    try {
                        int receptionst_INT = Integer.parseInt(customerId);
                        ResultSet rs = SQL.getReceptionstAppointments(SQL.con, receptionst_INT);
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
        });
        createNewAppointmentSpaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(eg01NOVEMBER2017TextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String initialtime = HHMMSSEgTextField.getText();

                    SQL.insertNewAppointmentSpace(SQL.con,sqlDate,initialtime);

                    ResultSet rs = SQL.getAppointmentSpace(SQL.con, sqlDate,initialtime);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                }catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        bookAppointmentMakeSureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(BookedAppointmentDate.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String initialtime = BookedAppointmentTime.getText();
                    int customerID = Integer.parseInt(BookedApptCustomerID.getText());
                    int receptionistID = Integer.parseInt(BookedApptReceptionistID.getText());
                    SQL.insertNewBookedAppointment(SQL.con,sqlDate,initialtime,customerID,receptionistID);

                    ResultSet rs = SQL.getBookedAppointment(SQL.con, sqlDate,initialtime,customerID,receptionistID);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                }catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        DeleteReceptionistIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int receptionistID = Integer.parseInt(DeleteReceptionistIDTextfield.getText());
                    SQL.deleteReceptionist(SQL.con,receptionistID);
                    ResultSet rs = SQL.getReceptionist(SQL.con, receptionistID);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                }catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
