package main;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMInterfaceAgent extends Agent {

    private ATMGui atmGui;

    protected void setup() {
        atmGui = new ATMGui(this);
    }

    private class ATMGui extends JFrame {

        ATMGui(final Agent agent) {
            super("ATM Interface");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);

            JButton checkBalanceButton = new JButton("Check Balance");
            JButton depositButton = new JButton("Deposit");
            JButton withdrawButton = new JButton("Withdraw");

            checkBalanceButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new CheckBalanceForm(agent);
                }
            });

            depositButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DepositForm(agent);
                }
            });

            withdrawButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new WithdrawForm(agent);
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));
            panel.add(checkBalanceButton);
            panel.add(depositButton);
            panel.add(withdrawButton);

            add(panel);
            setVisible(true);
        }
    }

    private class CheckBalanceForm extends JFrame {
        CheckBalanceForm(final Agent agent) {
            super("Check balance Form");
            setSize(300, 200);

            JTextField CardNumberFiled = new JTextField();
            JTextField CvvNumberFiled = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String CardNumber = CardNumberFiled.getText();
                    String CvvNumber = CvvNumberFiled.getText();

                    System.out.println("Check balance form submitted:");
                    System.out.println("main.Account Number: " + CardNumber);
                    System.out.println("Secret Code: " + CvvNumber);
                }
            });

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Card Number:"));
            panel.add(CardNumberFiled);
            panel.add(new JLabel("Cvv Code:"));
            panel.add(CvvNumberFiled);
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(submitButton);

            add(panel);
            setVisible(true);
        }
    }

    private class DepositForm extends JFrame {
        DepositForm(final Agent agent) {
            super("Deposit Form");
            setSize(300, 200);

            JTextField CardNumberFiled = new JTextField();
            JTextField CvvNumberFiled = new JTextField();
            JTextField amountField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String accountNumber = CardNumberFiled.getText();
                    String secretCode = CvvNumberFiled.getText();
                    String amount = amountField.getText();

                    System.out.println("Deposit form submitted:");
                    System.out.println("Card Number: " + accountNumber);
                    System.out.println("Cvv: " + secretCode);
                    System.out.println("Amount: " + amount);
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("Card Number:"));
            panel.add(CardNumberFiled);
            panel.add(new JLabel("Cvv Code:"));
            panel.add(CvvNumberFiled);
            panel.add(new JLabel("Amount:"));
            panel.add(amountField);
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(submitButton);

            add(panel);
            setVisible(true);
        }
    }

    private class WithdrawForm extends JFrame {
        WithdrawForm(final Agent agent) {
            super("Withdraw Form");
            setSize(300, 200);

            JTextField CardNumberFiled = new JTextField();
            JTextField CvvNumberFiled = new JTextField();
            JTextField amountField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String CardNumber = CardNumberFiled.getText();
                    String CvvNumber = CvvNumberFiled.getText();
                    String amount = amountField.getText();

                    // Simulate processing or communicate with the back-end agent
                    System.out.println("Withdraw form submitted:");
                    System.out.println("Card Number: " + CardNumber);
                    System.out.println("Cvv: " + CvvNumber);
                    System.out.println("Amount: " + amount);

                    // You can add logic here to communicate with the back-end or perform other actions
                    // For example, you might want to send a message to the back-end agent for withdrawal processing
                    // agent.send(...);

                    // Close the form after submission
                    dispose();
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("Card Number:"));
            panel.add(CardNumberFiled);
            panel.add(new JLabel("Cvv Code:"));
            panel.add(CvvNumberFiled);
            panel.add(new JLabel("Amount:"));
            panel.add(amountField);
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(submitButton);

            add(panel);
            setVisible(true);
        }
    }
}

