// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package src.main.java;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class foodsystem extends JFrame {
   private HashSet<String> orderIds = new HashSet();
   private HashMap<String, String> orderTracker = new HashMap();
   private JTextField idField;
   private JTextField itemField;
   private JTextField trackField;
   private JTextArea displayArea;

   public String placeOrderLogic(String var1, String var2) {
      if (var1 != null && !var1.trim().isEmpty() && var2 != null && !var2.trim().isEmpty()) {
         if (this.orderIds.contains(var1)) {
            return "DUPLICATE";
         } else {
            this.orderIds.add(var1);
            this.orderTracker.put(var1, "Order Placed - Preparing");
            return "SUCCESS";
         }
      } else {
         return "INVALID";
      }
   }

   public String trackOrderLogic(String var1) {
      return (String)this.orderTracker.getOrDefault(var1, "NOT_FOUND");
   }

   public foodsystem() {
      this.setTitle("Food Delivery Management System");
      this.setSize(450, 500);
      this.setDefaultCloseOperation(3);
      this.setLayout(new BorderLayout(10, 10));
      Color var1 = new Color(255, 69, 0);
      Font var2 = new Font("Arial", 1, 18);
      JPanel var3 = new JPanel();
      var3.setBackground(var1);
      JLabel var4 = new JLabel("Online Food Delivery");
      var4.setForeground(Color.WHITE);
      var4.setFont(var2);
      var3.add(var4);
      this.add(var3, "North");
      JPanel var5 = new JPanel(new GridLayout(6, 1, 5, 5));
      var5.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
      var5.add(new JLabel("Order ID:"));
      this.idField = new JTextField();
      var5.add(this.idField);
      var5.add(new JLabel("Food Item:"));
      this.itemField = new JTextField();
      var5.add(this.itemField);
      JButton var6 = new JButton("Place Order");
      var6.setBackground(var1);
      var6.setForeground(Color.WHITE);
      var5.add(new JLabel(""));
      var5.add(var6);
      JPanel var7 = new JPanel(new FlowLayout());
      this.trackField = new JTextField(10);
      JButton var8 = new JButton("Track Status");
      var7.add(new JLabel("Search ID:"));
      var7.add(this.trackField);
      var7.add(var8);
      JPanel var9 = new JPanel(new BorderLayout());
      var9.add(var5, "North");
      var9.add(var7, "Center");
      this.add(var9, "Center");
      this.displayArea = new JTextArea(8, 20);
      this.displayArea.setEditable(false);
      this.add(new JScrollPane(this.displayArea), "South");
      var6.addActionListener((var1x) -> {
         String var2 = this.idField.getText().trim();
         String var3 = this.itemField.getText().trim();
         String var4 = this.placeOrderLogic(var2, var3);
         if (var4.equals("INVALID")) {
            this.displayArea.setText("Error: Fields cannot be empty!");
         } else if (var4.equals("DUPLICATE")) {
            this.displayArea.setText("FAILED: Duplicate ID (" + var2 + ")");
         } else {
            this.displayArea.setText("SUCCESS: Order for " + var3 + " placed!\nID: " + var2);
            this.idField.setText("");
            this.itemField.setText("");
         }

      });
      var8.addActionListener((var1x) -> {
         String var2 = this.trackField.getText().trim();
         String var3 = this.trackOrderLogic(var2);
         if (var3.equals("NOT_FOUND")) {
            this.displayArea.setText("TRACKING ID [" + var2 + "]:\nOrder Not Found");
         } else {
            this.displayArea.setText("TRACKING ID [" + var2 + "]:\nStatus: " + var3);
         }

      });
   }

   public static void main(String[] var0) {
      SwingUtilities.invokeLater(() -> (new foodsystem()).setVisible(true));
   }
}
