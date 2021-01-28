package com.view;

import com.model.Stock;
import com.view.IEditStock;
import com.controller.systemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.text.DecimalFormat;

public class IStartScreen extends JFrame{

    public JPanel mainPanel;
    private JPanel rightPanel;
    private JPanel leftPanel;
    private JButton btnCheckout;
    private JTextField txtInCode;
    private JButton btnStaff;
    private JLabel lblRunningTotal;
    public JList lstGoods;
    public JList lstForBasket;
    private JLabel lblName;
    private JButton btnAdd;
    private JLabel lblHint;
    private JButton btnBasket;

    public Float runningTotal = 0.0f;
    //public String runningTotal;

    public JOptionPane forlogin;


    File listOfStock  = new File("resources\\StockList.txt");
    //public String separator = "\\|";
    private ArrayList<Stock> stockFile = new ArrayList<>();

    public IStartScreen(JFrame startFrame, JFrame next){

        lstForBasket.setModel(new DefaultListModel());

        btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staffScreen(startFrame, lstGoods);
            }
        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getStockCode();
            }
        });

        btnCheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                systemController.checkoutStart(lstForBasket, runningTotal, startFrame, next);
            }
        });


    }

    public void staffScreen(JFrame startFrame, JList lstGoods){

        JFrame forLogin = new JFrame();
        forLogin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        JTextField txtUsername = new JTextField();
        JTextField txtPassword = new JPasswordField();

        //Hard coded login details.
        String userName = "staff";
        String passWord = "password";

        Object[] inputs = {"UserName: ", txtUsername,
        "Password: ", txtPassword};

        int options = forlogin.showConfirmDialog(forLogin, inputs, "Staff Login", JOptionPane.OK_CANCEL_OPTION);

        if( options == forlogin.OK_OPTION){
            if(txtUsername.getText().equals(userName) && txtPassword.getText().equals(passWord)){

                forlogin.showMessageDialog(forLogin, "Staff Login Correct.", "Staff Login",
                        JOptionPane.INFORMATION_MESSAGE);

                systemController.adminStart(startFrame, lstGoods);
            }
            else{
                forlogin.showMessageDialog(forLogin, "Staff Login failed.", "Staff Login",
                        JOptionPane.INFORMATION_MESSAGE);
                }

            }

        else{
            forlogin.showMessageDialog(forLogin, "Staff Login Correct.", "Staff Login",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void getStockCode(){

        DefaultListModel ModelBasket = (DefaultListModel) lstForBasket.getModel();

        String separator = "\\|";

        String matchedCode;

        try
        {
            //method to read file
            BufferedReader reader = new BufferedReader(new FileReader(listOfStock));

            //String currentLine;
            String currentLine = null;

            while ((currentLine = reader.readLine()) != null)
            {

                String[] storedLine = currentLine.split(separator);
                //String[] attribute = CLine.split(separator);
                System.out.println(storedLine);

                if(storedLine[0].equals(txtInCode.getText()))
                {
                    matchedCode = currentLine;

                    ModelBasket.addElement(matchedCode);

                    modifyBasketFigures(storedLine);
                    txtInCode.setText("");
                }
            }
            reader.close();
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void modifyBasketFigures(String[] inArray){
        //public void modifyBasketFigures(String[] itemCost){



        String tempCost;
        tempCost = inArray[2];
        System.out.println(tempCost);
        tempCost= tempCost.replace("£","");

        runningTotal = runningTotal + Float.parseFloat(tempCost);

        //lblRunningTotal.setText("£" + String.format("%.2f", runningTotal));
        lblRunningTotal.setText("Total: £" + String.format("%.2f", runningTotal));

    }
}
