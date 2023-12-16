package main;

import java.io.Serializable;

public class Account implements Serializable {
    public final static long serialVersionUID = 284556873086L;
    public String accountNumber;
    public String secretCode;
    public String ccv;
    public String firstName;
    public String lastName;
    public String cardNumber;
    public String cardExpiryDate;
}

