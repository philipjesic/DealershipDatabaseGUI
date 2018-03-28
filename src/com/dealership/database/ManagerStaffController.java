package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

public class ManagerStaffController {
    public JPanel ManagerStaffControllerPanel;
    private JButton searchMechanicLastName;
    private JTextField enterMechanicLastNameTextField;
    private JButton searchReceptionistLastName;
    private JTextField enterReceptionistLastNameTextField;
    private JButton searchManagerLastName;
    private JTextField enterManagerLastNameTextField;
    private JButton searchSalesmenLastName;
    private JTextField enterSalesmenLastNameTextField;
    private JTextField enterNewStaffIDTextField;
    private JTextField enterNewStaffDOBTextField;
    private JTextField enterNewStaffFirstTextField;
    private JTextField enterNewStaffLastTextField;
    private JTextField enterNewStaffDealershipTextField;
    private JTextField enterDealershipNumberTextField;
    private JTextField enterWorkStatusEgTextField;
    private JButton addToMechanicButton;
    private JButton addToManagerButton;
    private JButton addToSalesmenButton;
    private JButton addToReceptionistButton;

    public ManagerStaffController() {
        searchMechanicLastName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lastName = enterMechanicLastNameTextField.getText();
                    ResultSet rs = SQL.getMechanicsByLastName(SQL.con, lastName);
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

        searchReceptionistLastName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lastName = enterReceptionistLastNameTextField.getText();
                    ResultSet rs = SQL.getReceptionistByLastName(SQL.con, lastName);
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
        searchManagerLastName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lastName = enterManagerLastNameTextField.getText();
                    ResultSet rs = SQL.getManagerByLastName(SQL.con, lastName);
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
        searchSalesmenLastName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String lastName = enterSalesmenLastNameTextField.getText();
                    ResultSet rs = SQL.getSalesmenByLastName(SQL.con, lastName);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionP
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
            }
            }
        });
        addToMechanicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(enterNewStaffIDTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(enterNewStaffDOBTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String f_name = enterNewStaffFirstTextField.getText();
                    String l_name = enterNewStaffLastTextField.getText();
                    String city = enterNewStaffDealershipTextField.getText();
                    int dnumber = Integer.parseInt(enterDealershipNumberTextField.getText());
                    String workstatus = enterWorkStatusEgTextField.getText();
                    SQL.insertMechanic(SQL.con, id,sqlDate,f_name,l_name,city,dnumber,workstatus);
                    ResultSet rs = SQL.getMechanicsByID(SQL.con, id);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addToReceptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(enterNewStaffIDTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(enterNewStaffDOBTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String f_name = enterNewStaffFirstTextField.getText();
                    String l_name = enterNewStaffLastTextField.getText();
                    String city = enterNewStaffDealershipTextField.getText();
                    int dnumber = Integer.parseInt(enterDealershipNumberTextField.getText());
                    String workstatus = enterWorkStatusEgTextField.getText();
                    SQL.insertReceptionist(SQL.con, id,sqlDate,f_name,l_name,city,dnumber,workstatus);
                    ResultSet rs = SQL.getReceptionistByID(SQL.con, id);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addToSalesmenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(enterNewStaffIDTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(enterNewStaffDOBTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String f_name = enterNewStaffFirstTextField.getText();
                    String l_name = enterNewStaffLastTextField.getText();
                    String city = enterNewStaffDealershipTextField.getText();
                    int dnumber = Integer.parseInt(enterDealershipNumberTextField.getText());
                    String workstatus = enterWorkStatusEgTextField.getText();
                    SQL.insertSalesmen(SQL.con, id,sqlDate,f_name,l_name,city,dnumber,workstatus);
                    ResultSet rs = SQL.getSalesmenByID(SQL.con, id);
                    JTable table = new JTable(MainForm.buildTableModel(rs));
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));

                } catch (Exception e1){
                    String message = e1.getMessage();
                    JOptionPane.showMessageDialog(new JFrame(), message, "Error In Input",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        addToManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(enterNewStaffIDTextField.getText());
                    java.util.Date utilDate = new SimpleDateFormat("dd MMM yyyy").parse(enterNewStaffDOBTextField.getText());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    String f_name = enterNewStaffFirstTextField.getText();
                    String l_name = enterNewStaffLastTextField.getText();
                    String city = enterNewStaffDealershipTextField.getText();
                    int dnumber = Integer.parseInt(enterDealershipNumberTextField.getText());
                    String workstatus = enterWorkStatusEgTextField.getText();
                    SQL.insertManager(SQL.con, id,sqlDate,f_name,l_name,city,dnumber,workstatus);
                    ResultSet rs = SQL.getManagerByID(SQL.con, id);
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
