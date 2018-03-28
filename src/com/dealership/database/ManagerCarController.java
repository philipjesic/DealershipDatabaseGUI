package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ManagerCarController {
    public JPanel ManagerCarControllerPanel;
    private JTextField enterVinEg67890TextField;
    private JTextField enterMileageTextField;
    private JTextField enterSoldOrNotTextField;
    private JTextField enterNewOrUsedTextField;
    private JTextField enterCleanOrRebuiltTextField;
    private JTextField enterYearEg2018TextField;
    private JTextField enterModelEGTextField;
    private JTextField enterMakeEGTextField;
    private JTextField enterTrimTextField;
    private JButton insertCarIntoDatabaseButton;
    private JTextField enterFeaturesEGTextField;
    private JTextField listPriceTextField;
    private JTextField vinMustExistInTextField;
    private JButton insertButton;
    private JTextField enterVinHereTextField;
    private JTextField newListPriceTextField;
    private JButton updateListPriceButton;
    private JTextField enterVinMustExistTextField;
    private JTextField enterDateEGTextField;
    private JTextField enterFinalPriceTextField;
    private JTextField enterCustomerIDTextField;
    private JTextField enterManagerIDTextField;
    private JTextField enterSalesmenIDTextField;
    private JButton finalizeDealEnterAllButton;
    private JTextField enterVinToFindTextField;
    private JButton findRecordButton;


    public ManagerCarController() {
        insertCarIntoDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    int vin = Integer.parseInt(enterVinEg67890TextField.getText());
                    int miles = Integer.parseInt(enterMileageTextField.getText());
                    String issold = enterSoldOrNotTextField.getText();
                    String prevStatus = enterNewOrUsedTextField.getText();
                    String titleStatus = enterCleanOrRebuiltTextField.getText();
                    int year = Integer.parseInt(enterYearEg2018TextField.getText());
                    String model = enterModelEGTextField.getText();
                    String make = enterMakeEGTextField.getText();
                    String trim = enterTrimTextField.getText();
                    String features = enterFeaturesEGTextField.getText();
                    SQL.insertCarFeatures(SQL.con, year, model, make, trim, features);
                    SQL.insertCar(SQL.con, vin,miles,issold,prevStatus,titleStatus,year,model,make,trim);
                    ResultSet rs = SQL.getCarRecord(SQL.con, vin);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1) {
                    try {
                        int vin = Integer.parseInt(enterVinEg67890TextField.getText());
                        int miles = Integer.parseInt(enterMileageTextField.getText());
                        String issold = enterSoldOrNotTextField.getText();
                        String prevStatus = enterNewOrUsedTextField.getText();
                        String titleStatus = enterCleanOrRebuiltTextField.getText();
                        int year = Integer.parseInt(enterYearEg2018TextField.getText());
                        String model = enterModelEGTextField.getText();
                        String make = enterMakeEGTextField.getText();
                        String trim = enterTrimTextField.getText();
                        String features = enterFeaturesEGTextField.getText();
                        SQL.insertCar(SQL.con, vin, miles, issold, prevStatus, titleStatus, year, model, make, trim);
                        ResultSet rs = SQL.getCarRecord(SQL.con, vin);
                        JTable table = new JTable(MainForm.buildTableModel(rs));
                        JOptionPane.showMessageDialog(null, new JScrollPane(table));
                    } catch (Exception e2){
                        String message = e2.getMessage();
                        JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(vinMustExistInTextField.getText());
                    int listprice = Integer.parseInt(listPriceTextField.getText());
                    SQL.insertSaleRecord(SQL.con, vin,listprice);
                    ResultSet rs = SQL.getSalesRecord(SQL.con, vin);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    //JOptionPane.showMessageDialog();
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        updateListPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(enterVinHereTextField.getText());
                    int listprice = Integer.parseInt(newListPriceTextField.getText());
                    SQL.updateListPrice(SQL.con,vin,listprice);
                    ResultSet rs = SQL.getSalesRecord(SQL.con, vin);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        finalizeDealEnterAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(enterVinMustExistTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(enterDateEGTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    int finalPrice = Integer.parseInt(enterFinalPriceTextField.getText());
                    int customerId = Integer.parseInt(enterCustomerIDTextField.getText());
                    int managerId = Integer.parseInt(enterManagerIDTextField.getText());
                    int salesmenId = Integer.parseInt(enterSalesmenIDTextField.getText());
                    SQL.updateisSold(SQL.con,vin);
                    SQL.updateSoldCar(SQL.con,vin,sqlDate,finalPrice,customerId,managerId,salesmenId);
                    ResultSet rs = SQL.getSalesRecordForCar(SQL.con,vin);
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
        findRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int vin = Integer.parseInt(enterVinToFindTextField.getText());
                    ResultSet rs = SQL.getCarRecord(SQL.con,vin);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
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
