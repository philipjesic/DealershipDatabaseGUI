package com.dealership.database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class MainForm {
    private JPanel MainForm;
    private JPanel MainFormPanel;
    private JButton customerFormButton;
    private JButton receptionistFormButton;
    private JButton mechanicFormButton;
    private JButton salesMenButton;
    private JButton managerFormButton;

    public MainForm() {
        customerFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Customer Search Form");
                frame.setContentPane(new CustomerSearchForm().CustomerSearchPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        receptionistFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Receptionist Search Form");
                frame.setContentPane(new ReceptionistForm().ReceptionistSearchPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        mechanicFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Mechanic Search Form");
                frame.setContentPane(new MechanicForm().MechanicSeachPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        salesMenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Salesmen Search Form");
                frame.setContentPane(new SalesMenForm().SalesMenSearchPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

            }
        });
        managerFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Manager Search Form");
                frame.setContentPane(new ManagerForm().ManagerFormPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Car Dealership Network");

        try {
            // get the connection
            SQL.getConnection();
            frame.setContentPane(new MainForm().MainFormPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(500,500);
            frame.setVisible(true);
        }
        catch (Exception e) {
            // JOptionPane.showMessageDialog(frame, e.getMessage());
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

}
