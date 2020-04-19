/*
 * Created by JFormDesigner on Tue Dec 24 15:19:30 EET 2019
 */

package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;

/**
 * @author hafsa
 */
public class GUI extends JPanel {
    public GUI() {
        initComponents();
    }
    public static void main(String[] args) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    private void btncomActionPerformed(ActionEvent e) throws IOException {
        String data = Main.read_from_file();
        String[] separate = data.split(",");
        ArrayList<String> returnn = Main.zeros(separate);

        Main.getFrequency(returnn);
        StandardCompression standard = new StandardCompression(Main.listdata);
        standard.sortNodes();
        Node root = standard.createTree();
        Main.writeinFile1(standard.createTableCodes(root, ""));
        Main.Codes_symbol= standard.Codes_symbol;
        Main.Codes_code = standard.Codes_code;

        Main.writeinFile(Main.compress());
    }

    private void btndecomActionPerformed(ActionEvent e) throws IOException {
        Main.writeinFile2(Main.decompress());
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - hafsa
        btncom = new JButton();
        btndecom = new JButton();

        //======== this ========
        setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax
        . swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing
        . border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .
        Font ("D\u0069alog" ,java .awt .Font .BOLD ,12 ), java. awt. Color. red
        ) , getBorder( )) );  addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override
        public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .getPropertyName (
        ) )) throw new RuntimeException( ); }} );
        setLayout(null);

        //---- btncom ----
        btncom.setText("COMPRESS");
        btncom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btncomActionPerformed(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(btncom);
        btncom.setBounds(new Rectangle(new Point(30, 65), btncom.getPreferredSize()));

        //---- btndecom ----
        btndecom.setText("DECOMPRESS");
        btndecom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    btndecomActionPerformed(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(btndecom);
        btndecom.setBounds(185, 65, 93, 30);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < getComponentCount(); i++) {
                Rectangle bounds = getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            setMinimumSize(preferredSize);
            setPreferredSize(preferredSize);
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - hafsa
    private JButton btncom;
    private JButton btndecom;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
