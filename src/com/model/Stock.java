package com.model;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Stock {

    private int code;
    private String product;
    private float cost;
    private int stockLevel;
    public String[] tempArray;

    public String filepath = "resources\\StockList.txt";
    public String separator = "\\|";
    private final ArrayList<Stock> stockFile = new ArrayList<>();


    //getters and setters.

    public int getCode(){
        return this.code;
    }
    public void setCode(int inCode){
        this.code = inCode;
    }


    public String getProduct(){

        return this.product;
    }
    public void setProduct(String inProduct){

        this.product = inProduct;
    }


    public float getCost(){

        return this.cost;
    }
    public void setCost(float inCost){

        this.cost = inCost;
    }


    public  int getStockLevel(){

        return this.stockLevel;
    }
    public  void setStockLevel(int inStockLevel){

        this.stockLevel = inStockLevel;
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
                int tempCode = Integer.parseInt(stockData[0]);
                stock.setCode(tempCode);
                stock.setProduct(stockData[1]);
                float tempCost = Float.parseFloat(stockData[2]);
                stock.setCost(tempCost);
                int tempLevel = Integer.parseInt(stockData[3]);
                stock.setStockLevel(tempLevel);

                stockFile.add(stock);

            }
            scanner.close();
            System.out.println("File successfully loaded.");
            System.out.println(stockFile.size());

            //code to output stockFile to console
            Iterator itr = stockFile.iterator();
            while (itr.hasNext()){
                Stock stock = (Stock) itr.next();
                System.out.println("Code: " + stock.getCode() + " Product: " + stock.getProduct()
                + " Cost: " + stock.getCost() + "Stock Level: " + stock.getStockLevel());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
