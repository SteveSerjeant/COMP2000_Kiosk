package com.view;

import com.controller.systemController;
import com.model.Stock;
import com.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ICheckout {
    public JPanel mainPanel;
    private JPanel leftPanel;
    private JLabel lblCheckout;
    private JList tempListFromBasket;
    private JList lstFromBasket;
    private JList lstForBasket;
    private JButton btnCash;
    private JButton btnQuit;
    private JButton btnCard;
    private JButton btnReceipt;
    private JLabel lblTotal;
    private JLabel lblAmount;
    private JButton btnBack;
    public JOptionPane checkPopup;
    String forReceipt;
    Float cashEntered = 0.00f;
    Float change = 0.00f;


    File listOfStock  = new File("resources\\StockList.txt");
    public String separator = "\\|";

    public ICheckout (JList shopList, Float tempTotal, JFrame startFrame, JFrame checkout){

        lstFromBasket.setModel(shopList.getModel());
        tempListFromBasket = shopList;

        lblAmount.setText("£" + String.format("%.2f",tempTotal));

        btnCash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithCash();
            }
        });
        btnCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithCard();
            }
        });

        btnReceipt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputReceipt();
            }
        });


        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                //systemController.checkoutStart( );
            }
        });

        btnQuit.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                systemController.systemStart(checkout);
                checkout.setVisible(false);
            }
        }));



    }

    public void payWithCash(){

        //define date/time format for receipt.
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd:MM:yyyy HH.mm.ss");
        Float total = 0.00f;

        JFrame popUp = new JFrame();
        popUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //temporary float for cash amounts entered.
        cashEntered = Float.parseFloat(checkPopup.showInputDialog(popUp, "Please enter cash amount tendered."));

        //store transaction total from lblAmount
        String transTotal = lblAmount.getText();

        transTotal = transTotal.replace("£", "");
        total = Float.parseFloat(transTotal);

        //check amount entered.
        if(cashEntered >= total){

            change = cashEntered - total;
            //create message to be used in if receipt wanted.
            forReceipt = "***** STEVE'S MINI-MART *****\n\n" +
                    "Items:    " + lstFromBasket.getModel().toString() +
                    "\n" + "Total:       " + lblAmount.getText() + "\n" +
                    "Cash:       £" + (String.format("%.2f",cashEntered))  + "\n" +
                    "Change:  " + (String.format("£" + "%.2f", change))
                    + "\n\n" + "Date:  " + timeNow.format(dateTime) +
                    "\n" + " ******** THANK YOU *********";
            lblAmount.setText("");
            lstFromBasket.setVisible(false);
            //updateStockFile();
        }
        else{
            checkPopup.showMessageDialog(popUp, "Insufficient cash, please enter more.");

        }


    }

    public void payWithCard(){

        //define date/time format for receipt, same as payWithCash
        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd:MM:yyyy HH.mm.ss");
        Float total = 0.00f;

        JFrame popUpCard = new JFrame();
        popUpCard.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        //message for popup
        int option = checkPopup.showConfirmDialog(popUpCard, "Card Verification",
                "WARNING", JOptionPane.INFORMATION_MESSAGE);

        if(option == JOptionPane.YES_OPTION){
            checkPopup.showMessageDialog(popUpCard, "Verification Successful",
                    "WARNING", JOptionPane.INFORMATION_MESSAGE);
            forReceipt = "***** STEVE'S MINI-MART *****\n\n" +
                    "Items:    " + lstFromBasket.getModel().toString() +
                    "\n" + "Total:       " + lblAmount.getText() + "\n" +
            "Card Verification Successful" + "\n\n" + "Date:  " +
                    timeNow.format(dateTime) + "\n" +
                    " ******** THANK YOU *********";
            lblAmount.setText("");
            lstFromBasket.setVisible(false);
            //updateStockFile();
        }
        else {
            checkPopup.showMessageDialog(popUpCard,
                    "VERIFICATION UNSUCCESSFUL. Try again?");
        }


    }

    public void outputReceipt(){
        JFrame popReceipt = new JFrame();
        popReceipt.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        checkPopup.showMessageDialog(popReceipt, forReceipt, "RECEIPT", JOptionPane.INFORMATION_MESSAGE);


    }


}
