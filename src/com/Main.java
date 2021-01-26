package com;
import com.model.Stock;
import com.controller.systemController;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main (String[] args){

        System.out.println("Hello World!");
        systemController system = new systemController();
        systemController.systemStart(system.inFrame);




    }
}
