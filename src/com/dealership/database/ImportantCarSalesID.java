package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImportantCarSalesID {
    public JPanel ImportantCarSalesMenPanel;
    private JButton checkAveragePriceByButton;
    private JButton createViewForAverageButton;
    private JButton getNumberOfCarsButton;
    private JButton checkMostExpensiveBrandButton;
    private JButton checkLeastExpensiveBrandButton;

    public ImportantCarSalesID() {
        checkAveragePriceByButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                                                            ResultSet rs = null;
                                                            String selectSql = " SELECT *\n" +
                                                                    "FROM averagebrandprice";
                                                            Statement stmt = SQL.con.createStatement();
                                                            rs = stmt.executeQuery(selectSql);
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
        createViewForAverageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getAveragePriceByMakeView(SQL.con);
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
        getNumberOfCarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getNumberOfCarsForSale(SQL.con);
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
        checkMostExpensiveBrandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getMostExpensiveBrand(SQL.con);
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
        checkLeastExpensiveBrandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet rs = SQL.getLeastExpensiveBrand(SQL.con);
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
