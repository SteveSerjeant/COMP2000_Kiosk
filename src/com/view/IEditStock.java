package com.view;

import com.model.Stock;
import com.controller.systemController;

import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import  java.io.FileNotFoundException;
import  java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class IEditStock {

    public JList lstGoods;
    public JPanel mainPanel;
    public JOptionPane popUpOption;
    public JFrame popUp = new JFrame();
    private JButton btnAddStock;
    private JButton btnEditStock;
    private JButton btnDeleteStock;
    private JButton btnExit;

    public String filepath = "resources\\StockList.txt";
    public String separator = "\\|";

    public IEditStock(JFrame frame, JList stockList){

        lstGoods.setModel(new DefaultListModel());


        popUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        loadStockData();


    }

    private void loadStockData(){

        Stock newStock = new Stock();
        newStock.loadStock();
        Stock[] tempArray = new Stock[0];
        tempArray = newStock.stockFile.toArray(tempArray);

        DefaultListModel stockModel = new DefaultListModel();

        //for loop extra comments to add
        int length = tempArray.length;

        for (int i = 0; i < length; i++ ){
            stockModel.addElement(tempArray[i].getCode() + " | " + tempArray[i].getProduct() + " | "
            + tempArray[i].getCost() + " | " + tempArray[i]);
        }
        lstGoods.setModel(stockModel);


    }

    //method to open the staff pane
    public void displayAdmin(){
        JFrame staffFrame = new JFrame("Staff");
        staffFrame.setContentPane(this.mainPanel);
        staffFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        staffFrame.pack();
        staffFrame.setVisible(true);
    }
}
