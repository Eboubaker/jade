package main;

import jade.core.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TPEInterfaceAgent extends Agent {

    private ProductInterface productInterface;

    protected void setup() {
        SwingUtilities.invokeLater(() -> {
            productInterface = new ProductInterface(this);
            productInterface.setVisible(true);
        });
    }

    private static class ProductInterface extends JFrame {

        private Agent agent;
        private Product[] products;

        public ProductInterface(Agent agent) {
            super("Product Interface");
            this.agent = agent;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 400);

            // Generate some sample products
            generateSampleProducts();

            // Create a panel with a vertical layout for the products
            JPanel productPanel = new JPanel();
            productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

            // Add each product to the panel
            for (Product product : products) {
                productPanel.add(createProductPanel(product));
            }

            // Create a scroll pane for the product panel
            JScrollPane scrollPane = new JScrollPane(productPanel);

            // Add the scroll pane to the frame
            add(scrollPane);

            setVisible(false);
        }

        private void generateSampleProducts() {
            products = new Product[3];

            // Sample product data (replace with your actual data)
            String[] productNames = {"Product 1", "Product 2", "Product 3"};
            String[] imagePaths = {"images/1.jpg", "images/2.png", "images/3.jfif"};
            double[] prices = {19.99, 29.99, 39.99};

            Random random = new Random();
            for (int i = 0; i < 3; i++) {
                Product product = new Product(productNames[i], imagePaths[i], prices[i]);
                products[i] = product;
            }
        }

        private JPanel createProductPanel(Product product) {
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

            // Add product image (replace with your actual image loading logic)
            ImageIcon imageIcon = new ImageIcon(product.getImagePath());
            Image image = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(scaledIcon);
            panel.add(imageLabel);

            // Add product name
            JLabel nameLabel = new JLabel(product.getName());
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(nameLabel);

            // Add product price
            JLabel priceLabel = new JLabel("$" + product.getPrice());
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(priceLabel);

            // Add "Buy" button
            JButton buyButton = new JButton("Buy");
            buyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle buy button click (e.g., open the payment window)
                    openPaymentWindow(product);
                }
            });
            panel.add(buyButton);

            // Add some spacing between products
            panel.add(Box.createVerticalStrut(20));

            return panel;
        }

        private void openPaymentWindow(Product product) {
            JFrame paymentFrame = new JFrame("Payment Window");
            paymentFrame.setSize(300, 200);

            // Add product name and price to the payment window
            JLabel nameLabel = new JLabel("Product: " + product.getName());
            JLabel priceLabel = new JLabel("Price: $" + product.getPrice());

            // Create fields for Card Number and CVV code
            JTextField cardNumberField = new JTextField();
            JTextField cvvField = new JTextField();

            // Add labels and fields to the payment window
            JPanel paymentPanel = new JPanel(new GridLayout(4, 2));
            paymentPanel.add(nameLabel);
            paymentPanel.add(priceLabel);
            paymentPanel.add(new JLabel("Card Number:"));
            paymentPanel.add(cardNumberField);
            paymentPanel.add(new JLabel("CVV Code:"));
            paymentPanel.add(cvvField);

            // Add "Confirm" button
            JButton confirmButton = new JButton("Confirm");
            confirmButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Handle confirm button click (e.g., process the payment)
                    String cardNumber = cardNumberField.getText();
                    String cvvCode = cvvField.getText();
                    System.out.println("Payment Confirmed for " + product.getName() +
                            " with Card Number: " + cardNumber +
                            " and CVV Code: " + cvvCode);
                    paymentFrame.dispose(); // Close the payment window
                }
            });
            paymentPanel.add(confirmButton);

            // Add the payment panel to the payment frame
            paymentFrame.add(paymentPanel);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            paymentFrame.setLocation(dim.width / 2 - paymentFrame.getSize().width / 2, dim.height / 2 - paymentFrame.getSize().height / 2);

            // Set default close operation and make the payment frame visible
            paymentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            paymentFrame.setVisible(true);
        }
    }

    private static class Product {
        private String name;
        private String imagePath;
        private double price;

        public Product(String name, String imagePath, double price) {
            this.name = name;
            this.imagePath = imagePath;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getImagePath() {
            return imagePath;
        }

        public double getPrice() {
            return price;
        }
    }
}
