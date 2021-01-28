package com.model;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Stock {

    private String code;
    private String product;
    private String cost;
    private String stockLevel;
    public String[] tempArray;

    public String filepath = "resources\\StockList.txt";
    public String separator = "\\|";
    public final ArrayList<Stock> stockFile = new ArrayList<>();


    //getters and setters.

    public String getCode(){

        return this.code;
    }
    public void setCode(String inCode){

        this.code = inCode;
    }


    public String getProduct(){

        return this.product;
    }
    public void setProduct(String inProduct){

        this.product = inProduct;
    }


    public String getCost(){

        return this.cost;
    }
    public void setCost(String inCost){

        this.cost = inCost;
    }


    public String getStockLevel(){

        return this.stockLevel;
    }
    public void setStockLevel(String inStockLevel){

        this.stockLevel = inStockLevel;
    }

    public String[] getStockFile(){

        return tempArray;
    }


    //load stock_List from resources folder
    public void loadStock(){

        try{
            File tempFile = new File(filepath);
            Scanner scanner = new Scanner(tempFile);

            while (scanner.hasNextLine()){
                String tableRow = scanner.nextLine();

                String[] stockData = tableRow.split(separator);
                System.out.println(stockData[0]);

                Stock stock = new Stock();
                stock.setCode(stockData[0]);
                stock.setProduct(stockData[1]);
                stock.setCost(stockData[2]);
                stock.setStockLevel(stockData[3]);


                stockFile.add(stock);

            }
            scanner.close();
            System.out.println("File successfully loaded.");

            //Test code from development
//            System.out.println(stockFile.size());
//
//            //code to output stockFile to console
//            Iterator itr = stockFile.iterator();
//            while (itr.hasNext()){
//                Stock stock = (Stock) itr.next();
//                System.out.println("Code: " + stock.getCode() + " Product: " + stock.getProduct()
//                + " Cost: " + stock.getCost() + " Stock Level: " + stock.getStockLevel());
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewStock(Stock newStock){

        stockFile.add(newStock);

    }

    public Stock getSelectedStock(int inSelectedIndex){

        //return null if selectedIndex is outside range
        if(inSelectedIndex >= stockFile.size())
        {
            return null;
        }
        return stockFile.get(inSelectedIndex);

    }

    public String displayDetails(){

        String details = new String(product + cost);
        return details;

    }

    public String forBasket(){

        String forBasket = new String(product + cost);
        return forBasket;
    }

//    public void setNewCost(String inCost){
//        this.newCost = inCost;
//    }

    public void deleteStock(Stock delStock){

        stockFile.remove(delStock);
    }

}
