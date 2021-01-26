package com.controller;

//import com.view.IEditStock;

import javax.swing.*;
import com.view.*;
import com.model.*;

public class systemController {

    public JFrame inFrame;
    public JFrame mainPanel;

    public static void systemStart(JFrame inFrame){


        JFrame startFrame = new JFrame("Start Screen");
        startFrame.setContentPane(new IStartScreen(startFrame, inFrame).mainPanel);
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setSize(600, 400);
        startFrame.setVisible(true);
        Stock stockFile = new Stock();
        stockFile.loadStock();


    }


    public static void adminStart(JFrame startFrame, JList stockList){


        JFrame staff = new JFrame("Staff");
        staff.setContentPane(new IEditStock(staff, stockList).mainPanel);
        staff.setSize(800, 750);
        staff.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        staff.setVisible(true);
        startFrame.setVisible(false);

    }
}
