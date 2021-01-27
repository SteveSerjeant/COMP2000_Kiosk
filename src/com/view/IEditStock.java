package com.view;

import com.model.Stock;
import com.controller.systemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;


public class IEditStock {

    public JList lstGoods;
    public JPanel mainPanel;
    public JOptionPane popUpOption;
    public JFrame popUpBox = new JFrame();
    private JButton btnAddStock;
    private JButton btnEditStock;
    private JButton btnDeleteStock;
    private JButton btnExit;

    File listOfStock  = new File("resources\\StockList.txt");
    public String separator = "\\|";

    public IEditStock(JFrame frame, JList stockList){

        lstGoods.setModel(new DefaultListModel());


        popUpBox.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        loadStockData();

        btnAddStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addNewStock();
            }
        });

        btnEditStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStockLine();
            }
        });

        btnDeleteStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStockLine();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                systemController.systemStart(frame);
            }
        });


    }
    public void addNewStock(){

        //declare variable for stock details fields
        JTextField txtNewCode =  new JTextField();
        JTextField txtNewProduct = new JTextField();
        JTextField txtNewCost = new JTextField();
        JTextField txtNewQty = new JTextField();

        Object[] field = {
                "Stock Code: ", txtNewCode, "Product: ", txtNewProduct,
                "Cost: ", txtNewCost, "Quantity: ", txtNewQty
        };

        int number = popUpOption.showConfirmDialog(popUpBox, field,
                "Adding New Stock Item",JOptionPane.INFORMATION_MESSAGE);

        //if statements for dealing with inputs
        if(number == popUpOption.OK_OPTION) {
            if (txtNewCode != null && txtNewProduct != null
                    && txtNewCost != null && txtNewQty != null) {

                //create temp details for adding to data file
                createTempStock(txtNewCode.getText(), txtNewProduct.getText(),
                        txtNewCost.getText(), txtNewQty.getText());

                loadStockData();

                //console output for successful addition
                System.out.println("New Stock item Successfully added.");

            } else {
                popUpOption.showMessageDialog(popUpBox, "All Boxes Require Input.", "WARNING",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            } else

            {
            popUpOption.showMessageDialog(popUpBox, "New Product Addition Failed.", "WARNING",
                    JOptionPane.INFORMATION_MESSAGE);

        }

    }

    public  void createTempStock(String inNewCode, String inNewProduct,
                                 String inNewCost, String inNewQty){

        //create new stock object
        Stock newStock = new Stock();

        newStock.loadStock();

        newStock.setCode(inNewCode);
        newStock.setProduct(inNewProduct);
        newStock.setCost(inNewCost);
        newStock.setStockLevel(inNewQty);

        newStock.addNewStock(newStock);

        updateStockList(newStock);

    }

    public void editStockLine(){

        //reuses popUp box and fields as addNewStock
        int selection;
        JTextField txtNewCode =  new JTextField();
        JTextField txtNewProduct = new JTextField();
        JTextField txtNewCost = new JTextField();
        JTextField txtNewQty = new JTextField();

        //get index selected by user
        selection = lstGoods.getSelectedIndex();

        //temporary Stock Object
        Stock tempObject = new Stock();

        tempObject.loadStock();


        Stock tempStock = tempObject.getSelectedStock(selection);



        Object[] output = {
                "Code: ", txtNewCode, "Product: ", txtNewProduct,
                "Cost: ", txtNewCost, "Quantity: ", txtNewQty
        };
        int number = popUpOption.showConfirmDialog(popUpBox,output,
                "Editing Stock item.",JOptionPane.INFORMATION_MESSAGE);

        //dealing with inputs
        if(number == popUpOption.OK_OPTION){

            //and fields not empty
            if(txtNewCode != null && txtNewProduct != null
                    && txtNewCost != null && txtNewQty != null){

                editStockItem(txtNewCode.getText(), txtNewProduct.getText(),
                        txtNewCost.getText(), txtNewQty.getText());

                //popup if successsful
                popUpOption.showMessageDialog(popUpBox, "Stock Edit Successful.",
                        "NOTICE",JOptionPane.INFORMATION_MESSAGE);

                loadStockData();

                //console output for successful edit
                System.out.println("Edit product successful.");
            }

            else {
                popUpOption.showMessageDialog(popUpBox, "All Fields Required.",
                        "WARNING",JOptionPane.INFORMATION_MESSAGE);
            }

        }
        else {
            popUpOption.showMessageDialog(popUpBox, "Stock Edit Failed.",
                    "WARNING",JOptionPane.INFORMATION_MESSAGE);
        }




    }

    public void editStockItem(String inNewCode, String inNewProduct,
                              String inNewCost, String inNewQty){


        //create temp Object
        Stock tempStock = new Stock();

        tempStock.loadStock();

        //retrieving elected stock item
        Stock forEdit = tempStock.getSelectedStock(lstGoods.getSelectedIndex());



        forEdit.setCode(inNewCode);
        forEdit.setProduct(inNewProduct);
        forEdit.setCost(inNewCost);
        forEdit.setStockLevel(inNewQty);

        //updateStockList(forEdit);
        updateStockList(tempStock);




    }

    public void deleteStockLine(){

        int selectedIndex;

        selectedIndex = lstGoods.getSelectedIndex();

        //temporary stock object
        Stock tempObject = new Stock();

        //load data file
        tempObject.loadStock();

        Stock stockLine = tempObject.getSelectedStock(selectedIndex);

        //store prior to display
        String tcode = stockLine.getCode();
        String tprod = stockLine.getProduct();
        String tcost = stockLine.getCost();
        String tqty = stockLine.getStockLevel();

        Object[] tempMessage = {
                "Please confirm you wish to PERMANENTLY delete this product.",
                "Code: " + tcode, "Product: " + tprod,
                "Cost: " + tcost, "Stock Level: " + tqty

        };

        int confirm = popUpOption.showConfirmDialog(popUpBox, tempMessage,
                "Permanent Delete?", JOptionPane.INFORMATION_MESSAGE);

        //answer is yes
        if (confirm ==popUpOption.OK_OPTION){
            removeStockLine();
            popUpOption.showMessageDialog(popUpBox, "Permanent Delete Successful.",
                    "WARNING", JOptionPane.INFORMATION_MESSAGE);
            loadStockData();
        }
        else{
            popUpOption.showMessageDialog(popUpBox, "Permanent Deletion Failed.",
                    "WARNING", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void removeStockLine(){
        Stock tempStock = new Stock();
        tempStock.loadStock();

        Stock forDelete = tempStock.getSelectedStock(lstGoods.getSelectedIndex());

        tempStock.deleteStock(forDelete);

        updateStockList(tempStock);

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
            + tempArray[i].getCost() + " | " + tempArray[i].getStockLevel());
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

    public void updateStockList(Stock forUpdate){

        //temp array for stock item to be added

        ArrayList<Stock> tempStock = new ArrayList<>();

        tempStock = forUpdate.stockFile;

        try{
            String text = null;
            FileWriter writer = new FileWriter(listOfStock);

            for (int i = 0; i < tempStock.size(); i++){

                String tempCode;
                String tempCost;
                String tempList = "";
                String tempProduct;



                if (i >= 1){
                    tempList = "\n";

                }
                tempCode = tempStock.get(i).getCode();
                //add to array with separator
                tempList += tempCode + "|";

                tempProduct = tempStock.get(i).getProduct();
                tempList += tempProduct + "|";

                tempCost = tempStock.get(i).getCost();
                tempList += tempCost + "|";

                String tempQty = tempStock.get(i).getStockLevel();
                tempList += tempQty;

                writer.write(tempList);

            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
