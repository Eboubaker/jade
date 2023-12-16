package main;

import communication.BlackBoardController;
import communication.request.CheckBalanceRequest;
import communication.request.DepositRequest;
import communication.request.SignUpRequest;
import communication.request.WithdrawRequest;
import jade.core.Agent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AGCInterfaceAgent extends Agent {
    public final static long serialVersionUID = 212927242086L;

    private AGCGui agcGui;

    protected void setup() {
        agcGui = new AGCGui(this);
    }

    private class AGCGui extends JFrame {

        AGCGui(final Agent agent) {
            super("AGC Interface");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);

            JButton signUpButton = new JButton("Sign Up");
            JButton checkBalanceButton = new JButton("Check Balance");
            JButton depositButton = new JButton("Deposit");
            JButton withdrawButton = new JButton("Withdraw");

            signUpButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new SignUpForm(agent);
                }
            });

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
            panel.setLayout(new GridLayout(4, 1));
            panel.add(signUpButton);
            panel.add(checkBalanceButton);
            panel.add(depositButton);
            panel.add(withdrawButton);

            add(panel);
            setVisible(true);
        }
    }

    private class SignUpForm extends JFrame {

        SignUpForm(final Agent agent) {
            super("Sign Up Form");
            setSize(300, 200);

            JTextField firstNameField = new JTextField();
            JTextField lastNameField = new JTextField();
            JTextField nationalNumberField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String nationalNumber = nationalNumberField.getText();
                    send(new SignUpRequest(getAID(), firstName, lastName, nationalNumber)
                            .toMessage(getAID(BlackBoardController.class.getSimpleName())));
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("First Name:"));
            panel.add(firstNameField);
            panel.add(new JLabel("Last Name:"));
            panel.add(lastNameField);
            panel.add(new JLabel("National Number:"));
            panel.add(nationalNumberField);
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(submitButton);

            add(panel);
            setVisible(true);
        }
    }

    private class CheckBalanceForm extends JFrame {
        CheckBalanceForm(final Agent agent) {
            super("Check balance Form");
            setSize(300, 200);

            JTextField accountNumberField = new JTextField();
            JTextField secretCodeField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String accountNumber = accountNumberField.getText();
                    String secretCode = secretCodeField.getText();

                    System.out.println("Check balance form submitted:");
                    System.out.println("CCP: " + accountNumber);
                    System.out.println("Code: " + secretCode);
                    send(new CheckBalanceRequest(getAID(), accountNumber, secretCode)
                            .toMessage(getAID(BlackBoardController.class.getSimpleName())));
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("main.Account  Number (CCP):"));
            panel.add(accountNumberField);
            panel.add(new JLabel("Secret Code:"));
            panel.add(secretCodeField);
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

            JTextField accountNumberField = new JTextField();
            JTextField secretCodeField = new JTextField();
            JTextField amountField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String accountNumber = accountNumberField.getText();
                    String secretCode = secretCodeField.getText();
                    double amount = Double.parseDouble(amountField.getText());


                    System.out.println("Deposit form submitted:");
                    System.out.println("CCP: " + accountNumber);
                    System.out.println("Code: " + secretCode);
                    System.out.println("Amount: " + amount);

                    send(new DepositRequest(getAID(), accountNumber, secretCode, amount)
                            .toMessage(getAID(BlackBoardController.class.getSimpleName())));
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("main.Account  Number (CCP):"));
            panel.add(accountNumberField);
            panel.add(new JLabel("Secret Code:"));
            panel.add(secretCodeField);
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

            JTextField accountNumberField = new JTextField();
            JTextField secretCodeField = new JTextField();
            JTextField amountField = new JTextField();

            JButton submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Handle form submission and communication with the back-end agent
                    String accountNumber = accountNumberField.getText();
                    String secretCode = secretCodeField.getText();
                    double amount = Double.parseDouble(amountField.getText());

                    System.out.println("Withdraw form submitted:");
                    System.out.println("CCP: " + accountNumber);
                    System.out.println("Code: " + secretCode);
                    System.out.println("Amount: " + amount);
                    send(new WithdrawRequest(getAID(), accountNumber, secretCode, amount)
                            .toMessage(getAID(BlackBoardController.class.getSimpleName())));
                }
            });

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("main.Account  Number (CCP):"));
            panel.add(accountNumberField);
            panel.add(new JLabel("Secret Code:"));
            panel.add(secretCodeField);
            panel.add(new JLabel("Amount:"));
            panel.add(amountField);
            panel.add(new JLabel()); // Empty label for spacing
            panel.add(submitButton);

            add(panel);
            setVisible(true);
        }
    }
}
