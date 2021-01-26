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

        newStock.setCode(Integer.parseInt(inNewCode) );
        newStock.setProduct(inNewProduct);
        newStock.setCost(Float.parseFloat(inNewCost));
        newStock.setStockLevel(Integer.parseInt(inNewQty));

        newStock.addNewStock(newStock);

    }

    public void editStockItem(String inNewCode, String inNewProduct,
                              String inNewCost, String inNewQty){

        //create temp Object
        Stock tempStock = new Stock();

        tempStock.loadStock();

        //retrieving elected stock item
        Stock forEdit = tempStock.getSelectedStock(lstGoods.getSelectedIndex());

        forEdit.setCode(Integer.parseInt(inNewCode));
        forEdit.setProduct(inNewProduct);
        forEdit.setCost(Float.parseFloat(inNewCost));
        forEdit.setStockLevel(Integer.parseInt(inNewQty));

        updateStockList(forEdit);




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
        int tcode = tempObject.getCode();
        String tprod = tempObject.getProduct();
        float tcost = tempObject.getCost();
        int tqty = tempObject.getStockLevel();

        Object[] tempMessage = {
                "Please confirm you wish to PERMANENTLY delete this product.",
                "Code: " + tcode, "Product: " + tprod +
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

                String tempList = "";
                String tempProduct;



                if (i >= 1){
                    tempList = "\n";

                }
                String tempCode = tempStock.get(i).toString();
                //add to array with separator
                tempList += tempCode + "|";

                tempProduct = tempStock.get(i).getProduct();
                tempList += tempProduct + "|";

                String tempCost = tempStock.get(i).toString();
                tempList += tempCost + "|";

                String tempQty = tempStock.get(i).toString();
                tempList += tempQty;

                writer.write(tempList);

            }
            writer.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
