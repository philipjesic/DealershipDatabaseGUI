package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class SalesMenForm {
    private JButton CarsForSaleButton;
    private JTextField enterVinTextField;
    private JButton SearchForSaleRecord;
    public JPanel SalesMenSearchPanel;
    private JButton SearchCarsBasedOnPriceButton;
    private JTextField enterMinPriceTextField;
    private JTextField enterMaxPriceTextField;
    private JButton findCarsBasedOnMileage;
    private JTextField enterMinMileageTextField;
    private JTextField enterMaxMileageTextField;

    public SalesMenForm() {
        CarsForSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getUnsoldCars(SQL.con,"Not Sold");
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
        SearchForSaleRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(enterVinTextField.getText());
                    ResultSet rs = SQL.getSalesRecordForCar(SQL.con, vin);
                    // commented out use to debug the GUI
                    // SQL.printDataGUI(rs);
                    // It creates and displays the table
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
        SearchCarsBasedOnPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int minPrice = Integer.parseInt(enterMinPriceTextField.getText());
                    int maxPrice = Integer.parseInt(enterMaxPriceTextField.getText());
                    ResultSet rs = SQL.getCarsInPriceRange(SQL.con, minPrice,maxPrice);
                    // commented out use to debug the GUI
                    // SQL.printDataGUI(rs);
                    // It creates and displays the table
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
        findCarsBasedOnMileage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int minKm = Integer.parseInt(enterMinMileageTextField.getText());
                    int maxKm = Integer.parseInt(enterMaxMileageTextField.getText());
                    ResultSet rs = SQL.getCarsInMileageRange(SQL.con, minKm,maxKm);
                    // commented out use to debug the GUI
                    // SQL.printDataGUI(rs);
                    // It creates and displays the table
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
    }
}
