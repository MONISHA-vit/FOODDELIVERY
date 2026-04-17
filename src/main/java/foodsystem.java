package src.main.java;

import java.awt.*;
import java.util.HashMap;
import java.util.HashSet;
import javax.swing.*;

public class foodsystem extends JFrame {
    private HashSet<String> orderIds = new HashSet<>();
    private HashMap<String, String> orderTracker = new HashMap<>();
    private JTextField idField, itemField, trackField;
    private JTextArea displayArea;

    // --- Business Logic Methods ---
    public String placeOrderLogic(String id, String item) {
        if (id != null && !id.trim().isEmpty() && item != null && !item.trim().isEmpty()) {
            if (this.orderIds.contains(id)) {
                return "DUPLICATE";
            } else {
                this.orderIds.add(id);
                this.orderTracker.put(id, "Order Placed - Preparing");
                return "SUCCESS";
            }
        } else {
            return "INVALID";
        }
    }

    public String trackOrderLogic(String id) {
        return this.orderTracker.getOrDefault(id, "NOT_FOUND");
    }

    public foodsystem() {
        this.setTitle("Food Delivery Management System");
        this.setSize(450, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        Color primaryColor = new Color(255, 69, 0);
        Font headerFont = new Font("Arial", Font.BOLD, 18);

        JPanel header = new JPanel();
        header.setBackground(primaryColor);
        JLabel title = new JLabel("Online Food Delivery");
        title.setForeground(Color.WHITE);
        title.setFont(headerFont);
        header.add(title);
        this.add(header, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.add(new JLabel("Order ID:"));
        this.idField = new JTextField();
        inputPanel.add(this.idField);
        inputPanel.add(new JLabel("Food Item:"));
        this.itemField = new JTextField();
        inputPanel.add(this.itemField);

        JButton placeBtn = new JButton("Place Order");
        placeBtn.setBackground(primaryColor);
        placeBtn.setForeground(Color.WHITE);
        inputPanel.add(new JLabel(""));
        inputPanel.add(placeBtn);

        JPanel trackPanel = new JPanel(new FlowLayout());
        this.trackField = new JTextField(10);
        JButton trackBtn = new JButton("Track Status");
        trackPanel.add(new JLabel("Search ID:"));
        trackPanel.add(this.trackField);
        trackPanel.add(trackBtn);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(inputPanel, BorderLayout.NORTH);
        centerPanel.add(trackPanel, BorderLayout.CENTER);
        this.add(centerPanel, BorderLayout.CENTER);

        this.displayArea = new JTextArea(8, 20);
        this.displayArea.setEditable(false);
        this.add(new JScrollPane(this.displayArea), BorderLayout.SOUTH);

        // Event Listeners with unique variable names
        placeBtn.addActionListener((e) -> {
            String idVal = this.idField.getText().trim();
            String itemVal = this.itemField.getText().trim();
            String result = this.placeOrderLogic(idVal, itemVal);

            if (result.equals("INVALID")) {
                this.displayArea.setText("Error: Fields cannot be empty!");
            } else if (result.equals("DUPLICATE")) {
                this.displayArea.setText("FAILED: Duplicate ID (" + idVal + ")");
            } else {
                this.displayArea.setText("SUCCESS: Order for " + itemVal + " placed!\nID: " + idVal);
                this.idField.setText("");
                this.itemField.setText("");
            }
        });

        trackBtn.addActionListener((e) -> {
            String searchId = this.trackField.getText().trim();
            String status = this.trackOrderLogic(searchId);
            if (status.equals("NOT_FOUND")) {
                this.displayArea.setText("TRACKING ID [" + searchId + "]:\nOrder Not Found");
            } else {
                this.displayArea.setText("TRACKING ID [" + searchId + "]:\nStatus: " + status);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> (new foodsystem()).setVisible(true));
    }
}
