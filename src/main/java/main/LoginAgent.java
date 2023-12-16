package main;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAgent extends Agent {
    private static final long serialVersionUID = 235123681L;

    protected void setup() {
        addBehaviour(new GUIBehaviour());
    }

    private static class GUIBehaviour extends OneShotBehaviour {
        private static final long serialVersionUID = 21248535681L;

        public void action() {
            JFrame frame = new JFrame("Bank Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new GridLayout(5, 2));

            JLabel cardNumberLabel = new JLabel("Enter Card Number:");
            JTextField cardNumberField = new JTextField();

            JLabel cardHolderLabel = new JLabel("Enter Cardholder Name:");
            JTextField cardHolderField = new JTextField();

            JLabel expirationLabel = new JLabel("Enter Expiration Date (MM/YY):");
            JTextField expirationField = new JTextField();

            JLabel cvvLabel = new JLabel("Enter CVV:");
            JTextField cvvField = new JTextField();

            JButton loginButton = new JButton("Login");

            loginButton.addActionListener(e -> {
                String cardNumber = cardNumberField.getText();
                String cardHolder = cardHolderField.getText();
                String expiration = expirationField.getText();
                String cvv = cvvField.getText();

                new ACLMessage(ACLMessage.INFORM);

                // Print entered details for demonstration
                System.out.println("Card Number: " + cardNumber);
                System.out.println("Cardholder Name: " + cardHolder);
                System.out.println("Expiration Date: " + expiration);
                System.out.println("CVV: " + cvv);
            });

            frame.add(cardNumberLabel);
            frame.add(cardNumberField);
            frame.add(cardHolderLabel);
            frame.add(cardHolderField);
            frame.add(expirationLabel);
            frame.add(expirationField);
            frame.add(cvvLabel);
            frame.add(cvvField);
            frame.add(loginButton);

            frame.setVisible(true);
        }
    }
}
