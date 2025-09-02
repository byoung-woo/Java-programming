package 박병우exam;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Calculator extends Frame implements ActionListener {
	  Panel p1;
	  TextField tf;
      Label lbl_phone;
      static double op1, op2, result;
      static String operator;
      static int index;
      StringBuffer sb;
      Panel p2;
      Panel p3;    
      
	public Calculator() {
	        super("Standard Calculator");
	      
	        sb = new StringBuffer("");
	        p1 = new Panel();
	        p2 = new Panel();
	        p3 = new Panel();
	        tf = new TextField(30);
	 
	        Button b1 = new Button("%");
	        Button b2 = new Button("CE");
	        Button b3 = new Button("C");
	        Button b4 = new Button("<-");
	        Button b5 = new Button("1/x");
	        Button b6 = new Button("x^2");
	        Button b7 = new Button("sqrt");
	        Button b8 = new Button("/");                                                           	                                           	
	        Button b9 = new Button("7");
	        Button b10 = new Button("8");
	        Button b11 = new Button("9");
	        Button b12 = new Button("x");
	        Button b13 = new Button("4");
	        Button b14 = new Button("5");
	        Button b15 = new Button("6");
	        Button b16 = new Button("-");
	        Button b17 = new Button("1");
	        Button b18 = new Button("2");
	        Button b19 = new Button("3");
	        Button b20 = new Button("+");
	        Button b21 = new Button("+/-");                                                           	                                           	
	        Button b22 = new Button("0");
	        Button b23 = new Button(".");
	        Button b24 = new Button("=");

	       
	        ActionListener numberButtonListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String buttonLabel = ((Button) e.getSource()).getLabel();
	                tf.setText(tf.getText() + buttonLabel);
	            }
	        };
	        ActionListener CButtonListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                    tf.setText("");
	                                                      	
	            }
	        };
	        ActionListener clearButtonListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String text = tf.getText();
	                if (text.length() > 0) {
	                    text = text.substring(0, text.length() - 1);
	                    tf.setText(text);
	                }                                        	
	            }
	        };
	        ActionListener ButtonListener = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               
	                                                       	
	            }
	        };
	        
	 
	        b1.addActionListener(ButtonListener);
	        b2.addActionListener(CButtonListener);
	        b3.addActionListener(CButtonListener);
	        b4.addActionListener(clearButtonListener);
	        b5.addActionListener(ButtonListener);
	        b6.addActionListener(ButtonListener);
	        b7.addActionListener(ButtonListener);
	        b8.addActionListener(ButtonListener);
	        b9.addActionListener(numberButtonListener);
	        b10.addActionListener(numberButtonListener);
	        b11.addActionListener(numberButtonListener);
	        b12.addActionListener(ButtonListener);
	        b13.addActionListener(numberButtonListener);
	        b14.addActionListener(numberButtonListener);
	        b15.addActionListener(numberButtonListener);
	        b16.addActionListener(ButtonListener);
	        b17.addActionListener(numberButtonListener);
	        b18.addActionListener(numberButtonListener);
	        b19.addActionListener(numberButtonListener);
	        b20.addActionListener(ButtonListener);
	        b21.addActionListener(ButtonListener);
	        b22.addActionListener(numberButtonListener);
	        b23.addActionListener(numberButtonListener);
	        b24.addActionListener(ButtonListener);
	       
	        b1.setBackground(Color.PINK);
	        b2.setBackground(Color.PINK);
	        b3.setBackground(Color.PINK);
	        b4.setBackground(Color.PINK);
	        b5.setBackground(Color.PINK);
	        b6.setBackground(Color.PINK);
	        b7.setBackground(Color.PINK);
	        b8.setBackground(Color.PINK);
	        b12.setBackground(Color.PINK);
	        b16.setBackground(Color.PINK);
	        b20.setBackground(Color.PINK);
	        b24.setBackground(Color.PINK);
	        b21.setBackground(Color.lightGray);
	        b23.setBackground(Color.lightGray); 
	        b9.setBackground(Color.lightGray);
	        b10.setBackground(Color.lightGray);
	        b11.setBackground(Color.lightGray);
	        b13.setBackground(Color.lightGray);
	        b14.setBackground(Color.lightGray);
	        b15.setBackground(Color.lightGray);
	        b17.setBackground(Color.lightGray);
	        b18.setBackground(Color.lightGray);
	        b19.setBackground(Color.lightGray);
	        b22.setBackground(Color.lightGray);
	        
	        p2.setLayout(new GridLayout(6, 4));
	        p2.add(b1);
	        p2.add(b2);
	        p2.add(b3);
	        p2.add(b4);
	        p2.add(b5);
	        p2.add(b6);
	        p2.add(b7);
	        p2.add(b8);
	        p2.add(b9);
	        p2.add(b10);
	        p2.add(b11);
	        p2.add(b12);
	        p2.add(b13);
	        p2.add(b14);
	        p2.add(b15);
	        p2.add(b16);
	        p2.add(b17);
	        p2.add(b18);
	        p2.add(b19);
	        p2.add(b20);
	        p2.add(b21);
	        p2.add(b22);
	        p2.add(b23);
	        p2.add(b24);
	 
	        p3.setLayout(new BorderLayout());
	
	        p3.add(tf, BorderLayout.CENTER);
	     
	 
	        p1.setLayout(new BorderLayout());
	        p1.add(p3, BorderLayout.NORTH);
	        p1.add(p2, BorderLayout.CENTER);
	 
	        add(p1);
	 
	        addWindowListener(new java.awt.event.WindowAdapter() {
	            public void windowClosing(java.awt.event.WindowEvent evt) {
	                System.exit(0);
	            }
	        });
	 
	        setLocation(300, 300);
	        setSize(400, 600);
	        setVisible(true);
	    }

public static void main(String[] args) {
	new Calculator();
	}
}

