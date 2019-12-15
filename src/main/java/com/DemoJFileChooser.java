package com;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DemoJFileChooser extends JPanel implements ActionListener {
    JButton Browse;

    JFileChooser chooser;
    String choosertitle;

    public DemoJFileChooser() {
        Browse = new JButton("Browse");
        Browse.addActionListener(this);
        add(Browse);
    }

    public Dimension getPreferredSize(){
        return new Dimension(200, 200);
    }

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(choosertitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
            App.setDirectory(chooser.getSelectedFile().toString());
        }
        else {
            System.out.println("No Selection ");
        }
    }
    public void test(){
        JFrame frame = new JFrame("");
        DemoJFileChooser panel = new DemoJFileChooser();
        frame.getContentPane().add(panel,"Center");
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);
    }
}

