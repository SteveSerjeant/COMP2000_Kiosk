package com.view;

import com.controller.systemController;
import com.model.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import  java.util.ArrayList;
import  java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;

public class ICheckout {
    public JPanel mainPanel;
    private JPanel leftPanel;
    private JLabel lblCheckout;
    private JList tempListFromBasket;
    private JList lstFromBasket;
    private JButton btnCash;
    private JButton btnBack;
    private JButton btnCard;
    private JButton btnReceipt;
    private JLabel lblTotal;
    private JLabel lblAmount;


    File listOfStock  = new File("resources\\StockList.txt");
    public String separator = "\\|";

    public ICheckout (JList shopList, Float tempTotal, JFrame startFrame, JFrame checkout){

        lstFromBasket.setModel(shopList.getModel());
        tempListFromBasket = shopList;

        lblCheckout.setText("£" + String.format("%.2f",tempTotal));

        btnBack.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //systemController.checkoutStart(checkout);
            }
        }));


    }
}
