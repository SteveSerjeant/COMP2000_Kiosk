package com.view;

import com.model.Stock;
import com.controller.systemController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class IStartScreen extends JFrame{

    public JPanel mainPanel;
    private JTextField txtStockCode;
    private JButton btnStaff;
    private JLabel lblRunningTotal;
    public JList lstGoods;
    public JList lstForBasket;

    public Float runningTotal = 0.0f;
    public JOptionPane forlogin;


    File listOfStock  = new File("resources\\StockList.txt");
    public String separator = "\\|";
    private final ArrayList<Stock> stockFile = new ArrayList<>();

    public IStartScreen(JFrame startFrame, JFrame next){

        lstForBasket.setModel(new DefaultListModel());

        btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staffScreen(startFrame, lstGoods);
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

        DefaultListModel modelBasket = (DefaultListModel) lstGoods.getModel();

        String separator = "|";

        String matchedCode;

        try {
            //method to read file
            BufferedReader reader = new BufferedReader(new FileReader(listOfStock));

            String currentLine = null;

            while ((currentLine = reader.readLine()) != null){

                String[] storedLine = currentLine.split(separator);

                if(storedLine[0].equals(txtStockCode.getText())){
                    matchedCode = currentLine;

                    modelBasket.addElement(matchedCode);

                    modifyBasketFigures(storedLine);
                }
            }
            reader.close();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyBasketFigures(String[] itemCost){

        String tempCost;
        tempCost = itemCost[2];

    runningTotal = runningTotal + Float.parseFloat(tempCost);

    lblRunningTotal.setText("£" + String.format("%.2f", runningTotal));


    }
}
