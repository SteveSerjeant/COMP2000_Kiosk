package com.view;

import com.model.Stock;
import com.controller.systemController;
import com.view.IEditStock;

import java.util.*;
import java.text.DecimalFormat;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IStartScreen extends JFrame{

    private JPanel mainPanel;
    private JButton btnStaff;
    public JOptionPane forlogin;


    public String filepath = "resources\\StockList.txt";
    public String separator = "\\|";
    private final ArrayList<Stock> stockFile = new ArrayList<>();

    public IStartScreen(){

        btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                staffScreen();
            }
        });

    }

    public void staffScreen(){

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

                systemController.adminStart();
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
}
