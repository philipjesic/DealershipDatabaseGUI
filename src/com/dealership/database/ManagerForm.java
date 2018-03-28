package com.dealership.database;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerForm {
    public JPanel ManagerFormPanel;
    private JButton updateOrInsertCarButton;
    private JButton checkUpdateOrAddButton;
    private JButton addOrViewGeneralButton;
    private JButton checkImportantCarSalesButton;

    public ManagerForm() {
        checkUpdateOrAddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Check or Update Staff Form");
                frame.setContentPane(new ManagerStaffController().ManagerStaffControllerPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        updateOrInsertCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add or Update Car Form");
                frame.setContentPane(new ManagerCarController().ManagerCarControllerPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        checkImportantCarSalesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Add or Update Car Form");
                frame.setContentPane(new ImportantCarSalesID().ImportantCarSalesMenPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
        addOrViewGeneralButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("View Dealerships");
                frame.setContentPane(new ManagerDealershipController().ManagerDealershipPanel);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
