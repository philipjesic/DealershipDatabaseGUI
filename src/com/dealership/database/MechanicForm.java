package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class MechanicForm {
    private JButton searchButtonForAllServices;
    public JPanel MechanicSeachPanel;
    private JTextField vinTextField;
    private JTextField MechanicIDTextField;
    private JTextField ServiceIDTextField;
    private JTextField OrderNumberTextField;
    private JTextField DateTextField;
    private JTextField HoursTextField;
    private JTextField PricetextField;
    private JTextField MileageTextField;
    private JButton insertButton;
    private JButton searchButtonForVin;
    private JTextField vinSearchField;

    public MechanicForm() {
        searchButtonForAllServices.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getAllServicedCars(SQL.con);
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
        });
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(vinTextField.getText());
                    int mechanicID = Integer.parseInt(MechanicIDTextField.getText());
                    int serviceID = Integer.parseInt(ServiceIDTextField.getText());
                    int orderNumber = Integer.parseInt(OrderNumberTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(DateTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    int hours = Integer.parseInt(HoursTextField.getText());
                    int price = Integer.parseInt(PricetextField.getText());
                    int mileage = Integer.parseInt(MileageTextField.getText());
                    SQL.insertServiceRecord(SQL.con, vin,mechanicID,serviceID,orderNumber,sqlDate,hours,price,mileage);
                    ResultSet rs = SQL.getServiceRecordForVin(SQL.con,vin);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    // Closes the Connection
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        searchButtonForVin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(vinSearchField.getText());
                    ResultSet rs = SQL.getServiceRecordForVin(SQL.con, vin);
                    // commented out use to debug the GUI
                    // SQL.printDataGUI(rs);
                    // It creates and displays the table
                    JTable table = new JTable(MainForm.buildTableModel(rs));

                    // Closes the Connection

                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1) {
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
