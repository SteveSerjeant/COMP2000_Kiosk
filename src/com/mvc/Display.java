package com.mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display extends JFrame {

    private final JTextField stockTF = new JTextField(25);
    private final JLabel nameLbl = new JLabel("Enter Name: ");

    public Display(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200,500);

        stockTF.setHorizontalAlignment(SwingConstants.CENTER);
        nameLbl.setHorizontalAlignment(SwingConstants.CENTER);

        JButton editBtn = new JButton("Edit Name: ");

        mainPanel.add(nameLbl);
        mainPanel.add(stockTF);
        mainPanel.add(editBtn);

        this.add(mainPanel);
        this.setVisible(true);

        editBtn.addActionListener
                (new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String newName = stockTF.getText();
                        nameLbl.setText(newName);

                    }
                }
        );

    }
}
