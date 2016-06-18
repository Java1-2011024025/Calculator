package Calculator;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {

   private JPanel panel;
   private JTextField tField;
   private JButton[] buttons;
   private JTextArea tArea;

   private String[] labels = { "7", "8", "9", "+", "C", "4", "5", "6", "-", "^2", "1", "2", "3", "*", "1/x", "0",
         " .", "backspace", "/", "=" };
   private double result = 0;
   private String operator = "=";
   private boolean startOfNumber = true;

   public Calculator() {
      tField = new JTextField(20);
      panel = new JPanel();
      tField.setText("0");
      tField.setEnabled(true);
      
      tArea = new JTextArea(3,0);

      panel.setLayout(new GridLayout(4, 5, 0, 0));
      buttons = new JButton[20];

      int index = 0;
      for (int rows = 0; rows < 4; rows++) {
         for (int cols = 0; cols < 5; cols++) {
            buttons[index] = new JButton(labels[index]);
            if (cols >= 3)
            	buttons[index].setForeground(Color.red);
            else if ( rows == 3 && cols == 2 )
            	buttons[index].setForeground(Color.blue);
            else
            	buttons[index].setForeground(Color.black);
            buttons[index].setBackground(Color.lightGray);
            panel.add(buttons[index]);
            buttons[index].addActionListener(this);
            index++;
         }
      }
      JScrollPane scrollpane = new JScrollPane(tArea);
      add(scrollpane, BorderLayout.CENTER);
      add(tField, BorderLayout.NORTH);
      add(panel, BorderLayout.SOUTH);
      setVisible(true);
      pack();
      setTitle("K & S Calculator");
   }
   
   public void actionPerformed(ActionEvent e) {
	      tField.selectAll();
	      tArea.setCaretPosition(tArea.getDocument().getLength());

	      String actionCommand = e.getActionCommand();
	      String command = e.getActionCommand();
	      
	      if (command.charAt(0) == 'C') {
	         startOfNumber = true;
	         result = 0;
	         operator = "=";
	         tField.setText("0.0");
	         tArea.setText(tArea.getText() + "\n");
	      } else if (command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")) {
	         tArea.setText(tArea.getText() + actionCommand);
	         if (startOfNumber == true) {
	            tField.setText(command);
	         } else
	            tField.setText(tField.getText() + command);
	         startOfNumber = false;
	      } else {
	         tArea.setText(tArea.getText() + actionCommand);
	         if (startOfNumber) {
	            if (command.equals("-")) {
	               tField.setText(command);
	               startOfNumber = false;
	            } else
	               operator = command;
	         } else {
	            double x = Double.parseDouble(tField.getText());
	
	            operator = command;
	            startOfNumber = true;
	            if (operator.equals("="))
	               tArea.setText(tArea.getText() + result);
	         }
	      }
	   }

public static void main(String[] args) {
      // TODO Auto-generated method stub
      Calculator s = new Calculator();
   }
}
   
